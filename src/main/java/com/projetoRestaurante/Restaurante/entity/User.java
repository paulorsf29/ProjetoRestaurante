package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "nome", length = 255, nullable = false)
    @NotBlank
    @NotNull
    private String nome;

    @Email
    @Column(name = "email", length = 255, nullable = false, unique = true)
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "senha", length = 255, nullable = false)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$")
    @NotBlank
    @Size(min = 6)
    private String senha;

    @Column(name = "telefone", length = 15, nullable = false, unique = true)
    @NotNull
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @CreationTimestamp
    @Column(name = "criado_Em", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_At")
    private LocalDateTime updated_at;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    public enum Role{
        ADMIN,
        CUSTOMER,
        KITCHEN
    }
    public User(){
    }
}
