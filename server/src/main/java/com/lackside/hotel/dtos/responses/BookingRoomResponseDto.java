package com.lackside.hotel.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRoomResponseDto {

    private Long id;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String guestName;
    private Integer numberOfAdult;
    private Integer numberOfChildren;
    private Integer totalNumberOfGuest;
    private String bookingConfirmationCode;

    public BookingRoomResponseDto(Long id, LocalDateTime checkInDate, LocalDateTime checkOutDate,
            String bookingConfirmationCode) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
