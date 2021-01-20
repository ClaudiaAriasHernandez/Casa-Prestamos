import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
import { Cliente } from '@shared/model/cliente';

@Injectable()
export class ClienteService {

  constructor(protected http: HttpService) {}

  public consultar() {
    return this.http.doGet<Cliente[]>(`${environment.endpoint}/clientes/`, this.http.optsName('consultar clientes'));
  }

  public guardar(cliente: Cliente) {
    return this.http.doPost<Cliente, boolean>(`${environment.endpoint}/clientes`, cliente,
                                                this.http.optsName('crear clientes'));
  }

  public actualizar(cliente: Cliente) {
    return this.http.doPut<Cliente, boolean>(`${environment.endpoint}/clientes/${cliente.id}`, cliente,
                                                this.http.optsName('actualizar clientes'));
  }

  public eliminar(cliente: Cliente) {
    return this.http.doDelete<boolean>(`${environment.endpoint}/clientes/${cliente.id}`,
                                                 this.http.optsName('eliminar clientes'));
  }
}
