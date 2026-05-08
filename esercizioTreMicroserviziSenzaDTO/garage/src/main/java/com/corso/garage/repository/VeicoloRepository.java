package com.corso.garage.repository;

import com.corso.garage.entity.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeicoloRepository extends JpaRepository<Veicolo, Long> {
}
