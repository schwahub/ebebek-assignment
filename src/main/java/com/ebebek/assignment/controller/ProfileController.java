package com.ebebek.assignment.controller;

import com.ebebek.assignment.model.User;
import com.ebebek.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @PostMapping("/user/profile")
    public ModelAndView home() {
        User user = userService.currentUser();
        ModelAndView mv = new ModelAndView("/user/profile");
        mv.getModel().put("user", user);
        return mv;
    }

}
