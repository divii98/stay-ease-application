package com.crio.stayease.service;

import com.crio.stayease.entity.Booking;

import java.util.List;

public interface BookingService {
    String bookHotel(Long hotelId);

    String cancelBooking(Long bookingId);

    List<Booking> getAllBookings();
}
