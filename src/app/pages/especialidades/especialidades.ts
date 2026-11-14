
import { Component } from '@angular/core';

interface Especialidad {
  id: number;
  nombre: string;
  codigo: string;
  descripcion: string;
  medicos: number;
  estado: string;
  colorFondo: string;
  colorTexto: string;
}

@Component({
  selector: 'app-especialidades',
  imports: [],
  templateUrl: './especialidades.html',
  styleUrl: './especialidades.css'
})
export class Especialidades {

  especialidades: Especialidad[] = [

    {
      id: 1,
      nombre: 'Medicina General',
      codigo: 'MG',
      descripcion: 'Atención médica integral y prevención de enfermedades.',
      medicos: 2,
      estado: 'Activa',
      colorFondo: '#e8f1ff',
      colorTexto: '#327dd1'
    },

    {
      id: 2,
      nombre: 'Cardiología',
      codigo: 'CA',
      descripcion: 'Diagnóstico y tratamiento de enfermedades cardiovasculares.',
      medicos: 1,
      estado: 'Activa',
      colorFondo: '#ffe9ef',
      colorTexto: '#d66b91'
    },

    {
      id: 3,
      nombre: 'Pediatría',
      codigo: 'PE',
      descripcion: 'Atención médica especializada en niños y adolescentes.',
      medicos: 1,
      estado: 'Activa',
      colorFondo: '#e3f7ef',
      colorTexto: '#279d78'
    },

    {
      id: 4,
      nombre: 'Traumatología',
      codigo: 'TR',
      descripcion: 'Atención de lesiones y enfermedades del sistema musculoesquelético.',
      medicos: 1,
      estado: 'Activa',
      colorFondo: '#fff0e3',
      colorTexto: '#db914c'
    },

    {
      id: 5,
      nombre: 'Ginecología',
      codigo: 'GI',
      descripcion: 'Atención integral de la salud reproductiva femenina.',
      medicos: 1,
      estado: 'Activa',
      colorFondo: '#f0eaff',
      colorTexto: '#8968d5'
    }

  ];

}
