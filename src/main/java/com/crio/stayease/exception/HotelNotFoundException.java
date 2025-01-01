package com.crio.stayease.exception;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String s) {
        super(s);
    }
}
