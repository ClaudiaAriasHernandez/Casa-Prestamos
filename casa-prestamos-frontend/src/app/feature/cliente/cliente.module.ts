import { NgModule } from '@angular/core';

import { ClienteRoutingModule } from './cliente-routing.module';
import { BorrarClienteComponent } from './components/borrar-cliente/borrar-cliente.component';
import { ListarClienteComponent } from './components/listar-cliente/listar-cliente.component';
import { CrearClienteComponent } from './components/crear-cliente/crear-cliente.component';
import { ActualizarClienteComponent } from './components/actualizar-cliente/actualizar-cliente.component';
import { ClienteComponent } from './components/cliente/cliente.component';
import { SharedModule } from '@shared/shared.module';
import { ClienteService } from './shared/service/cliente.service';
import { MaterialModule } from 'src/app/material.module';
import { NotificationService } from 'src/app/notification.service';
import { TipoDocumentoService } from 'src/app/feature/tipodocumento/shared/service/tipodocumento.service';

@NgModule({
  declarations: [
    CrearClienteComponent,
    ListarClienteComponent,
    BorrarClienteComponent,
    ActualizarClienteComponent,
    ClienteComponent,
  ],
  imports: [
    ClienteRoutingModule,
    SharedModule,
    MaterialModule
  ],
  providers: [ClienteService, NotificationService, TipoDocumentoService]
})
export class ClienteModule { }
