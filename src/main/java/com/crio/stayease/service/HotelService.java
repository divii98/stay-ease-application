package com.crio.stayease.service;

import com.crio.stayease.exchange.HotelAddRequest;

public interface HotelService {
    String add(HotelAddRequest request);

    void deleteHotel(Long id);

    String updateHotel(long id, HotelAddRequest request);
}
