package com.corso.auto.controller;


import com.corso.auto.service.AutoService;
import com.corso.auto.entity.Automobile;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/automobili")
public class AutoController {
    private final AutoService autoService;

    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping
    public List<Automobile> getAll() {
        return autoService.getAllAutomobili();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automobile> getById(@PathVariable Long id){
        return autoService.getAutomobileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Automobile> create(@RequestBody @Valid Automobile automobile){
        return ResponseEntity.status(201).body(autoService.saveAutomobile(automobile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Automobile> update(@PathVariable Long id, @Valid @RequestBody Automobile automobile){
        return ResponseEntity.ok(autoService.updateAutomobile(id, automobile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        autoService.deleteAutomobile(id);
        return ResponseEntity.noContent().build();
    }

}
