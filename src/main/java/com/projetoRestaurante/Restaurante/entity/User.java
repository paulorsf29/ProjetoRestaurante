package com.projetoRestaurante.Restaurante.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.descriptor.jdbc.UUIDJdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", name = "id", nullable = false)
    @JdbcType(UUIDJdbcType.class)
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

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}