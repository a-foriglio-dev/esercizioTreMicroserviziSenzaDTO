package com.corso.moto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "bikes")
// Oppure @Data = @Getter + @Setter
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder  // fai in modo che non sia necessario scrivere new()

public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String modello;

    @Min(50)
    @Column(name = "motoreCC", nullable = false)
    private Integer motoreCC;

    @NotBlank
    private String tipo;

    @Min(1900)
    private Integer anno;

    @DecimalMin(value = "0.0") // il valore dev'essere >= 0
    private Double prezzo;

}
