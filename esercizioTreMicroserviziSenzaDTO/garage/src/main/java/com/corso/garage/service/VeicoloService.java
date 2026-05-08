package com.corso.garage.service;

import com.corso.garage.entity.Garage;
import com.corso.garage.entity.Veicolo;
import com.corso.garage.repository.GarageRepository;
import com.corso.garage.repository.VeicoloRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VeicoloService {
    private final GarageRepository garageRepository;
    private final VeicoloRepository veicoloRepository;
    private final VeicoloValidator veicoloValidator;

    public VeicoloService(GarageRepository garageRepository, VeicoloRepository veicoloRepository, VeicoloValidator veicoloValidator) {
        this.garageRepository = garageRepository;
        this.veicoloRepository = veicoloRepository;
        this.veicoloValidator = veicoloValidator;
    }



    public Veicolo addVeicolo(Long garageId, Veicolo v) {
        if (!veicoloValidator.exists(v.getTipoVeicolo(), v.getIdEsterno())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veicolo " + v.getTipoVeicolo() + ":" + v.getTipoVeicolo() + " non esiste");
        }
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        v.setGarage(garage);
        return veicoloRepository.save(v);
    }

    public Optional<Veicolo> getVeicoloById(Long id){
        //return veicoloRepository.findById(id);
        return Optional.of(veicoloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public List<Veicolo> getAllVeicoli(){
        return veicoloRepository.findAll();
    }

    public void deleteVeicolo(Long id){
        Veicolo v = veicoloRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        veicoloRepository.delete(v);
    }

    public Veicolo updateVeicolo(Long id, Veicolo nuovoVeicolo){
        Veicolo veicoloEsistente = veicoloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!veicoloValidator.exists(nuovoVeicolo.getTipoVeicolo(), nuovoVeicolo.getIdEsterno())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veicolo non esistente");
        }
        veicoloEsistente.setTipoVeicolo(nuovoVeicolo.getTipoVeicolo());
        veicoloEsistente.setIdEsterno(nuovoVeicolo.getIdEsterno());
        return veicoloRepository.save(veicoloEsistente);
    }


}
