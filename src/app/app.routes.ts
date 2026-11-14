
import { Routes } from '@angular/router';

// Layout
import { Layout } from './layout/layout';

// Módulos
import { Dashboard } from './pages/dashboard/dashboard';
import { Pacientes } from './pages/pacientes/pacientes';
import { Medicos } from './pages/medicos/medicos';
import { Especialidades } from './pages/especialidades/especialidades';
import { Citas } from './pages/citas/citas';

export const routes: Routes = [

  {
    path: '',
    component: Layout,

    children: [

      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },

      {
        path: 'dashboard',
        component: Dashboard
      },

      {
        path: 'pacientes',
        component: Pacientes
      },

      {
        path: 'medicos',
        component: Medicos
      },

      {
        path: 'especialidades',
        component: Especialidades
      },

      {
        path: 'citas',
        component: Citas
      }

    ]
  },

  {
    path: '**',
    redirectTo: 'dashboard'
  }

];
