import { Component, OnInit } from '@angular/core';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TipoDocumento } from '@tipodocumento/shared/model/tipodocumento';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/notification.service';

const LONGITUD_MINIMA_PERMITIDA_TEXTO = 3;
const LONGITUD_MAXIMA_PERMITIDA_TEXTO = 20;

@Component({
  selector: 'app-actualizar-tipodocumento',
  templateUrl: './actualizar-tipodocumento.component.html',
  styleUrls: ['./actualizar-tipodocumento.component.scss']
})
export class ActualizarTipoDocumentoComponent implements OnInit {
  public documentoBuscado: TipoDocumento;
  tipodocumentoForm: FormGroup;
  findTipodocumentoForm: FormGroup;

  constructor(
    protected readonly tipodocumentoServices: TipoDocumentoService,
    private readonly router: Router,
    private readonly notificationService: NotificationService
  ) { }

  ngOnInit() {
    this.construirFormularioTipoDocumento();
    this.construirBuscarTipoDocumento();
  }

  buscarTipoDocumento() {
    if (!this.findTipodocumentoForm.valid) {
      return;
    }
    this.tipodocumentoServices.buscarTipoDocumento(this.findTipodocumentoForm.value).subscribe((respuesta) => {
      this.documentoBuscado = respuesta;
      this.tipodocumentoForm.patchValue(respuesta);
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  actualizar() {
    if (!this.tipodocumentoForm.valid) {
      return;
    }
    const datosActualizar = {
      ...this.documentoBuscado,
      ...this.tipodocumentoForm.value
    };
    this.tipodocumentoServices.actualizar(datosActualizar).subscribe((respuesta) => {
      console.log(respuesta);
      this.notificationService.success('Se actualizo de forma exitosa.');
      this.router.navigateByUrl('/tipodocumento/listar');
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  private construirFormularioTipoDocumento() {
    this.tipodocumentoForm = new FormGroup({
      tipoIdentificacion: new FormControl({ value: '', disabled: true }, [Validators.required]),
      descripcion: new FormControl(
        '',
        [
          Validators.required, Validators.minLength(LONGITUD_MINIMA_PERMITIDA_TEXTO),
          Validators.maxLength(LONGITUD_MAXIMA_PERMITIDA_TEXTO)
        ]
      )
    });
  }
  private construirBuscarTipoDocumento() {
    this.findTipodocumentoForm = new FormGroup({
      tipoIdentificacion: new FormControl('', [Validators.required])
    });
  }
}
