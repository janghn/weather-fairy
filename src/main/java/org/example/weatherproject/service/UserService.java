package org.example.weatherproject.service;

import lombok.RequiredArgsConstructor;
import org.example.weatherproject.dao.UserMapper;
import org.example.weatherproject.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    public boolean authenticateUser(String USER_ID, String USER_PW){
        logger.info("로그인 시작: " +  USER_ID);
        User user = userMapper.getUserByUserId(USER_ID);
        logger.info("로그인 시작 한다 : " +  USER_ID);

        logger.info("로그인 시작 한다 !!!!!!!!: " +  (user != null && user.getUSER_PW().equals(USER_PW)));

        return user != null && user.getUSER_PW().equals(USER_PW);
    }

    public int userJoin(User user){
        try {
            user.setCREATE_DATE(LocalDateTime.now());

            return userMapper.userJoin(user);

        }catch (Exception e){
            logger.error("회원가입 중 오류 발생: " + e.getMessage());
            throw new RuntimeException("회원가입 중 오류 발생");
        }
    }

}
