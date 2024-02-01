package com.kelly.demoTest.view;

import com.kelly.demoTest.data.entity.User;
import com.kelly.demoTest.kafka.controller.ConsumerGetData;
import com.kelly.demoTest.kafka.controller.JsonMessageController;
import com.kelly.demoTest.kafka.JsonKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.springframework.kafka.core.KafkaTemplate;

class UserViewTest {

    @Mock
    private KafkaTemplate<String, User> kafkaTemplate;

    @Mock
    private JsonMessageController controller;

    @Mock
    private JsonKafkaProducer kafkaProducer;

    @Mock
    private ConsumerGetData consumerGetData;

    @InjectMocks
    private AddUserView addUserView;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void saveUser_ShouldCallControllerPublish() {
        User user = new User();

        user.setFirstName("John");
        user.setLastName("Doe");
        addUserView.firstName.setValue(user.getFirstName());
        addUserView.lastName.setValue(user.getLastName());

        addUserView.save.click();

        verify(controller, times(1)).publish(argThat(actualUser ->
                actualUser.getFirstName().equals(user.getFirstName()) &&
                        actualUser.getLastName().equals(user.getLastName())
        ));


    }


}