package com.dwi.citasmedicas.repository;

import com.dwi.citasmedicas.model.Especialidad;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EspecialidadRepository {
    private final Map<Long, Especialidad> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        save(new Especialidad(null, "Cardiología"));
        save(new Especialidad(null, "Pediatría"));
        save(new Especialidad(null, "Medicina General"));
    }

    public List<Especialidad> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Especialidad> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Especialidad save(Especialidad especialidad) {
        if (especialidad.getId() == null) {
            especialidad.setId(idGenerator.getAndIncrement());
        }
        storage.put(especialidad.getId(), especialidad);
        return especialidad;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
