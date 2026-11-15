package com.dwi.citasmedicas.controller;

import com.dwi.citasmedicas.model.Especialidad;
import com.dwi.citasmedicas.service.EspecialidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final EspecialidadService service;

    public EspecialidadController(EspecialidadService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Especialidad>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Especialidad> crear(@RequestBody Especialidad especialidad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(especialidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> actualizar(@PathVariable Long id, @RequestBody Especialidad especialidad) {
        try {
            return ResponseEntity.ok(service.actualizar(id, especialidad));
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
