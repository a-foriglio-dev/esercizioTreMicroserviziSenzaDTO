package com.corso.garage.controller;

import com.corso.garage.entity.Veicolo;
import com.corso.garage.service.VeicoloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veicoli")
public class VeicoloController {
    private final VeicoloService veicoloService;

    public VeicoloController(VeicoloService veicoloService) {
        this.veicoloService = veicoloService;
    }

    @GetMapping
    public List<Veicolo> getAll(){
        return veicoloService.getAllVeicoli();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veicolo> getById(@PathVariable Long id){
        return veicoloService.getVeicoloById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{garageId}")
    public ResponseEntity<Veicolo> create(@RequestBody @Valid Veicolo veicolo, @PathVariable Long garageId){
        return ResponseEntity.status(201).body(veicoloService.addVeicolo(garageId, veicolo));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Veicolo> update(@PathVariable Long id, @Valid @RequestBody Veicolo veicolo){
        return ResponseEntity.ok(veicoloService.updateVeicolo(id,veicolo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        veicoloService.deleteVeicolo(id);
        return ResponseEntity.noContent().build();
    }

}
