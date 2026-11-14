
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

interface Paciente {
  id: number;
  nombres: string;
  apellidos: string;
  dni: string;
  telefono: string;
  correo: string;
  fechaRegistro: string;
  estado: 'Activo' | 'Inactivo';
}

@Component({
  selector: 'app-pacientes',
  imports: [FormsModule],
  templateUrl: './pacientes.html',
  styleUrl: './pacientes.css'
})
export class Pacientes {

  terminoBusqueda = '';
  mostrarFormulario = false;
  mensajeError = '';

  nuevoPaciente = {
    nombres: '',
    apellidos: '',
    dni: '',
    telefono: '',
    correo: '',
    estado: 'Activo' as 'Activo' | 'Inactivo'
  };

  pacientes: Paciente[] = [
    {
      id: 1,
      nombres: 'María',
      apellidos: 'García López',
      dni: '74125896',
      telefono: '987654321',
      correo: 'maria.garcia@gmail.com',
      fechaRegistro: '14/11/2026',
      estado: 'Activo'
    },
    {
      id: 2,
      nombres: 'José',
      apellidos: 'Pérez Ramírez',
      dni: '70345612',
      telefono: '965432187',
      correo: 'jose.perez@gmail.com',
      fechaRegistro: '12/11/2026',
      estado: 'Activo'
    },
    {
      id: 3,
      nombres: 'Laura',
      apellidos: 'Mendoza Torres',
      dni: '72894561',
      telefono: '956123478',
      correo: 'laura.mendoza@gmail.com',
      fechaRegistro: '10/11/2026',
      estado: 'Activo'
    },
    {
      id: 4,
      nombres: 'Andrés',
      apellidos: 'Ramírez Díaz',
      dni: '71563248',
      telefono: '978456123',
      correo: 'andres.ramirez@gmail.com',
      fechaRegistro: '08/11/2026',
      estado: 'Inactivo'
    },
    {
      id: 5,
      nombres: 'Sofía',
      apellidos: 'Castillo Flores',
      dni: '75412369',
      telefono: '943215678',
      correo: 'sofia.castillo@gmail.com',
      fechaRegistro: '05/11/2026',
      estado: 'Activo'
    }
  ];

  get pacientesFiltrados(): Paciente[] {
    const busqueda = this.terminoBusqueda.toLowerCase().trim();

    return this.pacientes.filter(paciente =>
      `${paciente.nombres} ${paciente.apellidos}`
        .toLowerCase()
        .includes(busqueda) ||
      paciente.dni.includes(busqueda)
    );
  }

  get totalPacientes(): number {
    return this.pacientes.length;
  }

  get pacientesActivos(): number {
    return this.pacientes.filter(
      paciente => paciente.estado === 'Activo'
    ).length;
  }

  get nuevosEsteMes(): number {
    const ahora = new Date();

    return this.pacientes.filter(paciente => {
      const [dia, mes, anio] =
        paciente.fechaRegistro.split('/').map(Number);

      return mes === ahora.getMonth() + 1 &&
             anio === ahora.getFullYear();
    }).length;
  }

  abrirFormulario(): void {
    this.mensajeError = '';
    this.mostrarFormulario = true;
  }

  cerrarFormulario(form?: NgForm): void {
    this.mostrarFormulario = false;
    this.mensajeError = '';

    this.nuevoPaciente = {
      nombres: '',
      apellidos: '',
      dni: '',
      telefono: '',
      correo: '',
      estado: 'Activo'
    };

    form?.resetForm(this.nuevoPaciente);
  }

  registrarPaciente(form: NgForm): void {

    this.mensajeError = '';

    if (form.invalid) {
      form.control.markAllAsTouched();
      return;
    }

    const dni = this.nuevoPaciente.dni.trim();

    const dniExiste = this.pacientes.some(
      paciente => paciente.dni === dni
    );

    if (dniExiste) {
      this.mensajeError =
        'Ya existe un paciente registrado con este DNI.';
      return;
    }

    const fecha = new Date();

    const fechaRegistro =
      `${String(fecha.getDate()).padStart(2, '0')}/` +
      `${String(fecha.getMonth() + 1).padStart(2, '0')}/` +
      `${fecha.getFullYear()}`;

    const paciente: Paciente = {
      id: Math.max(0, ...this.pacientes.map(p => p.id)) + 1,
      nombres: this.nuevoPaciente.nombres.trim(),
      apellidos: this.nuevoPaciente.apellidos.trim(),
      dni,
      telefono: this.nuevoPaciente.telefono.trim(),
      correo: this.nuevoPaciente.correo.trim(),
      fechaRegistro,
      estado: this.nuevoPaciente.estado
    };

    this.pacientes = [paciente, ...this.pacientes];

    this.terminoBusqueda = '';

    this.cerrarFormulario(form);
  }
}
