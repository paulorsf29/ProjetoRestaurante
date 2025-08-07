package com.projetoRestaurante.Restaurante.repository;

import com.projetoRestaurante.Restaurante.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notificacao, UUID> {
    List<Notificacao> findByDestinatarioIdOrderByCriadoEmDesc(UUID userId);

}
