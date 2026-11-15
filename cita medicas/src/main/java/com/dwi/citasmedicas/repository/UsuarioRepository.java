package com.dwi.citasmedicas.repository;

import com.dwi.citasmedicas.model.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UsuarioRepository {
    private final Map<Long, Usuario> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        // Usuario por defecto listo para hacer login: admin / 123456
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        save(new Usuario(null, "admin", encoder.encode("123456"), "ADMIN"));
    }

    public Optional<Usuario> findByUsername(String username) {
        return storage.values().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(idGenerator.getAndIncrement());
        }
        storage.put(usuario.getId(), usuario);
        return usuario;
    }
}
