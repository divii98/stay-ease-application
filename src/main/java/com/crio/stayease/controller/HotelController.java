package com.crio.stayease.controller;

import com.crio.stayease.entity.Booking;
import com.crio.stayease.exchange.HotelAddRequest;
import com.crio.stayease.service.BookingService;
import com.crio.stayease.service.HotelService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/hotel")
public class HotelController {
    HotelService hotelService;
    BookingService bookingService;
    public HotelController(HotelService hotelService, BookingService bookingService){
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addHotelController(@Valid @RequestBody HotelAddRequest request){
        log.info("Hotel add request called with data: "+request);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.add(request));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteHotelController(@PathVariable long id){
        log.info("Hotel delete request called for Id: "+id);
        hotelService.deleteHotel(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotelControlller(@Valid @RequestBody HotelAddRequest request, @PathVariable long id){
        log.info("Hotel update request called for Id: "+id + "with body{}: "+request);
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.updateHotel(id,request));
    }

    @PostMapping("/{hotelId}/book")
    public ResponseEntity<String> hotelBookingController(@PathVariable Long hotelId){
        log.info("Hotel booking request called for Hotel: "+ hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.bookHotel(hotelId));
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<String> hotelBookingCancelController(@PathVariable Long bookingId){
        log.info("Hotel booking cancel request called for booking Id: "+ bookingId);
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.cancelBooking(bookingId));
    }

    @GetMapping("/bookings")
    public  ResponseEntity<List<Booking>> getAllBookings(){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
    }
}
