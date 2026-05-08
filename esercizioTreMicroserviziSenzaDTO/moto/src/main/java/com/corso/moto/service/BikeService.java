package com.corso.moto.service;

import com.corso.moto.entity.Bike;
import com.corso.moto.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    private final BikeRepository bikeRepo;

    public BikeService(BikeRepository bikeRepo){
        this.bikeRepo = bikeRepo;
    }

    public List<Bike> getAllBikes(){
        return bikeRepo.findAll(); // Select * from bikes;
    }

    public Optional<Bike> getBikeById(Long id){
        return bikeRepo.findById(id);
    }

    public Bike saveBike(Bike bike){
        return bikeRepo.save(bike);
    }

    public void deleteBike(Long id){
        bikeRepo.deleteById(id);
    }

    public Bike updateBike(Long id, Bike updatedBike){
        return bikeRepo.findById(id)
                .map(existingBike -> {
                    existingBike.setBrand(updatedBike.getBrand());
                    existingBike.setModello(updatedBike.getModello());
                    existingBike.setMotoreCC(updatedBike.getMotoreCC());
                    existingBike.setTipo(updatedBike.getTipo());
                    existingBike.setAnno(updatedBike.getAnno());
                    existingBike.setPrezzo(updatedBike.getPrezzo());
                    return bikeRepo.save(existingBike);
                })
                .orElseThrow(() -> new RuntimeException("Bike not found: " + id));
    }

}
