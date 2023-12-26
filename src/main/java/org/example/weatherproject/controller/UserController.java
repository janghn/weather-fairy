package org.example.weatherproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.weatherproject.dto.User;
import org.example.weatherproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/userlist")
    public List<User> user(){
        return userService.getUserList();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/home")
    public String home(@ModelAttribute User user, HttpSession session){
        String user_id = user.getUser_id();
        String passwd = user.getPasswd();
        if (userService.authenticateUser(user_id,passwd)){
            session.setAttribute("loginUser",user_id);
            return "main";
        }else {
            return "login";
        }
    }
}
