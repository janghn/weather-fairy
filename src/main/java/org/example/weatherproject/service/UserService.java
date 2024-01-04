package org.example.weatherproject.service;

import lombok.RequiredArgsConstructor;
import org.example.weatherproject.dao.UserMapper;
import org.example.weatherproject.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /*
    *   사용자 조회
    */
    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    /*
     *   로그인
     */
    public boolean authenticateUser(String USER_ID, String USER_PW){

        User user = userMapper.getUserByUserId(USER_ID);

        return user != null && checkPassword(USER_PW, user.getUSER_PW());
    }

    /*
     *   비밀번호 암호화
     */
    private boolean checkPassword(String sessionPW, String userPW){
        return BCrypt.checkpw(sessionPW, userPW);
    }

    /*
     *   회원가입
     */
    public String userJoin(User user, Model model) {
        try {
            if (userIdExists(user)) {
                logger.info("중복 아이디 조회 결과 : {} ", userIdExists(user));
                // 중복 아이디인 경우 경고창을 띄우기 위한 메시지 생성
                String message = "이미 존재하는 아이디입니다.";
                // 메시지를 Model에 추가
                model.addAttribute("error", message);
                return "signup"; // 중복 아이디일 경우 회원가입 페이지로 이동
            } else {
                user.hashUSER_PW();
                userMapper.userJoin(user);
                return "redirect:/login";
            }
        } catch (Exception e) {
            logger.error("회원가입 중 오류 발생: " + e.getMessage());
            model.addAttribute("error", "회원가입 중 오류 발생");
            return "signup"; // 오류 발생 시 회원가입 페이지로 이동
        }
    }

    /*
     *  사용자 아이디 중복 체크
     */
    private boolean userIdExists(User user){
        return userMapper.userIdExists(user.getUSER_ID()) == 1;
    }

}
