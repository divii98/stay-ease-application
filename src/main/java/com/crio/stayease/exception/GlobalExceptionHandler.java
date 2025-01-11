package com.crio.stayease.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExistWithSameEmailException.class)
    public ResponseEntity<Error> existWithSameNameExceptionHandler( ExistWithSameEmailException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Error> hotelNotFoundExceptionHandler( HotelNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(AllRoomsBookedException.class)
    public ResponseEntity<Error> allRoomsBookedExceptionHandler( AllRoomsBookedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Error> bookingNotFoundExceptionHandler( BookingNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
    }
}
