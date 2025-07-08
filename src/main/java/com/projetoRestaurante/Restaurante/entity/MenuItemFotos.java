package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "menu_item_fotos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuItemFotos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @Column(name = "featured", nullable = false)
    private boolean isFeatured;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    public MenuItemFotos(String imageUrl, boolean isFeatured, MenuItem menuItem) {
        this.imageUrl = imageUrl;
        this.isFeatured = isFeatured;
        this.menuItem = menuItem;
    }
}
