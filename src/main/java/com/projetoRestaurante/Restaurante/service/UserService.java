package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.DTO.LoginRequestDTO;
import com.projetoRestaurante.Restaurante.DTO.LoginResponseDTO;
import com.projetoRestaurante.Restaurante.config.JwtService;
import com.projetoRestaurante.Restaurante.entity.User;
import com.projetoRestaurante.Restaurante.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificacaoService notificacaoService;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public LoginResponseDTO registrarUsuario(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        user.setSenha(passwordEncoder.encode(user.getSenha()));
        User savedUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(savedUser);

        return LoginResponseDTO.builder()
                .sucesso(true)
                .mensagem("Registro bem-sucedido")
                .role(savedUser.getRole())
                .token(jwtToken)
                .user(savedUser)
                .build();
    }

    public List<User> listaUsuarioPorCategoria(User.Role role) {
        return userRepository.findByRole(role);
    }

    public Optional<User> buscarUserPorId(UUID id) {
        return userRepository.findById(id);
    }

    public boolean verificarEmailExistente(String email) {
        return userRepository.existsByEmail(email);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequestDTO.getEmail());

        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(loginRequestDTO.getSenha(), user.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        if (!user.isAtivo()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário desativado");
        }

        String jwtToken = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .sucesso(true)
                .mensagem("Login bem-sucedido")
                .role(user.getRole())
                .token(jwtToken)
                .user(user)
                .build();
    }
}