
import { Component } from '@angular/core';

interface Medico {
  id: number;
  nombre: string;
  especialidad: string;
  cmp: string;
  telefono: string;
  correo: string;
  horario: string;
  estado: string;
  iniciales: string;
  avatarBg: string;
  avatarColor: string;
}

@Component({
  selector: 'app-medicos',
  imports: [],
  templateUrl: './medicos.html',
  styleUrl: './medicos.css'
})
export class Medicos {

  medicos: Medico[] = [

    {
      id: 1,
      nombre: 'Carlos Torres',
      especialidad: 'Medicina General',
      cmp: '085421',
      telefono: '987654321',
      correo: 'carlos.torres@medicitas.com',
      horario: 'Lun - Vie | 08:00 - 14:00',
      estado: 'Disponible',
      iniciales: 'CT',
      avatarBg: '#e8f1ff',
      avatarColor: '#327dd1'
    },

    {
      id: 2,
      nombre: 'Ana López',
      especialidad: 'Cardiología',
      cmp: '092315',
      telefono: '965432187',
      correo: 'ana.lopez@medicitas.com',
      horario: 'Lun - Vie | 09:00 - 15:00',
      estado: 'En consulta',
      iniciales: 'AL',
      avatarBg: '#f0eaff',
      avatarColor: '#8968d5'
    },

    {
      id: 3,
      nombre: 'Pedro Ruiz',
      especialidad: 'Pediatría',
      cmp: '078632',
      telefono: '956123478',
      correo: 'pedro.ruiz@medicitas.com',
      horario: 'Lun - Sáb | 08:00 - 13:00',
      estado: 'Disponible',
      iniciales: 'PR',
      avatarBg: '#e3f7ef',
      avatarColor: '#279d78'
    },

    {
      id: 4,
      nombre: 'Sofía Díaz',
      especialidad: 'Traumatología',
      cmp: '091245',
      telefono: '978456123',
      correo: 'sofia.diaz@medicitas.com',
      horario: 'Lun - Vie | 14:00 - 20:00',
      estado: 'No disponible',
      iniciales: 'SD',
      avatarBg: '#fff0e3',
      avatarColor: '#db914c'
    },

    {
      id: 5,
      nombre: 'Valeria Castro',
      especialidad: 'Ginecología',
      cmp: '083521',
      telefono: '943215678',
      correo: 'valeria.castro@medicitas.com',
      horario: 'Lun - Vie | 08:00 - 16:00',
      estado: 'Disponible',
      iniciales: 'VC',
      avatarBg: '#ffe9ef',
      avatarColor: '#d66b91'
    },

    {
      id: 6,
      nombre: 'Miguel Herrera',
      especialidad: 'Medicina General',
      cmp: '087654',
      telefono: '944321567',
      correo: 'miguel.herrera@medicitas.com',
      horario: 'Lun - Sáb | 14:00 - 20:00',
      estado: 'En consulta',
      iniciales: 'MH',
      avatarBg: '#e8f1ff',
      avatarColor: '#327dd1'
    }

  ];

}
