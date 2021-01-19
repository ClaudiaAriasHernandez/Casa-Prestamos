import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TipoDocumento } from '@tipodocumento/shared/model/tipodocumento';
import { NotificationService } from 'src/app/notification.service';

@Component({
  selector: 'app-borrar-tipodocumento',
  templateUrl: './borrar-tipodocumento.component.html',
  styleUrls: ['./borrar-tipodocumento.component.scss']
})
export class BorrarTipoDocumentoComponent implements OnInit {
  public documentoBuscado: TipoDocumento;
  tipodocumentoForm: FormGroup;
  findTipodocumentoForm: FormGroup;

  constructor(
    protected readonly tipodocumentoServices: TipoDocumentoService,
    private readonly router: Router,
    private readonly notificationService: NotificationService,
  ) { }

  ngOnInit() {
    this.construirBuscarTipoDocumento();
    this.construirFormularioTipoDocumento();
  }

  buscarTipoDocumento() {
    if (!this.findTipodocumentoForm.valid) {
      return;
    }
    this.tipodocumentoServices.buscarTipoDocumento(this.findTipodocumentoForm.value).subscribe((respuesta) => {
      this.documentoBuscado = respuesta;
      console.log(this.documentoBuscado);
      this.tipodocumentoForm.patchValue(respuesta);
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }  
  eliminar() {  

    const datosEliminar = {
      ...this.documentoBuscado,
      ...this.tipodocumentoForm.value
    };

    this.tipodocumentoServices.eliminar(datosEliminar).subscribe((respuesta) => {
      console.log(respuesta);
      this.notificationService.success("Se elimino el tipo de documento de forma exitosa.");
      this.router.navigateByUrl('/tipodocumento');
    }, (error) => {     
      this.notificationService.error(error.error.mensaje);
    });
  }
  private construirFormularioTipoDocumento() {
    this.tipodocumentoForm = new FormGroup({
      tipoIdentificacion: new FormControl({ value: '', disabled: true }),
      descripcion: new FormControl({ value: '', disabled: true })
    });
  }

private construirBuscarTipoDocumento() {
  this.findTipodocumentoForm = new FormGroup({
    tipoIdentificacion: new FormControl('', [Validators.required])
  });
}
}
