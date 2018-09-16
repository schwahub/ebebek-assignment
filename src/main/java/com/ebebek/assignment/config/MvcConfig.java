package com.ebebek.assignment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("com/ebebek/assignment");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/com/ebebek/assignment").setViewName("com/ebebek/assignment");
    }

}