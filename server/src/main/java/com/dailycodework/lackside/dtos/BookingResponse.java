package com.dailycodework.lackside.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;
    private String guestFullName;
    private String guestEmail;
    private int numberOfAdults;
    private int numberOfChildren;
    private int totalNumberOfGuest;
    private String bookingConfirmationCode;

    public BookingResponse(Long id, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime,
            String bookingConfirmationCode) {
        this.id = id;
        this.checkInDateTime = checkInDateTime;
        this.checkOutDateTime = checkOutDateTime;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
