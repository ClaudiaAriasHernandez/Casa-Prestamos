import { NgModule } from '@angular/core';

import { PrestamoRoutingModule } from './prestamo-routing.module';
import { ListarPrestamoComponent } from './components/listar-prestamo/listar-prestamo.component';
import { CrearPrestamoComponent } from './components/crear-prestamo/crear-prestamo.component';
import { PagarPrestamoComponent } from './components/pagar-prestamo/pagar-prestamo.component';
import { PrestamoComponent } from './components/prestamo/prestamo.component';
import { SharedModule } from '@shared/shared.module';
import { PrestamoService } from './shared/service/prestamo.service';
import { NotificationService } from 'src/app/notification.service';
import { MaterialModule } from 'src/app/material.module';
import { ClienteService } from 'src/app/feature/cliente/shared/service/cliente.service';
import { TipoDocumentoService } from 'src/app/feature/tipodocumento/shared/service/tipodocumento.service';

@NgModule({
  declarations: [
    CrearPrestamoComponent,
    ListarPrestamoComponent,
    PagarPrestamoComponent,
    PrestamoComponent
  ],
  imports: [
    PrestamoRoutingModule,
    SharedModule,
    MaterialModule
  ],
  providers: [PrestamoService, NotificationService, ClienteService, TipoDocumentoService]
})
export class PrestamoModule { }
