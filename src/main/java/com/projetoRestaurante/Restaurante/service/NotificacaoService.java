package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.Notificacao;
import com.projetoRestaurante.Restaurante.entity.Order;
import com.projetoRestaurante.Restaurante.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final NotificationRepository notificationRepository;


    public Notificacao notificarPedidoCriado(Order order){
        Notificacao notificacao = Notificacao.builder()
                .destinatario(order.getCustomer())
                .titulo("pedido "+order.getId()+" recebido")
                .mensagem("O pedido esta sendo processado")
                .tipoNotificacao(Notificacao.TipoDeNotificacao.PEDIDO_CRIADO)
                .metadata("{\"pedidoId\":\"" + order.getId() + "\"}")
                .build();
        System.out.println("enviada para "+ order.getCustomer().getEmail());
        return notificationRepository.save(notificacao);
    }
}
