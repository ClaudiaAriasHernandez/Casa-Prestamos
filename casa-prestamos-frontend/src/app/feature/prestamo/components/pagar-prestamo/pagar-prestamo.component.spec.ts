import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { PagarPrestamoComponent } from './pagar-prestamo.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { PrestamoService } from '../../shared/service/prestamo.service';
import { HttpService } from 'src/app/core/services/http.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NotificationService } from 'src/app/notification.service';


describe('PagarPrestamoComponent', () => {
  let component: PagarPrestamoComponent;
  let fixture: ComponentFixture<PagarPrestamoComponent>;
  let prestamoService: PrestamoService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PagarPrestamoComponent ],
      imports: [
        CommonModule,
        HttpClientModule,
        RouterTestingModule,
        ReactiveFormsModule,
        FormsModule,
        NotificationService
      ],
      providers: [PrestamoService, HttpService],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PagarPrestamoComponent);
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

  it('Pagar prestamo', () => {
    expect(component.prestamoForm.valid).toBeFalsy();
    component.prestamoForm.controls.idCliente.setValue(15);
    component.prestamoForm.controls.tipoIdentificacion.setValue("CC");
    component.prestamoForm.controls.numeroDocumento.setValue("12876554567")
    component.prestamoForm.controls.valor.setValue("1000000")
    component.prestamoForm.controls.valorMora.setValue("0")
    component.prestamoForm.controls.valorRecargo.setValue("0")
    component.prestamoForm.controls.valorTotal.setValue("10300000")
    component.prestamoForm.controls.valorInteres.setValue("30000")
    component.prestamoForm.controls.fechaSolicitud.setValue("2021-01-19")
    component.prestamoForm.controls.fechaEstimadaPago.setValue("2021-02-02")
    component.prestamoForm.controls.fechaPago.setValue("2021-01-19")
    component.prestamoForm.controls.estado.setValue("P")
    expect(component.prestamoForm.valid).toBeTruthy();

    component.pagar();

    // Aca validamos el resultado esperado al enviar la petición
    // TODO adicionar expect
  });
});
