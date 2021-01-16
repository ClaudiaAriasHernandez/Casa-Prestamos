import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { CrearClienteComponent } from './crear-cliente.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ClienteService } from '../../shared/service/cliente.service';
import { HttpService } from 'src/app/core/services/http.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

describe('CrearClienteComponent', () => {
  let component: CrearClienteComponent;
  let fixture: ComponentFixture<CrearClienteComponent>;
  let clienteService: ClienteService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearClienteComponent ],
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
    fixture = TestBed.createComponent(CrearClienteComponent);
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
