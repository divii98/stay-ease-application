package com.crio.stayease.exception;

public class ExistWithSameEmailException extends RuntimeException {
    public ExistWithSameEmailException(String s) {
        super(s);
    }
}
