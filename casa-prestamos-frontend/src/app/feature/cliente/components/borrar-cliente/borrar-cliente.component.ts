import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../../shared/service/cliente.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NotificationService } from 'src/app/notification.service';
import { Router } from '@angular/router';
import { Cliente } from '@cliente/shared/model/cliente';
import { ConsultaClienteService } from '@shared/service/consulta-cliente.service';
import { ConsultaTipoDocumentoService } from '@shared/service/consulta-tipodocumento.service';

@Component({
  selector: 'app-borrar-cliente',
  templateUrl: './borrar-cliente.component.html',
  styleUrls: ['./borrar-cliente.component.scss']
})
export class BorrarClienteComponent implements OnInit {
  clienteForm: FormGroup;
  public clienteBuscado: Cliente;
  findClienteForm: FormGroup;
  public listaTipoDocumentos;
  constructor(protected clienteServices: ClienteService,
              private readonly router: Router,
              private readonly notificationService: NotificationService,
              protected consultaClienteService: ConsultaClienteService,
              protected consultaTipoDocumentoService: ConsultaTipoDocumentoService,
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
    this.consultaClienteService.buscarCliente(this.findClienteForm.value).subscribe((respuesta) => {
      this.clienteBuscado = respuesta;
      this.clienteForm.patchValue(respuesta);
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  eliminar() {
    const datosActualizar = {
      ...this.clienteBuscado,
      ...this.clienteForm.value
    };
    this.clienteServices.eliminar(datosActualizar).subscribe((respuesta) => {
      console.log(respuesta);
      this.notificationService.success('Se elimino el cliente de forma exitosa.');
      this.router.navigateByUrl('/cliente/listar');
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  private construirFormularioCliente() {
    this.clienteForm = new FormGroup({
      correo: new FormControl({ value: '', disabled: true }),
      nombre: new FormControl({ value: '', disabled: true }, [Validators.required]),
      direccion: new FormControl({ value: '', disabled: true }, [Validators.required]),
      idTipoDocumento: new FormControl({ value: '', disabled: true }, [Validators.required]),
      numeroDocumento: new FormControl({ value: '', disabled: true }, [Validators.required]),
      telefono: new FormControl({ value: '', disabled: true }, [Validators.required])
    });
  }
  private construirBuscarCliente() {
    this.findClienteForm = new FormGroup({
      idTipoDocumento: new FormControl('', [Validators.required]),
      numeroDocumento: new FormControl('', [Validators.required])
    });
  }
}
