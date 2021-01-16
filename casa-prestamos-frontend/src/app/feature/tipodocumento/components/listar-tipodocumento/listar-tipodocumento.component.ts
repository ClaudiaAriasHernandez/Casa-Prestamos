import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { TipoDocumentoService } from '@tipodocumento/shared/service/tipodocumento.service';
import { TipoDocumento } from '@tipodocumento/shared/model/tipodocumento';

@Component({
  selector: 'app-listar-tipodocumento',
  templateUrl: './listar-tipodocumento.component.html',
  styleUrls: ['./listar-tipodocumento.component.scss']
})
export class ListarTipoDocumentoComponent implements OnInit {
  public listaTipoDocumentos: Observable<TipoDocumento[]>;

  constructor(protected tipoDocumentoService: TipoDocumentoService) { }

  ngOnInit() {
    this.listaTipoDocumentos = this.tipoDocumentoService.consultar();
  }

}
