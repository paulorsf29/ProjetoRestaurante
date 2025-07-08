package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notificacao")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User destinatario;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Column(name = "mensagem", nullable = false, length = 255)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoDeNotificacao tipoNotificacao;

    @Column(name = "lida", nullable = false)
    private boolean lida = false;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "lida_em", nullable = false)
    private LocalDateTime lidaEm;

    @Column(name = "metadata", columnDefinition = "json")
    private String metadata;

    public enum TipoDeNotificacao{
        PEDIDO_CRIADO,
        PEDIDO_STATUS_ATUALIZADO,
        ENTREGA_EM_ANDAMENTO,
        SENHA_RESET,
        PROMOCAO,
        OUTROS
    }

}
