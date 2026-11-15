package com.dwi.citasmedicas.controller;

import com.dwi.citasmedicas.model.Medico;
import com.dwi.citasmedicas.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidad/{especialidadId}")
    public ResponseEntity<List<Medico>> listarPorEspecialidad(@PathVariable Long especialidadId) {
        return ResponseEntity.ok(service.buscarPorEspecialidad(especialidadId));
    }

    @PostMapping
    public ResponseEntity<Medico> crear(@RequestBody Medico medico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable Long id, @RequestBody Medico medico) {
        try {
            return ResponseEntity.ok(service.actualizar(id, medico));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
