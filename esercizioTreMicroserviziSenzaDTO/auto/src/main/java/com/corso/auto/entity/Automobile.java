package com.corso.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "automobili", schema = "auto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String modello;

    @NotBlank
    private String tipoCarburante;

    @Min(2)
    private Integer porte;

    @Min(2)
    private Integer sedili;

    @Min(1900)
    private Integer anno;

    @Min(0)
    private Double prezzo;

}
