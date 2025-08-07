package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ordem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    @Column(name = "total", nullable = false)
    private Integer total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private OrderType orderType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id")
    private User driver;

    private String instrucoesAdicionais;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum OrderStatus {
        PENDENTE, EM_PREPARO, PRONTO_PARA_ENTREGA, SAIU_PARA_ENTREGA, ENTREGUE, CANCELADO
    }

    public enum OrderType {
        DELIVERY, PEGAR_NO_RESTAURANTE
    }
}
