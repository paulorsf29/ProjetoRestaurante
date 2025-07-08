package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "area_delivery")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Area_delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "nome", length = 254)
    private String nome;

    @Column(name = "latitude_central", nullable = false)
    private double latitudeCentral;

    @Column(name = "longitude_central", nullable = false)
    private double longitudeCentral;

    @Column(name = "raio", nullable = false)
    @Max(10000)
    private double raio;

    @Column(name = "recebimento_delivery", nullable = false)
    private Integer recebimentoDelivery;

    @Column(name = "revebimento_hora", nullable = false)
    private Integer recebimentoHora;

}
