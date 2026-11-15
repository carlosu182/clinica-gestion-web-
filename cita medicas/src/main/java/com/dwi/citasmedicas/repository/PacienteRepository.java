package com.dwi.citasmedicas.repository;

import com.dwi.citasmedicas.model.Paciente;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PacienteRepository {
    private final Map<Long, Paciente> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        save(new Paciente(null, "María", "Torres", "45896321", "987654321", "maria@correo.com"));
        save(new Paciente(null, "Luis", "Ramírez", "71234568", "912345678", "luis@correo.com"));
    }

    public List<Paciente> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Paciente> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Optional<Paciente> findByDni(String dni) {
        return storage.values().stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst();
    }

    public Paciente save(Paciente paciente) {
        if (paciente.getId() == null) {
            paciente.setId(idGenerator.getAndIncrement());
        }
        storage.put(paciente.getId(), paciente);
        return paciente;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
