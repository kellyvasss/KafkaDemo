package com.kelly.demoTest.view;

import com.kelly.demoTest.data.entity.User;
import com.kelly.demoTest.kafka.JsonKafkaProducer;
import com.kelly.demoTest.kafka.controller.ConsumerGetData;
import com.kelly.demoTest.kafka.controller.JsonMessageController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.kafka.core.KafkaTemplate;

@Route(value = "new-user")
public class AddUserView extends VerticalLayout {
    JsonMessageController controller;
    JsonKafkaProducer kafkaProducer;
    ConsumerGetData consumerGetData;
    KafkaTemplate<String, User> kafkaTemplate;
    User user;
    TextField firstName;
    TextField lastName;
    Button save;
    Button getKafkaData;
    Grid<User> userGrid;
    public AddUserView
                        (KafkaTemplate<String, User> kafkaTemplate,
                       JsonMessageController controller,
                       JsonKafkaProducer kafkaProducer,
                       ConsumerGetData consumerGetData)
    {

        firstName = new TextField("First Name");
        lastName = new TextField("Last Name");
        save = new Button("Save");
        getKafkaData = new Button("Data from Kafka");
        user = new User();
        userGrid = new Grid<>(User.class); // This binds the grid to the DB-structure for User-table (class)
        this.kafkaTemplate = kafkaTemplate;
        this.controller = controller;
        this.kafkaProducer = kafkaProducer;
        this.consumerGetData = consumerGetData;
        save.addClickListener(e -> saveUser());
        getKafkaData.addClickListener(e -> updateGrid());
        configureGrid();
        updateGrid();

        add(
                new HorizontalLayout(
                        new VerticalLayout(
                                firstName,
                                lastName,
                                save,
                                getKafkaData
                        ),
                        userGrid
                )

        );
    }

    private void updateGrid() {
        userGrid.setItems(consumerGetData.getData());
    }

    private void configureGrid() {
        userGrid.setColumns("firstName", "lastName");
        userGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void saveUser() {
        user.setFirstName(firstName.getValue());
        user.setLastName(lastName.getValue());
        controller.publish(user);
    }


}
