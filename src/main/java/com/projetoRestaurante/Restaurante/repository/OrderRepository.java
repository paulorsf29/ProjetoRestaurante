package com.projetoRestaurante.Restaurante.repository;

import com.projetoRestaurante.Restaurante.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByCustomerId(UUID customerId);
    List<Order> findTop5ByCustomerIdOrderByCreated_AtDesc(UUID customerId);
    List<Order> findByOrderStatus(Order.OrderStatus orderStatus);
    @Query("SELECT o FROM Order o WHERE o.created_At BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
    @Query("SELECT o FROM Order o WHERE o.orderType = 'DELIVERY' AND o.deliveryAddress.latitude BETWEEN :minLat AND :maxLat AND o.deliveryAddress.longitude BETWEEN :minLng AND :maxLng")
    List<Order> findDeliveryOrdersInArea(@Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLng") double minLng, @Param("maxLng") double maxLng);
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END " +
            "FROM Order o JOIN o.items oi " +
            "WHERE o.customer.id = :customerId " +
            "AND oi.menuItem.id = :menuItemId")
    boolean existsByCustomerIdAndMenuItemId(
            @Param("customerId") UUID customerId,
            @Param("menuItemId") UUID menuItemId
    );
}
