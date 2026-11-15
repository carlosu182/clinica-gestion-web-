package com.dwi.citasmedicas.controller;

import com.dwi.citasmedicas.model.Usuario;
import com.dwi.citasmedicas.repository.UsuarioRepository;
import com.dwi.citasmedicas.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.get("username"), loginRequest.get("password")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken((UserDetails) authentication.getPrincipal());
        
        return ResponseEntity.ok(Map.of("token", jwt, "type", "Bearer"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario signUpRequest) {
        if (usuarioRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error: El nombre de usuario ya existe."));
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(signUpRequest.getUsername());
        usuario.setPassword(encoder.encode(signUpRequest.getPassword()));
        usuario.setRol(signUpRequest.getRol() != null ? signUpRequest.getRol() : "USER");

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado exitosamente."));
    }
}
