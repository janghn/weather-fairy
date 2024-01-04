package org.example.weatherproject.exception;

public class WeatherException extends RuntimeException{
    private String message;

    public WeatherException(String message) {
        this.message = message;
    }

    public String getMessage(){
        if (message == null || message.length() == 0){
            return "요청처리에 실패했습니다.";
        }
        return message;
    }
}
