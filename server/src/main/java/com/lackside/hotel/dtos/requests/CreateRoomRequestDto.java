package com.lackside.hotel.dtos.requests;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoomRequestDto {
    private MultipartFile photo;
    private String roomType;
    private BigDecimal roomPrice;
}
