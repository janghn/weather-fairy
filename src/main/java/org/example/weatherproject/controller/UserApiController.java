package org.example.weatherproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.weatherproject.dto.User;
import org.example.weatherproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/userlist")
    public List<User> user(){
        return userService.getUserList();
    }

    /*@GetMapping("/login")
    public String login(){
        return "login";
    }*/

    @PostMapping("/home")
    public String home(@ModelAttribute User user, HttpSession session){
        String userId = user.getUserId();
        String userPw = user.getUserPw();
        if (userService.authenticateUser(userId,userPw)){
            session.setAttribute("loginUser",userId);
            return "main";
        }else {
            return "login";
        }
    }
}
