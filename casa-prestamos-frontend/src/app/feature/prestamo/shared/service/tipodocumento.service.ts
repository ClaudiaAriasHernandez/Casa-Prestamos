import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
import { TipoDocumento } from '../model/tipodocumento';


@Injectable()
export class TipoDocumentoService {

  constructor(protected http: HttpService) {}

  public consultar() {
    return this.http.doGet<TipoDocumento[]>(`${environment.endpoint}/tipodocumentos/`, this.http.optsName('consultar tipos de documentos'));
  }

  public guardar(tipoDocumento: TipoDocumento) {
    return this.http.doPost<TipoDocumento, boolean>(`${environment.endpoint}/tipodocumentos`, tipoDocumento,
                                                this.http.optsName('crear tipo documentos'));
  }

  public actualizar(tipoDocumento: TipoDocumento) {
    return this.http.doPost<TipoDocumento, boolean>(`${environment.endpoint}/tipodocumentos/${tipoDocumento.id}`, tipoDocumento,
                                                this.http.optsName('actualizar tipo documentos'));
  }

  public eliminar(tipoDocumento: TipoDocumento) {
    return this.http.doDelete<boolean>(`${environment.endpoint}/tipodocumentos/${tipoDocumento.id}`,
                                                 this.http.optsName('eliminar tipo documentos '));
  }
}
