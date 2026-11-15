package com.dwi.citasmedicas.model;

public class Medico {
    private Long id;
    private String nombres;
    private String apellidos;
    private String cmp;
    private Especialidad especialidad;

    public Medico() {}

    public Medico(Long id, String nombres, String apellidos, String cmp, Especialidad especialidad) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cmp = cmp;
        this.especialidad = especialidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getCmp() { return cmp; }
    public void setCmp(String cmp) { this.cmp = cmp; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
}
