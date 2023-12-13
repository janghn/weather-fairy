package org.example.weatherproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long id;
    private String user_id;
    private String passwd;
}
