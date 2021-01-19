import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../../shared/service/cliente.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NotificationService } from 'src/app/notification.service';
import { Router } from '@angular/router';
import { TipoDocumentoService } from 'src/app/feature/tipodocumento/shared/service/tipodocumento.service';

const LONGITUD_MINIMA_PERMITIDA_TEXTO = 7;
const LONGITUD_MAXIMA_PERMITIDA_TEXTO = 10;

@Component({
  selector: 'app-crear-cliente',
  templateUrl: './crear-cliente.component.html',
  styleUrls: ['./crear-cliente.component.scss']
})
export class CrearClienteComponent implements OnInit {
  clienteForm: FormGroup;
  public listaTipoDocumentos;
  constructor(protected clienteServices: ClienteService,
    private readonly router: Router,
    private readonly notificationService: NotificationService,
    protected tipoDocumentoService: TipoDocumentoService,
    ) { }

  ngOnInit() {
    this.construirFormularioCliente();

    this.tipoDocumentoService.consultar().subscribe((respuesta) => {
      this.listaTipoDocumentos = respuesta;    
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }
  crear() {
    if (!this.clienteForm.valid) {
      return;
    }
    this.clienteServices.guardar(this.clienteForm.value).subscribe((respuesta) => {
      console.log(respuesta);
      this.notificationService.success("Se creo el cliente de forma exitosa.");
      this.router.navigateByUrl('/cliente/listar');
    }, (error) => {
      
      this.notificationService.error(error.error.mensaje);
    });
  }

  private construirFormularioCliente() {
    this.clienteForm = new FormGroup({
      correo:new FormControl(),
      nombre: new FormControl('', [Validators.required]),
      direccion: new FormControl('', [Validators.required]),
      idTipoDocumento: new FormControl('', [Validators.required]),
      numeroDocumento: new FormControl('', [Validators.required]),
      telefono: new FormControl('', [Validators.required, Validators.minLength(LONGITUD_MINIMA_PERMITIDA_TEXTO),
                                                             Validators.maxLength(LONGITUD_MAXIMA_PERMITIDA_TEXTO)])
    });
  }
}