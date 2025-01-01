package com.crio.stayease.exchange;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class HotelAddRequest {
    @NotBlank(message = "Hotel name cannot be blank")
    String name;
    @NotBlank(message= "Location cannot be blank")
    String location;
    String description;
    @NotNull(message = "Number of Rooms required")
    Integer numberOfRooms;
}
