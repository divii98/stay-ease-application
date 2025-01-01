package com.crio.stayease.controller;

import com.crio.stayease.exchange.HotelAddRequest;
import com.crio.stayease.service.HotelService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Log4j2
@RestController
@RequestMapping("/hotel")
public class HotelController {
    HotelService hotelService;
    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
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

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHotelControlller(@Valid @RequestBody HotelAddRequest request, @PathVariable long id){
        log.info("Hotel update request called for Id: "+id + "with body{}: "+request);
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.updateHotel(id,request));
    }
}
