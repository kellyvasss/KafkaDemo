package com.kelly.demoTest.kafka.controller;

import com.kelly.demoTest.data.entity.User;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

@Service
public class ConsumerGetData {
    private final Properties properties = new Properties();
    private final Consumer<String, User> consumer;

    public ConsumerGetData() {

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "fetch");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put("spring.json.trusted.packages", "*");
        consumer = new KafkaConsumer<String, User>(properties);

        consumer.assign(Collections.singletonList(new TopicPartition("users", 0)));

    }

    public ArrayList<User> getData() {

        consumer.seekToBeginning(consumer.assignment());
        ArrayList<User> users = new ArrayList<>();

        while (true) {
            ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(100));

            if(records.isEmpty()) continue;
            for(ConsumerRecord<String, User> record : records) {
                users.add(record.value());
            } break;
        } return users;
    }



}
