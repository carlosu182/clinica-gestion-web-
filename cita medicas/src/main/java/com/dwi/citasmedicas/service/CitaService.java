package com.dwi.citasmedicas.service;

import com.dwi.citasmedicas.model.Cita;
import com.dwi.citasmedicas.model.EstadoCita;
import com.dwi.citasmedicas.repository.CitaRepository;
import com.dwi.citasmedicas.repository.MedicoRepository;
import com.dwi.citasmedicas.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public CitaService(CitaRepository citaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> buscarPorPaciente(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId);
    }

    public List<Cita> buscarPorMedico(Long medicoId) {
        return citaRepository.findByMedicoId(medicoId);
    }

    public Cita guardar(Cita cita) {
        if (cita.getPaciente() != null && cita.getPaciente().getId() != null) {
            cita.setPaciente(pacienteRepository.findById(cita.getPaciente().getId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado")));
        }
        if (cita.getMedico() != null && cita.getMedico().getId() != null) {
            cita.setMedico(medicoRepository.findById(cita.getMedico().getId())
                    .orElseThrow(() -> new RuntimeException("Médico no encontrado")));
        }
        if (cita.getEstado() == null) {
            cita.setEstado(EstadoCita.PROGRAMADA);
        }
        return citaRepository.save(cita);
    }

    public Cita actualizar(Long id, Cita datos) {
        return citaRepository.findById(id).map(cita -> {
            cita.setFechaHora(datos.getFechaHora());
            if (datos.getEstado() != null) {
                cita.setEstado(datos.getEstado());
            }
            if (datos.getPaciente() != null && datos.getPaciente().getId() != null) {
                cita.setPaciente(pacienteRepository.findById(datos.getPaciente().getId())
                        .orElseThrow(() -> new RuntimeException("Paciente no encontrado")));
            }
            if (datos.getMedico() != null && datos.getMedico().getId() != null) {
                cita.setMedico(medicoRepository.findById(datos.getMedico().getId())
                        .orElseThrow(() -> new RuntimeException("Médico no encontrado")));
            }
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }

    public Cita cambiarEstado(Long id, EstadoCita nuevoEstado) {
        return citaRepository.findById(id).map(cita -> {
            cita.setEstado(nuevoEstado);
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}
