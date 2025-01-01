package com.crio.stayease.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, name = "first_name")
    String firstName;
    @Column(nullable = false, name = "last_name")
    String lastName;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Role role = Role.CUSTOMER;
}
