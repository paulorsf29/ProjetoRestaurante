package com.projetoRestaurante.Restaurante.DTO;

import com.projetoRestaurante.Restaurante.entity.Order;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ReportDTO {
    private final int totalPedidos;
    private final double valorTotal;
    private final Map<String, Long> pedidosPorStatus;
    private final Map<String, Double> faturamentoPorCategoria;

    public ReportDTO(List<Order> orders) {
        this.totalPedidos = orders.size();
        this.valorTotal = orders.stream()
                .mapToDouble(o -> o.getTotal() / 100.0)
                .sum();

        this.pedidosPorStatus = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.getOrderStatus().name(),
                        Collectors.counting()
                ));

        this.faturamentoPorCategoria = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getMenuItem().getCategory().getNome(),
                        Collectors.summingDouble(item -> item.getPreco_unitario() / 100.0 * item.getQuantidade())
                ));
    }
}