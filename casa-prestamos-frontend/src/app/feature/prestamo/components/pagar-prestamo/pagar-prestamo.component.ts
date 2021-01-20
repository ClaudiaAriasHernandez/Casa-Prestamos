import { Component, OnInit } from '@angular/core';
import { PrestamoService } from '../../shared/service/prestamo.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/notification.service';
import { Prestamo } from '@prestamo/shared/model/prestamo';
import { ConsultaTipoDocumentoService } from '@shared/service/consulta-tipodocumento.service';

@Component({
  selector: 'app-pagar-prestamo',
  templateUrl: './pagar-prestamo.component.html',
  styleUrls: ['./pagar-prestamo.component.scss']
})
export class PagarPrestamoComponent implements OnInit {
 public prestamoForm: FormGroup;
 public findPrestamoForm: FormGroup;
 public prestamoBuscado: Prestamo;
 public listaTipoDocumentos;
  constructor(protected prestamoServices: PrestamoService,
              private readonly notificationService: NotificationService,
              protected consultaTipoDocumentoService: ConsultaTipoDocumentoService,
              private readonly router: Router,
 ) { }

  ngOnInit() {
    this.construirFormularioPrestamo();
    this.construirBuscarPrestamoCliente();
    this.consultarTiposDocumentos();
  }

  private consultarTiposDocumentos() {
    this.consultaTipoDocumentoService.consultar().subscribe((respuesta) => {
      this.listaTipoDocumentos = respuesta;
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  private generarDatos(respuesta: Prestamo, esFormulario: boolean): any {
    let prestamo: any = { ...respuesta };
    if (!!respuesta && !!respuesta.dtoCliente) {
      const dtoCliente = respuesta.dtoCliente;
      prestamo = {
        ...respuesta,
        nombre: dtoCliente.nombre,
      };
      if (esFormulario) {
        prestamo.numeroDocumento = dtoCliente.dtoTipoDocumento.tipoIdentificacion + ' - ' + dtoCliente.numeroDocumento;
      } else {
        prestamo.numeroDocumento = dtoCliente.numeroDocumento;
        prestamo.tipoIdentificacion = dtoCliente.dtoTipoDocumento.tipoIdentificacion;
      }
    }
    return prestamo;
  }
  buscarClientePrestamo() {
    if (!this.findPrestamoForm.valid) {
      return;
    }
    this.prestamoServices.consultarPorCliente(this.findPrestamoForm.value).subscribe((respuesta) => {
      this.prestamoBuscado = respuesta;
      const prestamo = this.generarDatos(respuesta, true);
      this.prestamoForm.patchValue(prestamo);
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  pagar() {
    if (!this.prestamoForm.valid) {
      return;
    }
    const datosPagar = this.generarDatos(this.prestamoBuscado, false);
    this.prestamoServices.pagar(datosPagar).subscribe((respuesta) => {
      console.log(respuesta);
      this.notificationService.success('Se realizo el pago dl prestamo del cliente de forma exitosa.');
      this.router.navigateByUrl('/prestamo/listar');
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  private construirFormularioPrestamo() {
    this.prestamoForm = new FormGroup({
      id: new FormControl(),
      nombre: new FormControl({ value: '', disabled: true }),
      numeroDocumento: new FormControl({ value: '', disabled: true }),
      valor: new FormControl({ value: '', disabled: true }),
      valorMora: new FormControl({ value: '', disabled: true }),
      valorInteres: new FormControl({ value: '', disabled: true }),
      valorRecargo: new FormControl({ value: '', disabled: true }),
      valorTotal: new FormControl({ value: '', disabled: true }),
      fechaSolicitud: new FormControl({ value: '', disabled: true }),
      fechaEstimadaPago: new FormControl({ value: '', disabled: true }),
      fechaPago: new FormControl({ value: '', disabled: true }),
    });
  }
  private construirBuscarPrestamoCliente() {
    this.findPrestamoForm = new FormGroup({
      tipoIdentificacion: new FormControl('', [Validators.required]),
      numeroDocumento: new FormControl('', [Validators.required])
    });
  }
}
