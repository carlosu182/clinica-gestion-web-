package com.dwi.citasmedicas.model;

import java.time.LocalDateTime;

public class Cita {
    private Long id;
    private LocalDateTime fechaHora;
    private EstadoCita estado = EstadoCita.PROGRAMADA;
    private Paciente paciente;
    private Medico medico;

    public Cita() {}

    public Cita(Long id, LocalDateTime fechaHora, EstadoCita estado, Paciente paciente, Medico medico) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado != null ? estado : EstadoCita.PROGRAMADA;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
}
