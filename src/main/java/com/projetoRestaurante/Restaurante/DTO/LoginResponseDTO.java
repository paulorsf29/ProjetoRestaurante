package com.projetoRestaurante.Restaurante.DTO;

import com.projetoRestaurante.Restaurante.entity.User;
import lombok.*;

import lombok.Builder;
import lombok.Data;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResponseDTO {
    private boolean sucesso;
    private String mensagem;
    private User.Role role;
    private String token;
    private User user;
}
