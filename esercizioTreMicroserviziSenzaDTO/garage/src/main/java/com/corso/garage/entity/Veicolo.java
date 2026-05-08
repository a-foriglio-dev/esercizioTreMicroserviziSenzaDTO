package com.corso.garage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veicoli", schema = "garage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Veicolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idEsterno; // id della moto o auto, necessario per identificarla

    @NotNull
    private String tipoVeicolo;

    // @ManyToOne: molti 'Veicolo' possono appartenere a un solo Garage
    // @JoinColumn(name = "garage_id"): HIbernate capisce quale colonna usare nel db per creare il collegamento
    // Nel database la tabella vehicles:
    // id | id_esterno | tipo_veicolo | garage_id
    // garage_id: foreign key che punta alla tabella garage
    @ManyToOne
    @JoinColumn(name = "garage_id")
    @JsonIgnore // serve quando converti l'oggetto JSON  ed evita cicli infiniti
    private Garage garage;
}
