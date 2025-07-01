package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.Order;
import com.projetoRestaurante.Restaurante.repository.MenuItemRepository;
import com.projetoRestaurante.Restaurante.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserService userService;

    public Order createOrder(Order order, UUID customerId){
        order.setCustomer(userService.buscarUserPorId(customerId).orElseThrow());
        order.setOrderStatus(order.getOrderStatus().ESPERA);
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(UUID orderId, Order.OrderStatus status){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

}
