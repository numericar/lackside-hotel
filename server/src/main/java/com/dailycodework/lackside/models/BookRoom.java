package com.dailycodework.lackside.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_rooms")
public class BookRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "check_in_date_time")
    private LocalDateTime checkInDateTime;

    @Column(name = "check_out_date_time")
    private LocalDateTime checkOutDateTime;

    @Column(name = "guest_full_name", length = 100, nullable = false)
    private String guestFullName;

    @Column(name = "guest_email", length = 100, nullable = false)
    private String guestEmail;

    @Column(name = "number_of_adults")
    private int numberOfAdults;

    @Column(name = "number_of_children")
    private int numberOfChildren;
    
    @Column(name = "total_number_of_guest")
    private int totalNumberOfGuest;

    @Column(name = "booking_confirmation_code")
    private String bookingConfirmationCode;

    @JoinColumn(name = "room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    public void calculateNumberOfGuest() {
        this.totalNumberOfGuest = this.numberOfAdults + this.numberOfChildren;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;

        this.calculateNumberOfGuest();
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;

        this.calculateNumberOfGuest();
    }
}
