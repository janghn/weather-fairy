package org.example.weatherproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private long U_IDX;
    private String USER_ID;
    private String USER_PW;
    private String MY_LEVEL;
    private int MY_POINT;
    private String USER_NAME;
    private LocalDateTime CREATE_DATE;
    private LocalDateTime UPDATE_DATE;
    private int TRY_COUNT;
    private int CORRECT_COUNT;

    public void hashUSER_PW(){
        this.USER_PW = hashpassword(USER_PW);
        this.CREATE_DATE = LocalDateTime.now();
    }

    private String hashpassword(String USER_PW){
        return BCrypt.hashpw(USER_PW, BCrypt.gensalt());
    }

}
