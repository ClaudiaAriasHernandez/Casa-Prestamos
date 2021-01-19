import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { CrearPrestamoComponent } from './crear-prestamo.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { PrestamoService } from '../../shared/service/prestamo.service';
import { HttpService } from 'src/app/core/services/http.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NotificationService } from 'src/app/notification.service';

describe('CrearPrestamoComponent', () => {
  let component: CrearPrestamoComponent;
  let fixture: ComponentFixture<CrearPrestamoComponent>;
  let prestamoService: PrestamoService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearPrestamoComponent ],
      imports: [
        CommonModule,
        HttpClientModule,
        RouterTestingModule,
        ReactiveFormsModule,
        FormsModule,
        NotificationService
      ],
      providers: [PrestamoService, HttpService,NotificationService],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearPrestamoComponent);
    component = fixture.componentInstance;
   prestamoService = TestBed.inject(PrestamoService);
    spyOn(prestamoService, 'guardar').and.returnValue(
      of(true)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('formulario es invalido cuando esta vacio', () => {
    expect(component.prestamoForm.valid).toBeFalsy();
  });

  it('Registrando prestamo', () => {
    expect(component.prestamoForm.valid).toBeFalsy();
    component.prestamoForm.controls.idCliente.setValue(15);
    component.prestamoForm.controls.tipoIdentificacion.setValue("CC");
    component.prestamoForm.controls.numeroDocumento.setValue("12876554567")
    component.prestamoForm.controls.valor.setValue("1000000")
    component.prestamoForm.controls.valorMora.setValue("")
    component.prestamoForm.controls.valorRecargo.setValue("")
    component.prestamoForm.controls.valorTotal.setValue("")
    component.prestamoForm.controls.valorInteres.setValue("")
    component.prestamoForm.controls.fechaSolicitud.setValue("2021-01-19")
    component.prestamoForm.controls.fechaEstimadaPago.setValue("2021-02-02")
    component.prestamoForm.controls.fechaPago.setValue("")
    component.prestamoForm.controls.estado.setValue("D")
    expect(component.prestamoForm.valid).toBeTruthy();

    component.crear();

    // Aca validamos el resultado esperado al enviar la petición
    // TODO adicionar expect
  });
});
