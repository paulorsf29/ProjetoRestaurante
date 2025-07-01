package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    private String imagenUrl;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private list<MenuItem> menuItems;

    @CreationTimestamp
    @Column(name = "criado_em",updatable = false)
    private LocalDateTime criado_Em;

    @UpdateTimestamp
    private LocalDateTime updated_at;
    
}
