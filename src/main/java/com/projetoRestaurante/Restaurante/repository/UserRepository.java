package com.projetoRestaurante.Restaurante.repository;

import com.projetoRestaurante.Restaurante.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserRepository> findByEmail(String email);
    boolean existByemail(String email);
    boolean existBytelefone(String telefone);
    List<User> findByRole(User.Role role);
}
