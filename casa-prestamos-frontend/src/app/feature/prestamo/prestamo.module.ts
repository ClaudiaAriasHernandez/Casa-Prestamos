import { NgModule } from '@angular/core';
import { PrestamoRoutingModule } from './prestamo-routing.module';
import { ListarPrestamoComponent } from './components/listar-prestamo/listar-prestamo.component';
import { CrearPrestamoComponent } from './components/crear-prestamo/crear-prestamo.component';
import { PagarPrestamoComponent } from './components/pagar-prestamo/pagar-prestamo.component';
import { PrestamoComponent } from './components/prestamo/prestamo.component';
import { SharedModule } from '@shared/shared.module';
import { PrestamoService } from './shared/service/prestamo.service';
import { MaterialModule } from 'src/app/material.module';

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
  providers: [PrestamoService]
})
export class PrestamoModule { }
