import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
import { Prestamo } from '../model/prestamo';


@Injectable()
export class PrestamoService {

  constructor(protected http: HttpService) {}

  public consultar(prestamo: Prestamo) {
    return this.http.doGet<Prestamo[]>(`${environment.endpoint}/prestamos/tipoidentificacion/${prestamo.tipoIdentificacion}/numerodocumento/${prestamo.numeroDocumento}`, this.http.optsName('consultar prestamo'));
  }

 
  public guardar(prestamo: Prestamo) {
    return this.http.doPost<Prestamo, boolean>(`${environment.endpoint}/prestamos`, prestamo,
                                                this.http.optsName('crear prestamo'));
  } 

  public pagar(prestamo: Prestamo) {
    return this.http.doPost<Prestamo, boolean>(`${environment.endpoint}/prestamos/tipoidentificacion/${prestamo.tipoIdentificacion}/numerodocumento/${prestamo.numeroDocumento}`, prestamo,
                                                 this.http.optsName('Pagar prestamo '));
  }
}
