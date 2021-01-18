import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../../shared/service/cliente.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TipoDocumentoService } from 'src/app/feature/tipodocumento/shared/service/tipodocumento.service';
import { NotificationService } from 'src/app/notification.service';
import { Router } from '@angular/router';
import { Cliente } from '@cliente/shared/model/cliente';

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
     protected tipoDocumentoService: TipoDocumentoService,) { }

  ngOnInit() {
    this.construirFormularioCliente();
    this.construirBuscarCliente();

    this.tipoDocumentoService.consultar().subscribe((respuesta) => {
      this.listaTipoDocumentos = respuesta;    
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }

  buscarCliente() {
    this.clienteServices.buscarCliente(this.findClienteForm.value).subscribe((respuesta) => {
      console.log(respuesta);
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
      this.notificationService.success("Se elimino el cliente de forma exitosa.");
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
      numeroDocumento: new FormControl({ value: '', disabled: true },[Validators.required]),
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
