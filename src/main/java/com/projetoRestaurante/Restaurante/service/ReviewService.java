package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.Order;
import com.projetoRestaurante.Restaurante.entity.Review;
import com.projetoRestaurante.Restaurante.repository.OrderRepository;
import com.projetoRestaurante.Restaurante.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public Review createReview(Review review, UUID customerId,UUID menuItemId){

        boolean fezPedido = orderRepository.existsByCustomerIdAndItems_MenuItemIdAndOrderStatus(customerId,menuItemId, Order.OrderStatus.ENTREGUE);
        if (!fezPedido){
            throw new RuntimeException("cliente não pode avaliar item que não foi comprado");
        }
        return reviewRepository.save(review);
    }
}
