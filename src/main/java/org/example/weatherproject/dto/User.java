package org.example.weatherproject.dto;

import lombok.Getter;
import lombok.Setter;

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

}
