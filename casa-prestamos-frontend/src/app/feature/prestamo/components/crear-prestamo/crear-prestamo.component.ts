import { Component, OnInit } from '@angular/core';
import { PrestamoService } from '../../shared/service/prestamo.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

const LONGITUD_MINIMA_PERMITIDA_TEXTO = 3;
const LONGITUD_MAXIMA_PERMITIDA_TEXTO = 20;

@Component({
  selector: 'app-crear-prestamo',
  templateUrl: './crear-prestamo.component.html',
  styleUrls: ['./crear-prestamo.component.scss']
})
export class CrearPrestamoComponent implements OnInit {
  prestamoForm: FormGroup;
  constructor(protected prestamoService: PrestamoService) { }

  ngOnInit() {
    this.construirFormularioPrestamo();
  }

  cerar() {
    this.prestamoService.guardar(this.prestamoForm.value);
  }

  private construirFormularioPrestamo() {
    this.prestamoForm = new FormGroup({
      id: new FormControl('', [Validators.required]),
      descripcion: new FormControl('', [Validators.required, Validators.minLength(LONGITUD_MINIMA_PERMITIDA_TEXTO),
                                                             Validators.maxLength(LONGITUD_MAXIMA_PERMITIDA_TEXTO)])
    });
  }

}
