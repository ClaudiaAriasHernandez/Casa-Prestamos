import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoDocumento } from '../model/tipodocumento';

@Injectable()
export class TipoDocumentoService {

  private readonly RUTA_BASE = environment.endpoint;

  constructor(protected http: HttpService) {}

  public consultar(): Observable<TipoDocumento[]> {
    return this.http.doGet<TipoDocumento[]>(`${this.RUTA_BASE}/tipodocumentos/`);
  }
  
  public buscarTipoDocumento( tipoDocumento: TipoDocumento): Observable<TipoDocumento> {
    return this.http.doGet<TipoDocumento>(`${this.RUTA_BASE}/tipodocumentos/tipoidentificacion/${tipoDocumento.tipoIdentificacion}`);
  }

  public guardar(tipoDocumento: TipoDocumento) {
    return this.http.doPost<TipoDocumento, any>(`${this.RUTA_BASE}/tipodocumentos`, tipoDocumento);
  }

  public actualizar(tipoDocumento: TipoDocumento) {
    return this.http.doPut<TipoDocumento, boolean>(`${this.RUTA_BASE}/tipodocumentos/${tipoDocumento.id}`, tipoDocumento);
  }

  public eliminar(tipoDocumento: TipoDocumento) {
    return this.http.doDelete<boolean>(`${this.RUTA_BASE}/tipodocumentos/${tipoDocumento.id}`,
                                                 this.http.optsName('eliminar tipo documentos '));
  }
}
