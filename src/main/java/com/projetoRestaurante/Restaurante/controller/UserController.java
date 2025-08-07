package com.projetoRestaurante.Restaurante.controller;

import com.projetoRestaurante.Restaurante.DTO.LoginRequestDTO;
import com.projetoRestaurante.Restaurante.DTO.LoginResponseDTO;
import com.projetoRestaurante.Restaurante.entity.User;
import com.projetoRestaurante.Restaurante.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody User user) {
        try {
            LoginResponseDTO resposta = userService.registrarUsuario(user);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno no servidor");
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUserByRole(@PathVariable User.Role role) {
        return ResponseEntity.ok(userService.listaUsuarioPorCategoria(role));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.buscarUserPorId(id).orElse(null));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO resposta = userService.login(loginRequestDTO);
        if (resposta.isSucesso()) {
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resposta);
        }
    }
}