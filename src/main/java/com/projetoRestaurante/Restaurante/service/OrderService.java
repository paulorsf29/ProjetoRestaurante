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
    private final NotificacaoService notificacaoService;

    public Order createOrder(Order order, UUID customerId){
        order.setCustomer(userService.buscarUserPorId(customerId).orElseThrow());
        order.setOrderStatus(order.getOrderStatus().PENDENTE);
        Order savedOrder = orderRepository.save(order);
        notificacaoService.notificarPedidoCriado(savedOrder);
        return savedOrder;
    }

    public Order updateOrderStatus(UUID orderId, Order.OrderStatus status){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

}
