package com.dwi.citasmedicas.service;

import com.dwi.citasmedicas.model.Especialidad;
import com.dwi.citasmedicas.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {

    private final EspecialidadRepository repository;

    public EspecialidadService(EspecialidadRepository repository) {
        this.repository = repository;
    }

    public List<Especialidad> listarTodas() {
        return repository.findAll();
    }

    public Optional<Especialidad> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Especialidad guardar(Especialidad especialidad) {
        return repository.save(especialidad);
    }

    public Especialidad actualizar(Long id, Especialidad datos) {
        return repository.findById(id).map(esp -> {
            esp.setNombre(datos.getNombre());
            return repository.save(esp);
        }).orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
