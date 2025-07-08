package com.projetoRestaurante.Restaurante.repository;

import com.projetoRestaurante.Restaurante.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Override
    List<Review> findAll();

    List<Review> findByOrder_Id(UUID orderId);
    List<Review> findByMenuItemId(UUID menuItemId);
    Optional<Review> findByOrder_IdAndCustomer_Id(UUID orderId, UUID customerId);
    @Query("SELECT COALESCE(AVG(r.avaliacao), 0) FROM Review r WHERE r.menuItem.id = :menuItemId")
    Double calcularMediaAvaliacoesPorMenuItem(@Param("menuItemId") UUID menuItemId);
}
