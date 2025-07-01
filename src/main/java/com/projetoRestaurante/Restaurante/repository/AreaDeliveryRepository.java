package com.projetoRestaurante.Restaurante.repository;

import com.projetoRestaurante.Restaurante.entity.Area_delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AreaDeliveryRepository extends JpaRepository<Area_delivery, UUID> {
    @Query("SELECT da FROM DeliveryArea da WHERE " +
            "6371 * acos(cos(radians(:latitude)) * cos(radians(da.centerLatitude)) * " +
            "cos(radians(da.centerLongitude) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(da.centerLatitude))) <= da.radius")

    Optional<Area_delivery> findDeliveryAreaByCordenates(@Param("latitude") double latitude, @Param("longitude") double longitude);

}
