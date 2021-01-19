import { Component, OnInit } from '@angular/core';
import { PrestamoService } from '../../shared/service/prestamo.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NotificationService } from 'src/app/notification.service';
import { Router } from '@angular/router';
import { TipoDocumentoService } from 'src/app/feature/tipodocumento/shared/service/tipodocumento.service';
import { ClienteService } from 'src/app/feature/cliente/shared/service/cliente.service';
import { Cliente } from '@cliente/shared/model/cliente';

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
  public clienteBuscado: Cliente;
 

  constructor(protected prestamoService: PrestamoService,
    private readonly router: Router,
    private readonly notificationService: NotificationService,
    protected tipoDocumentoService: TipoDocumentoService,
    protected clienteService: ClienteService,
    ) { }

  ngOnInit() {
    this.construirBuscarCliente();
    this.construirFormularioPrestamo();
    this.tipoDocumentoService.consultar().subscribe((respuesta) => {
      this.listaTipoDocumentos = respuesta;    
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }

  buscarCliente() {
    if (!this.findClienteForm.valid) {
      return;
    }
    this.clienteService.buscarCliente(this.findClienteForm.value).subscribe((respuesta) => {
      this.clienteBuscado = respuesta;
      let cliente: any = { ...respuesta };
      if (!!respuesta && !!respuesta.dtoTipoDocumento) {
        cliente = {
          ...respuesta,
          tipoIdentificacion: respuesta.dtoTipoDocumento.tipoIdentificacion,
        };
      }
      this.prestamoForm.patchValue(cliente);
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
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
      this.notificationService.success("Se creó el prestamo al cliente de forma exitosa.");
      this.router.navigateByUrl('/prestamo/listar');
    }, (error) => {
      
      this.notificationService.error(error.error.mensaje);
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
