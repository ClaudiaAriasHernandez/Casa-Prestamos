import { Component, OnInit } from '@angular/core';
import { PrestamoService } from '../../shared/service/prestamo.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NotificationService } from 'src/app/notification.service';
import { Router } from '@angular/router';
import { ConsultaClienteService } from '@shared/service/consulta-cliente.service';
import { ConsultaTipoDocumentoService } from '@shared/service/consulta-tipodocumento.service';

@Component({
  selector: 'app-crear-prestamo',
  templateUrl: './crear-prestamo.component.html',
  styleUrls: ['./crear-prestamo.component.scss']
})
export class CrearPrestamoComponent implements OnInit {
  public prestamoForm: FormGroup;
  public findClienteForm: FormGroup;
  public listaTipoDocumentos;
  public tipoDocumentoCliente;
  public clienteBuscado;

  constructor(protected prestamoService: PrestamoService,
              private readonly router: Router,
              private readonly notificationService: NotificationService,
              private readonly consultaTipoDocumentoService: ConsultaTipoDocumentoService,
              protected consultaClienteService: ConsultaClienteService,
    ) { }

  ngOnInit() {
    this.construirBuscarCliente();
    this.construirFormularioPrestamo();
    this.listarTiposDocumentos();
  }

  listarTiposDocumentos() {
    this.consultaTipoDocumentoService.consultar().subscribe((respuesta) => {
      this.listaTipoDocumentos = respuesta;
    });
  }

  buscarCliente() {
    if (!this.findClienteForm.valid) {
      return;
    }
    this.consultaClienteService.buscarCliente(this.findClienteForm.value).subscribe((respuesta) => {
      this.clienteBuscado = respuesta;
      let cliente: any = { ...respuesta };
      if (!!respuesta && !!respuesta.dtoTipoDocumento) {
        cliente = {
          ...respuesta,
          tipoIdentificacion: respuesta.dtoTipoDocumento.tipoIdentificacion,
        };
      }
      this.prestamoForm.patchValue(cliente);
    });
  }

  private construirBuscarCliente() {
    this.findClienteForm = new FormGroup({
      idTipoDocumento: new FormControl('', [Validators.required]),
      numeroDocumento: new FormControl('', [Validators.required])
    });
  }
  crear() {
    if (!this.prestamoForm.valid) {
      return;
    }
    const datosCrear = {
      idCliente: this.clienteBuscado.id,
      ...this.prestamoForm.value,
    };
    this.prestamoService.guardar(datosCrear).subscribe((respuesta) => {
      console.log(respuesta);
      this.notificationService.success('Se creo el prestamo al cliente de forma exitosa');
      this.router.navigateByUrl('/prestamo/listar');
    });
  }

  private construirFormularioPrestamo() {
    this.prestamoForm = new FormGroup({
      nombre: new FormControl({ value: '', disabled: true }),
      tipoIdentificacion: new FormControl({ value: '', disabled: true }),
      numeroDocumento: new FormControl({ value: '', disabled: true }),
      valor: new FormControl('', [Validators.required])
    });
  }
}
