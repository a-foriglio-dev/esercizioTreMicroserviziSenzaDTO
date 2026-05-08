package com.corso.moto.controller;

import com.corso.moto.entity.Bike;
import com.corso.moto.service.BikeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bikes")

public class BikeController {
    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<Bike> getAll(){
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable Long id){
        return bikeService.getBikeById(id)
                .map(ResponseEntity::ok)
                // map trasforma bike in 200 ok + json
                // orElse: restituisce 404 not found
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bike> create(@RequestBody @Valid Bike bike){
        return ResponseEntity.status(201).body(bikeService.saveBike(bike));
    }

    @PutMapping("/{id}")
    // @Valid controlla se i vincoli imposti in entity vengono rispettati
    // @RequestBody
    public ResponseEntity<Bike> update (@PathVariable Long id, @Valid @RequestBody Bike bike){
        return ResponseEntity.ok(bikeService.updateBike(id, bike));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bikeService.deleteBike(id);
        return ResponseEntity.noContent().build();
    }

}
