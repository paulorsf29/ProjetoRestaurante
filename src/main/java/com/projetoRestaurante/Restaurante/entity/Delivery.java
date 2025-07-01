package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Motoboy_Delivery")
@Getter
@Setter
public class Delivery extends User{

    @Enumerated(EnumType.STRING)
    private DriveStatus status;

    @OneToMany(mappedBy = "driver")
    private List<Order> deliveries;

    public enum DriveStatus {
        DISPONIVEL, INDISPONIVEL, EM_ENTREGA
    }

}

