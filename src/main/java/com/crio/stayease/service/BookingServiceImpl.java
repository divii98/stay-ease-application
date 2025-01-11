package com.crio.stayease.service;

import com.crio.stayease.entity.Booking;
import com.crio.stayease.entity.BookingStatus;
import com.crio.stayease.entity.Hotel;
import com.crio.stayease.entity.User;
import com.crio.stayease.exception.AllRoomsBookedException;
import com.crio.stayease.exception.BookingNotFoundException;
import com.crio.stayease.exception.HotelNotFoundException;
import com.crio.stayease.repository.BookingRepository;
import com.crio.stayease.repository.HotelRepository;
import com.crio.stayease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Override
    @Transactional
    public String bookHotel(Long hotelId) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(userEmail).get();
        if(!hotelRepository.existsById(hotelId))
            throw new HotelNotFoundException("Hotel not found with given Id");
        Hotel hotel = hotelRepository.getReferenceById(hotelId);
        if(hotel.getNumberOfRooms()<1)
            throw new AllRoomsBookedException("No Rooms available for booking");
        hotel.setNumberOfRooms(hotel.getNumberOfRooms()-1);
        Booking booking = new Booking(hotel,user,BookingStatus.ACTIVE,LocalDateTime.now());
        bookingRepository.save(booking);
        hotelRepository.saveAndFlush(hotel);
        return "Hotel booked successfully";
    }

    @Override
    public String cancelBooking(Long bookingId) {
        if(!bookingRepository.existsById(bookingId))
            throw  new BookingNotFoundException("Booking not found with given Id");
        Booking booking = bookingRepository.getReferenceById(bookingId);
        Hotel hotel = booking.getHotel();
        hotel.setNumberOfRooms(hotel.getNumberOfRooms()+1);
        booking.setStatus(BookingStatus.CANCELED);
        bookingRepository.saveAndFlush(booking);
        hotelRepository.saveAndFlush(hotel);
        return "Booking Cancelled for given Id";
    }
}
