import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CrearTipoDocumentoComponent } from './components/crear-tipodocumento/crear-tipodocumento.component';
import { ListarTipoDocumentoComponent } from './components/listar-tipodocumento/listar-tipodocumento.component';
import { BorrarTipoDocumentoComponent } from './components/borrar-tipodocumento/borrar-tipodocumento.component';
import { ActualizarTipoDocumentoComponent } from './components/actualizar-tipodocumento/actualizar-tipodocumento.component';

import { TipoDocumentoComponent } from './components/tipodocumento/tipodocumento.component';


const routes: Routes = [
  {
    path: '',
    component: TipoDocumentoComponent,
    children: [
      {
        path: 'crear',
        component: CrearTipoDocumentoComponent
      },
      {
        path: 'listar',
        component: ListarTipoDocumentoComponent
      },
      {
        path: 'borrar',
        component: BorrarTipoDocumentoComponent
      },
      {
        path: 'actualizar',
        component: ActualizarTipoDocumentoComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TipoDocumentoRoutingModule { }
