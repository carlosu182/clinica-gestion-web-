package com.dwi.citasmedicas.service;

import com.dwi.citasmedicas.model.Paciente;
import com.dwi.citasmedicas.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Paciente> buscarPorDni(String dni) {
        return repository.findByDni(dni);
    }

    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente actualizar(Long id, Paciente datos) {
        return repository.findById(id).map(pac -> {
            pac.setNombres(datos.getNombres());
            pac.setApellidos(datos.getApellidos());
            pac.setDni(datos.getDni());
            pac.setTelefono(datos.getTelefono());
            pac.setCorreo(datos.getCorreo());
            return repository.save(pac);
        }).orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
