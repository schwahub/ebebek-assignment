package com.ebebek.assignment.service;

import com.ebebek.assignment.controller.UserCreationRequest;
import com.ebebek.assignment.model.User;
import java.io.Serializable;
import javax.validation.Valid;

public interface LoginService extends Serializable {

    User create(@Valid UserCreationRequest request);

    User currentUser();

}
