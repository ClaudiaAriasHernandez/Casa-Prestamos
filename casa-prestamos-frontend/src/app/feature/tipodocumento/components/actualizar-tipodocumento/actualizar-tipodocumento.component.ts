import { Component, OnInit } from '@angular/core';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

const LONGITUD_MINIMA_PERMITIDA_TEXTO = 3;
const LONGITUD_MAXIMA_PERMITIDA_TEXTO = 20;

@Component({
  selector: 'app-actualizar-tipodocumento',
  templateUrl: './actualizar-tipodocumento.component.html',
  styleUrls: ['./actualizar-tipodocumento.component.scss']
})
export class ActualizarTipoDocumentoComponent implements OnInit {
  tipodocumentoForm: FormGroup;
  constructor(protected tipodocumentoServices: TipoDocumentoService) { }

  ngOnInit() {
    this.construirFormularioTipoDocumento();
  }

  cerar() {
    this.tipodocumentoServices.actualizar(this.tipodocumentoForm.value);
  }

  private construirFormularioTipoDocumento() {
    this.tipodocumentoForm = new FormGroup({
      id: new FormControl('', [Validators.required]),
      descripcion: new FormControl('', [Validators.required, Validators.minLength(LONGITUD_MINIMA_PERMITIDA_TEXTO),
                                                             Validators.maxLength(LONGITUD_MAXIMA_PERMITIDA_TEXTO)])
    });
  }

}
