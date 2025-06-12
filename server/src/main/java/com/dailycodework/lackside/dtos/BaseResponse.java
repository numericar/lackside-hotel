package com.dailycodework.lackside.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean isSuccess;
    private String message;
    private T data;
    private LocalDateTime responseDateTime = LocalDateTime.now();
    
    public BaseResponse(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }
}
