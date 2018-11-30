package com.wuyunu.simplekafkaclient.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class SimpleKafkaProducer {
    private Properties props=new Properties();
    {
        props.put("bootstrap.servers", "wuyunu:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }
    public void produce(){
        Producer<String, String> producer = new KafkaProducer<>(props);
        Random random=new Random();
        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<>("TEST", Integer.toString(random.nextInt()), Integer.toString(random.nextInt())));
        System.out.println("Message sent successfully");
        producer.close();
    }
}
