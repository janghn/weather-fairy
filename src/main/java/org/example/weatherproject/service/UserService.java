package org.example.weatherproject.service;

import lombok.RequiredArgsConstructor;
import org.example.weatherproject.dao.UserMapper;
import org.example.weatherproject.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    public boolean authenticateUser(String user_id, String passwd){
        User user = userMapper.getUserByUserId(user_id);
        return user != null && user.getPasswd().equals(passwd);
    }


}
