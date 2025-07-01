package com.projetoRestaurante.Restaurante.repository;

import com.projetoRestaurante.Restaurante.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    boolean existByname(String nome);
}
