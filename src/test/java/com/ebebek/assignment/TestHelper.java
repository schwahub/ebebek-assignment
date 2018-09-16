package com.ebebek.assignment;

import com.ebebek.assignment.controller.UserCreationRequest;

public class TestHelper {

    public static final String TEST_USER = "test-user";

    public static UserCreationRequest createUserCreationRequest() {
        UserCreationRequest creationRequest = new UserCreationRequest();
        creationRequest.setUserName(TEST_USER);
        creationRequest.setFirstName("Test");
        creationRequest.setLastName("User");
        creationRequest.setEmail("test@test.com");
        creationRequest.setPassword("Test@ebebek");
        creationRequest.setConfirmPassword("Test@ebebek");
        return creationRequest;
    }


}
