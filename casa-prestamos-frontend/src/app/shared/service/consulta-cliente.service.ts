import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
import { Cliente } from '@shared/model/cliente';
import { Observable } from 'rxjs';

@Injectable()
export class ConsultaClienteService {

  constructor(protected http: HttpService) {}

  public buscarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.doGet<Cliente>(
      `${environment.endpoint}/clientes/tipoidentificacion/${cliente.idTipoDocumento}/numerodocumento/${cliente.numeroDocumento}`
    );
  }
}
