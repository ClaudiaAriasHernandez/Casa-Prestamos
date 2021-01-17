import { NgModule } from '@angular/core';

import { TipoDocumentoRoutingModule } from './tipodocumento-routing.module';
import { BorrarTipoDocumentoComponent } from './components/borrar-tipodocumento/borrar-tipodocumento.component';
import { ListarTipoDocumentoComponent } from './components/listar-tipodocumento/listar-tipodocumento.component';
import { CrearTipoDocumentoComponent } from './components/crear-tipodocumento/crear-tipodocumento.component';
import { ActualizarTipoDocumentoComponent } from './components/actualizar-tipodocumento/actualizar-tipodocumento.component';
import { TipoDocumentoComponent } from './components/tipodocumento/tipodocumento.component';
import { SharedModule } from '@shared/shared.module';
import { TipoDocumentoService } from './shared/service/tipodocumento.service';
import { MaterialModule } from 'src/app/material.module';

@NgModule({
  declarations: [
    CrearTipoDocumentoComponent,
    ListarTipoDocumentoComponent,
    BorrarTipoDocumentoComponent,
    ActualizarTipoDocumentoComponent,
    TipoDocumentoComponent,
  ],
  imports: [
    TipoDocumentoRoutingModule,
    SharedModule,
    MaterialModule
  ],
  providers: [TipoDocumentoService]
})
export class TipoDocumentoModule { }
