import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { ActualizarClienteComponent } from './actualizar-cliente.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ClienteService } from '../../shared/service/cliente.service';
import { HttpService } from 'src/app/core/services/http.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

describe('ActualizarClienteComponent', () => {
  let component: ActualizarClienteComponent;
  let fixture: ComponentFixture<ActualizarClienteComponent>;
  let clienteService: ClienteService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActualizarClienteComponent ],
      imports: [
        CommonModule,
        HttpClientModule,
        RouterTestingModule,
        ReactiveFormsModule,
        FormsModule
      ],
      providers: [ClienteService, HttpService],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActualizarClienteComponent);
    component = fixture.componentInstance;
    clienteService = TestBed.inject(ClienteService);
    spyOn(clienteService, 'guardar').and.returnValue(
      of(true)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('formulario es invalido cuando esta vacio', () => {
    expect(component.clienteForm.valid).toBeFalsy();
  });

  it('Registrando cliente', () => {
    expect(component.clienteForm.valid).toBeFalsy();
    component.clienteForm.controls.id.setValue(2);
    component.clienteForm.controls.nombre.setValue("Sara")
    expect(component.clienteForm.valid).toBeTruthy();

    component.cerar();

    // Aca validamos el resultado esperado al enviar la petición
    // TODO adicionar expect
  });
});
