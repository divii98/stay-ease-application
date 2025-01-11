package com.crio.stayease.service;

public interface BookingService {
    String bookHotel(Long hotelId);

    String cancelBooking(Long bookingId);
}
