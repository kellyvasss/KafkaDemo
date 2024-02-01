package com.kelly.demoTest.kafka;



import com.kelly.demoTest.data.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    private KafkaTemplate<String, User> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {

        LOGGER.info(String.format("Användare skickad! -> %s", user.getFirstName()));

        Message<User> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, "users").build();

        kafkaTemplate.send(message);
    }
}
