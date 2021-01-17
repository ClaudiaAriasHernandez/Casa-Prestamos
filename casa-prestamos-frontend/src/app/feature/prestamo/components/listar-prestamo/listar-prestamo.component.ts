import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { PrestamoService } from '@prestamo/shared/service/prestamo.service';
import { Prestamo } from '@prestamo/shared/model/prestamo';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-listar-prestamo',
  templateUrl: './listar-prestamo.component.html',
  styleUrls: ['./listar-prestamo.component.scss']
})
export class ListarPrestamoComponent implements OnInit {
  prestamoForm: FormGroup;
  public listaPrestamos: Observable<Prestamo[]>;

  constructor(protected prestamoService: PrestamoService) { }

  ngOnInit() {
    this.listaPrestamos = this.prestamoService.consultar(this.prestamoForm.value);
  }


 

}
