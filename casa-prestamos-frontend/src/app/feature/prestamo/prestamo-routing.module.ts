import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CrearPrestamoComponent } from './components/crear-prestamo/crear-prestamo.component';
import { ListarPrestamoComponent } from './components/listar-prestamo/listar-prestamo.component';
import { PagarPrestamoComponent } from './components/pagar-prestamo/pagar-prestamo.component';

import { PrestamoComponent } from './components/prestamo/prestamo.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: PrestamoComponent
  },
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
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestamoRoutingModule { }
