package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menu_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "preco", nullable = false)
    private Integer preco;

    @Column(name = "tempo_preparo", updatable = false)
    private Integer TempoPreparo;

    @Column(name = "disponibilidade", nullable = false)
    private boolean disponibilidade = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "menuitem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemFotos> fotos;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criado_em;

    @CreationTimestamp
    private LocalDateTime Updated_em;

}
