package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Customer extends User {
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private list<Adress> adresslist;

    @OneToMany(mappedBy = "customer")
    private list<Order> orders;

    @OneToMany(mappedBy = "customer")
    private list<Review> reviews;

}
