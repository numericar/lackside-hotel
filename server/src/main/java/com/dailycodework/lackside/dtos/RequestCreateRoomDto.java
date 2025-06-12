package com.dailycodework.lackside.dtos;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateRoomDto {
    private MultipartFile photo;
    private String roomType;
    private BigDecimal roomPrice;
}
