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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    private User user;
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Test Hotel");
        hotel.setNumberOfRooms(5);


    }

    @Test
    void testBookHotel_Success() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@example.com");
        SecurityContextHolder.setContext(securityContext);
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(hotelRepository.existsById(1L)).thenReturn(true);
        when(hotelRepository.getReferenceById(1L)).thenReturn(hotel);

        String result = bookingService.bookHotel(1L);

        assertEquals("Hotel booked successfully", result);
        assertEquals(4, hotel.getNumberOfRooms());
        verify(bookingRepository, times(1)).save(any(Booking.class));
        verify(hotelRepository, times(1)).saveAndFlush(hotel);
    }

    @Test
    void testBookHotel_HotelNotFound() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@example.com");
        SecurityContextHolder.setContext(securityContext);
        when(hotelRepository.existsById(1L)).thenReturn(false);
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        assertThrows(HotelNotFoundException.class, () -> bookingService.bookHotel(1L));
    }

    @Test
    void testBookHotel_AllRoomsBooked() {
        hotel.setNumberOfRooms(0);
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(hotelRepository.existsById(1L)).thenReturn(true);
        when(hotelRepository.getReferenceById(1L)).thenReturn(hotel);

        assertThrows(AllRoomsBookedException.class, () -> bookingService.bookHotel(1L));
    }

    @Test
    void testCancelBooking_Success() {
        Booking booking = new Booking(hotel, user, BookingStatus.ACTIVE, LocalDateTime.now());
        booking.setId(1L);

        when(bookingRepository.existsById(1L)).thenReturn(true);
        when(bookingRepository.getReferenceById(1L)).thenReturn(booking);

        String result = bookingService.cancelBooking(1L);

        assertEquals("Booking Cancelled for given Id", result);
        assertEquals(6, hotel.getNumberOfRooms());
        assertEquals(BookingStatus.CANCELED, booking.getStatus());
        verify(bookingRepository, times(1)).saveAndFlush(booking);
        verify(hotelRepository, times(1)).saveAndFlush(hotel);
    }

    @Test
    void testCancelBooking_BookingNotFound() {
        when(bookingRepository.existsById(1L)).thenReturn(false);

        assertThrows(BookingNotFoundException.class, () -> bookingService.cancelBooking(1L));
    }
}
