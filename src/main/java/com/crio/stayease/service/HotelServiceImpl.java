package com.crio.stayease.service;

import com.crio.stayease.entity.Hotel;
import com.crio.stayease.exception.HotelNotFoundException;
import com.crio.stayease.exchange.HotelAddRequest;
import com.crio.stayease.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

    HotelRepository hotelRepository;
    ModelMapper mapper;

    @Autowired
    HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public String add(HotelAddRequest request) {
        Hotel hotel = mapper.map(request, Hotel.class);
        hotelRepository.save(hotel);
        return "Hotel added successfully";
    }

    @Override
    public void deleteHotel(Long id) {
        if (hotelRepository.existsById(id))
            throw new HotelNotFoundException("Hotel not found with given Id");
        hotelRepository.deleteById(id);
    }

    @Override
    public String updateHotel(long id, HotelAddRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new HotelNotFoundException("Hotel not found with given Id"));
        hotel.setName(request.getName());
        hotel.setDescription(request.getDescription());
        hotel.setLocation(request.getLocation());
        hotel.setNumberOfRooms(request.getNumberOfRooms());
        hotelRepository.saveAndFlush(hotel);
        return "Hotel details updated for given Id";
    }
}
