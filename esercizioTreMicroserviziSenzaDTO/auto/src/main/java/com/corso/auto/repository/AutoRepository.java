package com.corso.auto.repository;

import com.corso.auto.entity.Automobile;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Automobile, Long> {
}
