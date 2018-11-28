package com.wuyunu.simplekafkaclient.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class SimpleKafkaConsumer {
    private Properties props=new Properties();
    {
        props.put("bootstrap.servers", "10.22.3.3:9092");

        //每个消费者分配独立的组号
        props.put("group.id", "test");

        //如果value合法，则自动提交偏移量
        props.put("enable.auto.commit", "true");

        props.put("auto.commit.interval.ms", "1000");


        props.put("session.timeout.ms", "30000");

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }
    public void consume(){
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("TEST"));
        System.out.println("Subscribed to topic " + "test");
        int i = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
            }
        }
    }
}
