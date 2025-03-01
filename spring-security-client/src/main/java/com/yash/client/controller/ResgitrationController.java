package com.yash.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.client.entity.User;
import com.yash.client.event.RegistrationCompleteEvent;
import com.yash.client.model.UserModel;
import com.yash.client.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ResgitrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {

        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
            user,
            applicationUrl(request)
                    ));
                    return "Success";
                }
            
                private String applicationUrl(HttpServletRequest request) {
                 
                    return "http://" + 
                            request.getServerName() +
                             ":" + 
                             request.getServerPort() + 
                             request.getServletPath();
                }


}
