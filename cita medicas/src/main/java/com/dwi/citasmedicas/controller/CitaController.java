package com.dwi.citasmedicas.controller;

import com.dwi.citasmedicas.model.Cita;
import com.dwi.citasmedicas.model.EstadoCita;
import com.dwi.citasmedicas.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Cita>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(service.buscarPorPaciente(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Cita>> listarPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(service.buscarPorMedico(medicoId));
    }

    @PostMapping
    public ResponseEntity<Cita> crear(@RequestBody Cita cita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        try {
            return ResponseEntity.ok(service.actualizar(id, cita));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Cita> cambiarEstado(@PathVariable Long id, @RequestParam EstadoCita estado) {
        try {
            return ResponseEntity.ok(service.cambiarEstado(id, estado));
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
