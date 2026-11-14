
import { Component } from '@angular/core';

interface Cita {
  id: number;
  hora: string;
  paciente: string;
  iniciales: string;
  medico: string;
  especialidad: string;
  estado: 'Confirmada' | 'Pendiente' | 'Cancelada';
}

@Component({
  selector: 'app-citas',
  imports: [],
  templateUrl: './citas.html',
  styleUrl: './citas.css'
})
export class Citas {

  citas: Cita[] = [

    {
      id: 1,
      hora: '08:00 AM',
      paciente: 'María García López',
      iniciales: 'MG',
      medico: 'Dr. Carlos Torres',
      especialidad: 'Medicina General',
      estado: 'Confirmada'
    },

    {
      id: 2,
      hora: '09:00 AM',
      paciente: 'José Pérez Ramírez',
      iniciales: 'JP',
      medico: 'Dra. Ana López',
      especialidad: 'Cardiología',
      estado: 'Pendiente'
    },

    {
      id: 3,
      hora: '10:30 AM',
      paciente: 'Laura Mendoza Torres',
      iniciales: 'LM',
      medico: 'Dr. Pedro Ruiz',
      especialidad: 'Pediatría',
      estado: 'Confirmada'
    },

    {
      id: 4,
      hora: '11:00 AM',
      paciente: 'Andrés Ramírez Díaz',
      iniciales: 'AR',
      medico: 'Dra. Sofía Díaz',
      especialidad: 'Traumatología',
      estado: 'Cancelada'
    },

    {
      id: 5,
      hora: '12:30 PM',
      paciente: 'Sofía Castillo Flores',
      iniciales: 'SC',
      medico: 'Dra. Valeria Castro',
      especialidad: 'Ginecología',
      estado: 'Confirmada'
    },

    {
      id: 6,
      hora: '02:00 PM',
      paciente: 'Diego Flores Morales',
      iniciales: 'DF',
      medico: 'Dr. Miguel Herrera',
      especialidad: 'Medicina General',
      estado: 'Pendiente'
    }

  ];

}
