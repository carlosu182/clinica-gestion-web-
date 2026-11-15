package com.dwi.citasmedicas.repository;

import com.dwi.citasmedicas.model.Medico;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MedicoRepository {
    private final Map<Long, Medico> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final EspecialidadRepository especialidadRepository;

    public MedicoRepository(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @PostConstruct
    public void init() {
        especialidadRepository.findById(1L).ifPresent(esp ->
            save(new Medico(null, "Carlos", "Mendoza", "CMP-45123", esp))
        );
        especialidadRepository.findById(2L).ifPresent(esp ->
            save(new Medico(null, "Ana", "Gómez", "CMP-78945", esp))
        );
    }

    public List<Medico> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Medico> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Medico> findByEspecialidadId(Long especialidadId) {
        return storage.values().stream()
                .filter(m -> m.getEspecialidad() != null && especialidadId.equals(m.getEspecialidad().getId()))
                .collect(Collectors.toList());
    }

    public Medico save(Medico medico) {
        if (medico.getId() == null) {
            medico.setId(idGenerator.getAndIncrement());
        }
        storage.put(medico.getId(), medico);
        return medico;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
