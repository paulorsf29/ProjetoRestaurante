package com.projetoRestaurante.Restaurante.controller;

import com.projetoRestaurante.Restaurante.entity.User;
import com.projetoRestaurante.Restaurante.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> resgistrarUsuario(@RequestBody User user){
        return ResponseEntity.ok(userService.usuarioRegistrado(user));
    }
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUserByRole(@PathVariable User.Role role){
        return ResponseEntity.ok(userService.listaUsuarioPorCategoria(role));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        return  ResponseEntity.ok(userService.buscarUserPorId(id).orElse(null));
    }

}
