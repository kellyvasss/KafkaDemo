package com.kelly.demoTest.kafka;

import com.kelly.demoTest.data.entity.User;
import com.kelly.demoTest.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
    @Autowired
    UserRepository userRepository;
    @KafkaListener(topics = "users", groupId = "myGroup")
    public void writeToDB(User user) {


        userRepository.save(user);

        LOGGER.info("Användare mottagen -> " + user.getFirstName());
    }
}
