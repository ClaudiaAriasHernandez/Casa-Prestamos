import { NgModule } from '@angular/core';

import { TipoDocumentoRoutingModule } from './tipodocumento-routing.module';
import { BorrarTipoDocumentoComponent } from './components/borrar-tipodocumento/borrar-tipodocumento.component';
import { ListarTipoDocumentoComponent } from './components/listar-tipodocumento/listar-tipodocumento.component';
import { CrearTipoDocumentoComponent } from './components/crear-tipodocumento/crear-tipodocumento.component';
import { ActualizarTipoDocumentoComponent } from './components/actualizar-tipodocumento/actualizar-tipodocumento.component';
import { TipoDocumentoComponent } from './components/tipodocumento/tipodocumento.component';
import { SharedModule } from '@shared/shared.module';
import { TipoDocumentoService } from './shared/service/tipodocumento.service';


@NgModule({
  declarations: [
    CrearTipoDocumentoComponent,
    ListarTipoDocumentoComponent,
    BorrarTipoDocumentoComponent,
    ActualizarTipoDocumentoComponent,
    TipoDocumentoComponent
  ],
  imports: [
    TipoDocumentoRoutingModule,
    SharedModule
  ],
  providers: [TipoDocumentoService]
})
export class TipoDocumentoModule { }
