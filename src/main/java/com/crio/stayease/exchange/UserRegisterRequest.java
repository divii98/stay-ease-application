package com.crio.stayease.exchange;

import com.crio.stayease.entity.Role;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank(message = "first name cannot be blank")
    String firstName;
    @NotBlank(message = "last name cannot be blank")
    String lastName;
    @NotBlank(message = "email cannot be blank")
    String email;
    @NotBlank(message = "password cannot be blank")
    String password;
    @JsonEnumDefaultValue
    Role role = Role.CUSTOMER;

}
