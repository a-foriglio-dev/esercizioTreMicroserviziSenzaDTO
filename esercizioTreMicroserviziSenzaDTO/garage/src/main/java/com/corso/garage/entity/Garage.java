package com.corso.garage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "garages", schema = "garage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String proprietario;

    @NotBlank
    private String indirizzo;

    // @OneToMany: un garage può avere molti veicoli
    // Ogni veicolo appartiene a un solo garage
    // mappedBy = "garage": la relazione è gestita dal campo garage che è nella classe Veicolo
    // CASCADE = CascadeType.ALL: tutte le operazioni del garage si riflettono anche nella classe Veicolo
    // orphanRemoval = true: se un Veicolo viene rimosso dalla lista veicoli, viene eliminato anche dal database
    // orphanRemoval = true: Hibernate cancella quel Veicolo dal DB
    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Veicolo> veicoli = new ArrayList<>();

}
