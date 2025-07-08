package com.projetoRestaurante.Restaurante.repository;

import com.projetoRestaurante.Restaurante.entity.Area_delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AreaDeliveryRepository extends JpaRepository<Area_delivery, UUID> {
    @Query("SELECT a FROM Area_delivery a WHERE " +
            "6371 * acos(cos(radians(:latitude)) * cos(radians(a.latitudeCentral)) * " +
            "cos(radians(a.longitudeCentral) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(a.latitudeCentral))) <= a.raio")
    Optional<Area_delivery> findDeliveryAreaByCoordenates(@Param("latitude") double latitude, @Param("longitude") double longitude);

    List<Area_delivery> findByRaioLessThanEqual(double raioMaximo);

}
