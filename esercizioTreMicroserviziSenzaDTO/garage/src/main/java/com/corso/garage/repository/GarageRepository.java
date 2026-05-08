package com.corso.garage.repository;

import com.corso.garage.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GarageRepository extends JpaRepository<Garage, Long> {

}
