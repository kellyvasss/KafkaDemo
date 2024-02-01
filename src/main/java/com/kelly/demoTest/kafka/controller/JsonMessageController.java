package com.kelly.demoTest.kafka.controller;

import com.kelly.demoTest.data.entity.User;
import com.kelly.demoTest.kafka.JsonKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new-user") // <- Skriv in antagligen samma route här som det skall bli till sidan där man kan fylla i uppgifter
public class JsonMessageController {
    private JsonKafkaProducer kafkaProducer;
    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user) {
        System.out.println("innuti controller-klassen ");
        kafkaProducer.sendMessage(user);
        System.out.println("Json meddelande skickat till topic");
        return ResponseEntity.ok("Json meddelande skickat till Topic :)");
    }
}
