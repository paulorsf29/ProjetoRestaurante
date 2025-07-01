package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.User;
import com.projetoRestaurante.Restaurante.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User usuarioRegistrado(User user){
        if (userRepository.existByemail(user.getEmail())){
            throw new RuntimeException("Email Ja Cadastrado");
        }
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }
    public List<User> listaUsuarioPorCategoria(User.Role role){
        return userRepository.findByRole(role);
    }
    Optional<User> buscarUserPorId(UUID id){
        return userRepository.findById(id);
    }
    public boolean verificarEmailExistente(String email){
        return userRepository.existByemail(email);
    }

}
