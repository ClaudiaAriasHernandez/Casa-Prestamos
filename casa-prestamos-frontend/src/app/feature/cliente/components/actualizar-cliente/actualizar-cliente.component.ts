import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../../shared/service/cliente.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NotificationService } from 'src/app/notification.service';
import { Router } from '@angular/router';
import { Cliente } from '@cliente/shared/model/cliente';
import { ConsultaClienteService } from '@shared/service/consulta-cliente.service';
import { ConsultaTipoDocumentoService } from '@shared/service/consulta-tipodocumento.service';

const LONGITUD_MINIMA_PERMITIDA_TEXTO = 3;
const LONGITUD_MAXIMA_PERMITIDA_TEXTO = 20;

@Component({
  selector: 'app-actualizar-cliente',
  templateUrl: './actualizar-cliente.component.html',
  styleUrls: ['./actualizar-cliente.component.scss']
})
export class ActualizarClienteComponent implements OnInit {
  clienteForm: FormGroup;
  public clienteBuscado: Cliente;
  findClienteForm: FormGroup;
  public listaTipoDocumentos;
  constructor(protected clienteServices: ClienteService,
              private readonly router: Router,
              private readonly notificationService: NotificationService,
              protected consultaTipoDocumentoService: ConsultaTipoDocumentoService,
              protected consultaClienteService: ConsultaClienteService,
     ) { }

  ngOnInit() {
    this.construirFormularioCliente();
    this.construirBuscarCliente();
    this.consultarTiposDocumentos();
  }
  consultarTiposDocumentos() {
    this.consultaTipoDocumentoService.consultar().subscribe((respuesta) => {
      this.listaTipoDocumentos = respuesta;
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  buscarCliente() {
    if (!this.findClienteForm.valid) {
      return;
    }
    this.consultaClienteService.buscarCliente(this.findClienteForm.value).subscribe((respuesta) => {
      this.clienteBuscado = respuesta;
      this.clienteForm.patchValue(respuesta);
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  actualizar() {
    if (!this.clienteForm.valid) {
      return;
    }
    const datosActualizar = {
      ...this.clienteBuscado,
      ...this.clienteForm.value
    };
    console.log(datosActualizar);
    this.clienteServices.actualizar(datosActualizar).subscribe((respuesta) => {
      console.log(respuesta);
      this.notificationService.success('Se actualizo el cliente de forma exitosa.');
      this.router.navigateByUrl('/cliente/listar');
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  private construirFormularioCliente() {
    this.clienteForm = new FormGroup({
      correo: new FormControl(),
      nombre: new FormControl({ value: '', disabled: true }, [Validators.required]),
      direccion: new FormControl('', [Validators.required]),
      idTipoDocumento: new FormControl({ value: '', disabled: true }, [Validators.required]),
      numeroDocumento: new FormControl({ value: '', disabled: true }, [Validators.required]),
      telefono: new FormControl('', [Validators.required, Validators.minLength(LONGITUD_MINIMA_PERMITIDA_TEXTO),
                                                             Validators.maxLength(LONGITUD_MAXIMA_PERMITIDA_TEXTO)])
    });
  }
  private construirBuscarCliente() {
    this.findClienteForm = new FormGroup({
      idTipoDocumento: new FormControl('', [Validators.required]),
      numeroDocumento: new FormControl('', [Validators.required])
    });
  }
}
