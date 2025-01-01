package com.crio.stayease.repository;

import com.crio.stayease.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hotel")
public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
