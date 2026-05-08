package com.corso.auto.service;

import com.corso.auto.entity.Automobile;
import com.corso.auto.repository.AutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    private final AutoRepository autoRepo;

    public AutoService(AutoRepository autoRepo) {
        this.autoRepo = autoRepo;
    }

    public List<Automobile> getAllAutomobili(){
        return autoRepo.findAll();
    }

    public Optional<Automobile> getAutomobileById(Long id){
        return autoRepo.findById(id);
    }

    public Automobile saveAutomobile(Automobile automobile){
        return autoRepo.save(automobile);
    }

    public void deleteAutomobile(Long id){
        autoRepo.deleteById(id);
    }

    public Automobile updateAutomobile(Long id, Automobile updatedAutomobile){
        return autoRepo.findById(id)
                .map(existingAutomobile -> {
                    existingAutomobile.setBrand(updatedAutomobile.getBrand());
                    existingAutomobile.setModello(updatedAutomobile.getModello());
                    existingAutomobile.setTipoCarburante(updatedAutomobile.getTipoCarburante());
                    existingAutomobile.setPorte(updatedAutomobile.getPorte());
                    existingAutomobile.setSedili(updatedAutomobile.getSedili());
                    existingAutomobile.setPrezzo(updatedAutomobile.getPrezzo());
                    return autoRepo.save(existingAutomobile);
                })
                .orElseThrow(() -> new RuntimeException("Automobile not found: " + id));
    }

}
