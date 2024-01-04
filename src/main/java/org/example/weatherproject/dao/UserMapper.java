package org.example.weatherproject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.weatherproject.dto.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUserList();

    User getUserByUserId(String USER_ID);

    int userJoin(User user);

    int userIdExists(String USER_ID);
}
