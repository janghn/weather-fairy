package org.example.weatherproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.weatherproject.dto.User;
import org.example.weatherproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);

    @GetMapping("/userlist")
    public List<User> user(){
        return userService.getUserList();
    }

    @PostMapping("/home")
    public String home(@ModelAttribute User user, HttpSession session){
        String USER_ID = user.getUSER_ID();
        String USER_PW = user.getUSER_PW();
        if (userService.authenticateUser(USER_ID,USER_PW)){
            logger.info("로그인 시작 : UserApiController == " +  USER_ID);
            session.setAttribute("loginUser",USER_ID);
            return "redirect:/main";
        }else {
            logger.info("로그인 실패 : 아이디 또는 비밀번호가 일치하지 않습니다 {} ", USER_ID);
            return "redirect:/login";
        }
    }

    @PostMapping("/join")
    public String userJoin(User user, Model model) {
        logger.info("회원가입 시작한다 {} ", user.getUSER_ID());

        return userService.userJoin(user, model);
    }

}
