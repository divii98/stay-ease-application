package com.crio.stayease.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String s) {
        super(s);
    }
}
