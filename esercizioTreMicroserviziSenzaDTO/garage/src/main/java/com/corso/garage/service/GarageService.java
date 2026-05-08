package com.corso.garage.service;

import com.corso.garage.entity.Garage;
import com.corso.garage.repository.GarageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class GarageService {
    private final GarageRepository garageRepository;
    private final VeicoloValidator veicoloValidator;

    public GarageService(GarageRepository garageRepository, VeicoloValidator veicoloValidator) {
        this.garageRepository = garageRepository;
        this.veicoloValidator = veicoloValidator;
    }

    public List<Garage> getAllGarage(){
        return garageRepository.findAll();
    }

    public Optional<Garage> getGarageById(Long id){
        return garageRepository.findById(id);
    }

    public Garage saveGarage(Garage garage){
        garage.getVeicoli().forEach(v -> {
            if (!veicoloValidator.exists(v.getTipoVeicolo(), v.getIdEsterno())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veicolo non esistente");
            }
            v.setGarage(garage);
        });

        return garageRepository.save(garage);
    }

    public void deleteGarage(Long id){
        garageRepository.deleteById(id);
    }

    public Garage updateGarage(Long id, Garage updatedGarage){
        return garageRepository.findById(id)
                .map(existing -> {
                    existing.setProprietario(updatedGarage.getProprietario());
                    existing.setIndirizzo(updatedGarage.getIndirizzo());
                    return garageRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Garage not found: " + id));
    }

}
