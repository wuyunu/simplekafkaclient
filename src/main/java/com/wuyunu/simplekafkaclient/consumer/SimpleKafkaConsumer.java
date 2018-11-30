package com.wuyunu.simplekafkaclient.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class SimpleKafkaConsumer {
    private Properties props=new Properties();
    {
        props.put("bootstrap.servers", "wuyunu:9092");

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
        //consumer.subscribe(Collections.singletonList("TEST"));
        TopicPartition topicPartition = new TopicPartition("TEST", 0);
        consumer.assign(Arrays.asList(topicPartition));
        consumer.seek(topicPartition,0);
        System.out.println("Subscribed to topic " + "TEST");
        int i = 0;
        for(int j=0;j<1;j++){
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
                System.out.println("---------------------------------->");
            }
        }
        consumer.close();
    }
}
