package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.DTO.ReportDTO;
import com.projetoRestaurante.Restaurante.entity.Order;
import com.projetoRestaurante.Restaurante.repository.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final OrderRepository orderRepository;

    public ReportDTO gerarRelatorioDeVendas(LocalDateTime inicio, LocalDateTime fim){
        if (inicio.isAfter(fim)){
            throw new IllegalArgumentException("data de inicio deve ser anterior a data final");
        }
        List<Order> pedidos = orderRepository.findOrdersBetweenDates(inicio.toLocalDate().atStartOfDay(), fim.toLocalDate().atTime(23,59,59));
        return new ReportDTO(pedidos);
    }
}
