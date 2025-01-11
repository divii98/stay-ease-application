package com.crio.stayease.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "bookings")
@Data @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    Hotel hotel;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    User user;
    @Column(nullable = false,name = "booking_date")
    LocalDateTime bookingDate;
    @Column
    @Enumerated(EnumType.STRING)
    BookingStatus status;

    public Booking(Hotel hotel, User user, BookingStatus status, LocalDateTime bookingDate) {
        this.hotel = hotel;
        this.user = user;
        this.bookingDate = bookingDate;
        this.status = status;
    }
}
