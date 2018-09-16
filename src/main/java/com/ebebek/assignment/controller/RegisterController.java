package com.ebebek.assignment.controller;

import com.ebebek.assignment.model.User;
import com.ebebek.assignment.service.LoginService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/register")
    public ModelAndView getData() {
        ModelAndView mv = new ModelAndView("register");
        mv.getModelMap().addAttribute("user", new UserCreationRequest());
        return mv;
    }

    @PostMapping(value = "/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserCreationRequest request,
            BindingResult result) {
        User user = null;
        if (!result.hasErrors()) {
            user = loginService.create(request);
        }
        return user == null ? "register" : "redirect:/login?register=true";
    }

}
