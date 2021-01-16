import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CrearPrestamoComponent } from './components/crear-/crear-prestamo.component';
import { ListarPrestamoComponent } from './components/listar-prestamo/listar-prestamo.component';
import { PagarPrestamoComponent } from './components/pagar-prestamo/pagar-prestamo.component';

import { PrestamoComponent } from './components/prestamo/prestamo.component';


const routes: Routes = [
  {
    path: '',
    component: PrestamoComponent,
    children: [
      {
        path: 'crear',
        component: CrearPrestamoComponent
      },
      {
        path: 'listar',
        component: ListarPrestamoComponent
      },
    
      {
        path: 'pagar',
        component: PagarPrestamoComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestamoRoutingModule { }
