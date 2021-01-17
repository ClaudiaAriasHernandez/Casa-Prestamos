import { Component, OnInit } from '@angular/core';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

const LONGITUD_MINIMA_PERMITIDA_TEXTO = 3;
const LONGITUD_MAXIMA_PERMITIDA_TEXTO = 20;

@Component({
  selector: 'app-crear-tipodocumento',
  templateUrl: './crear-tipodocumento.component.html',
  styleUrls: ['./crear-tipodocumento.component.scss']
})
export class CrearTipoDocumentoComponent implements OnInit {
  tipodocumentoForm: FormGroup;
  constructor(protected tipoDocumentoService: TipoDocumentoService) { }

  ngOnInit() {
    this.construirFormularioTipoDocumento();
  }

  crear() {
    this.tipoDocumentoService.guardar(this.tipodocumentoForm.value);
  }

  private construirFormularioTipoDocumento() {
    this.tipodocumentoForm = new FormGroup({
      id: new FormControl('', [Validators.required]),
      tipoIdentificacion: new FormControl('', [Validators.required, Validators.minLength(LONGITUD_MINIMA_PERMITIDA_TEXTO),
                                                             Validators.maxLength(LONGITUD_MAXIMA_PERMITIDA_TEXTO)])
    });
  }

}
