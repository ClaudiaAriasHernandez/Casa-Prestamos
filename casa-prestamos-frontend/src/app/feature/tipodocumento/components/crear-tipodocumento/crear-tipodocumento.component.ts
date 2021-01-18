import { Component, OnInit } from '@angular/core';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

const LONGITUD_MINIMA_PERMITIDA_TEXTO = 5;
const LONGITUD_MAXIMA_PERMITIDA_TEXTO = 50;

@Component({
  selector: 'app-crear-tipodocumento',
  templateUrl: './crear-tipodocumento.component.html',
  styleUrls: ['./crear-tipodocumento.component.scss']
})
export class CrearTipoDocumentoComponent implements OnInit {
  tipodocumentoForm: FormGroup;
  constructor(protected tipoDocumentoService: TipoDocumentoService,
    private readonly router: Router
  ) { }

  ngOnInit() {
    this.construirFormularioTipoDocumento();
  }

  crear() {
    if (!this.tipodocumentoForm.valid) {
      return;
    }
    this.tipoDocumentoService.guardar(this.tipodocumentoForm.value).subscribe((respuesta) => {
      console.log(respuesta);
      this.router.navigateByUrl('/tipodocumento/listar');
    }, (error) => {
      console.log(error.error.mensaje);
    });
  }

  private construirFormularioTipoDocumento() {
    this.tipodocumentoForm = new FormGroup({
      tipoIdentificacion: new FormControl('', [Validators.required]),
      descripcion: new FormControl('', [Validators.required, Validators.minLength(LONGITUD_MINIMA_PERMITIDA_TEXTO),
                                                             Validators.maxLength(LONGITUD_MAXIMA_PERMITIDA_TEXTO)])
    });
  }

}