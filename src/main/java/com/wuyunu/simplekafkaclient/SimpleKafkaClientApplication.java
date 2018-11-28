package com.wuyunu.simplekafkaclient;

import com.wuyunu.simplekafkaclient.consumer.SimpleKafkaConsumer;
import com.wuyunu.simplekafkaclient.producer.SimpleKafkaProducer;

public class SimpleKafkaClientApplication {
    public static void main(String[] args) {
        SimpleKafkaProducer simpleKafkaProducer=new SimpleKafkaProducer();
        simpleKafkaProducer.produce();
        SimpleKafkaConsumer simpleKafkaConsumer=new SimpleKafkaConsumer();
        simpleKafkaConsumer.consume();
    }
}
