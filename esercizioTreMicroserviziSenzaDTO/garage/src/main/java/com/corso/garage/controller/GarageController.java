package com.corso.garage.controller;

import com.corso.garage.entity.Garage;
import com.corso.garage.entity.Veicolo;
import com.corso.garage.service.GarageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("garage")
public class GarageController {
    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping
    public List<Garage> getAll() {
        return garageService.getAllGarage();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garage> getById(@PathVariable Long id){
        return garageService.getGarageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Garage> create(@RequestBody @Valid Garage garage){
        return ResponseEntity.status(201).body(garageService.saveGarage(garage));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Garage> update(@PathVariable Long id, @Valid @RequestBody Garage garage){
        return ResponseEntity.ok(garageService.updateGarage(id, garage));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        garageService.deleteGarage(id);
        return ResponseEntity.noContent().build();
    }

}
