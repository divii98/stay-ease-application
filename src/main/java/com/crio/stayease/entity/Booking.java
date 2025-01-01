package com.crio.stayease.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
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
}
