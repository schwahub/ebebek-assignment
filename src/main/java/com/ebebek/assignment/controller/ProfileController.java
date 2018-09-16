package com.ebebek.assignment.controller;

import com.ebebek.assignment.model.User;
import com.ebebek.assignment.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    LoginService loginService;

    @PostMapping("/user/profile")
    public ModelAndView home() {
        User user = loginService.currentUser();
        ModelAndView mv = new ModelAndView("/user/profile");
        mv.getModel().put("user", user);
        return mv;
    }

}
