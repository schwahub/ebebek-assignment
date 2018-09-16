package com.ebebek.assignment;

import com.ebebek.assignment.controller.UserCreationRequest;
import com.ebebek.assignment.service.UserService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        createTestUser();
    }

    @Transactional
    protected void createTestUser() {
        UserCreationRequest request = new UserCreationRequest();
        request.setUserName("mustafa.ergin");
        request.setEmail("info@mustafaergin.com");
        request.setFirstName("Mustafa");
        request.setLastName("Ergin");
        request.setPassword("Mustafa@ebebek");
        userService.create(request);
    }

}
