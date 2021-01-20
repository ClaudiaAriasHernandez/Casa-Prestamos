import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoDocumento } from 'src/app/feature/tipodocumento/shared/model/tipodocumento';

@Injectable()
export class ConsultaTipoDocumentoService {

  private readonly RUTA_BASE = environment.endpoint;

  constructor(protected http: HttpService) {}

  public consultar(): Observable<TipoDocumento[]> {
    return this.http.doGet<TipoDocumento[]>(`${this.RUTA_BASE}/tipodocumentos/`);
  }
}
