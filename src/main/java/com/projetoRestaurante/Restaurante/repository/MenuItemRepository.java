package com.projetoRestaurante.Restaurante.repository;

import com.projetoRestaurante.Restaurante.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    List<MenuItem> findByCategoryId(UUID categoryId);
    List<MenuItem> findByAvaibleTrue();

    @Query("SELECT mi FROM MenuItem mi WHERE LOWER(mi.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<MenuItem> acharPeloNome(@Param("query") String query);
}
