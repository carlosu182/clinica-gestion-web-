package com.dwi.citasmedicas.service;

import com.dwi.citasmedicas.model.Medico;
import com.dwi.citasmedicas.repository.EspecialidadRepository;
import com.dwi.citasmedicas.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;

    public MedicoService(MedicoRepository medicoRepository, EspecialidadRepository especialidadRepository) {
        this.medicoRepository = medicoRepository;
        this.especialidadRepository = especialidadRepository;
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public List<Medico> buscarPorEspecialidad(Long especialidadId) {
        return medicoRepository.findByEspecialidadId(especialidadId);
    }

    public Medico guardar(Medico medico) {
        if (medico.getEspecialidad() != null && medico.getEspecialidad().getId() != null) {
            medico.setEspecialidad(especialidadRepository.findById(medico.getEspecialidad().getId())
                    .orElseThrow(() -> new RuntimeException("Especialidad no encontrada")));
        }
        return medicoRepository.save(medico);
    }

    public Medico actualizar(Long id, Medico datos) {
        return medicoRepository.findById(id).map(med -> {
            med.setNombres(datos.getNombres());
            med.setApellidos(datos.getApellidos());
            med.setCmp(datos.getCmp());
            if (datos.getEspecialidad() != null && datos.getEspecialidad().getId() != null) {
                med.setEspecialidad(especialidadRepository.findById(datos.getEspecialidad().getId())
                        .orElseThrow(() -> new RuntimeException("Especialidad no encontrada")));
            }
            return medicoRepository.save(med);
        }).orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }
}
