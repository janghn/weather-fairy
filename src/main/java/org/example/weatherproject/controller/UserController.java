package org.example.weatherproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.weatherproject.dto.User;
import org.example.weatherproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/userlist")
    public List<User> user(){
        return userService.getUserList();
    }
}
