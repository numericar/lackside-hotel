package com.dailycodework.lackside.models;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_type", length = 100, nullable = false)
    private String roomType;

    @Column(name = "room_price", nullable = false)
    private BigDecimal roomPrice;

    @Column(name = "is_booking", nullable = false)
    private boolean isBooked = false;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<BookRoom> bookings;

    public Room() {
        this.bookings = new ArrayList<>();
    }

    public void addBooking(BookRoom booking) {
        if (this.bookings == null) {
            this.bookings = new ArrayList<>();
        } 

        booking.setRoom(this);
        this.isBooked = true;
        String confirmationCode = UUID.randomUUID().toString();
        booking.setBookingConfirmationCode(confirmationCode);
        this.bookings.add(booking);
    }
}
