package com.dwi.citasmedicas.repository;

import com.dwi.citasmedicas.model.Cita;
import com.dwi.citasmedicas.model.EstadoCita;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CitaRepository {
    private final Map<Long, Cita> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public CitaRepository(PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @PostConstruct
    public void init() {
        pacienteRepository.findById(1L).ifPresent(pac ->
            medicoRepository.findById(1L).ifPresent(med ->
                save(new Cita(null, LocalDateTime.of(2026, 11, 20, 10, 30), EstadoCita.PROGRAMADA, pac, med))
            )
        );
    }

    public List<Cita> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Cita> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Cita> findByPacienteId(Long pacienteId) {
        return storage.values().stream()
                .filter(c -> c.getPaciente() != null && pacienteId.equals(c.getPaciente().getId()))
                .collect(Collectors.toList());
    }

    public List<Cita> findByMedicoId(Long medicoId) {
        return storage.values().stream()
                .filter(c -> c.getMedico() != null && medicoId.equals(c.getMedico().getId()))
                .collect(Collectors.toList());
    }

    public Cita save(Cita cita) {
        if (cita.getId() == null) {
            cita.setId(idGenerator.getAndIncrement());
        }
        storage.put(cita.getId(), cita);
        return cita;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
