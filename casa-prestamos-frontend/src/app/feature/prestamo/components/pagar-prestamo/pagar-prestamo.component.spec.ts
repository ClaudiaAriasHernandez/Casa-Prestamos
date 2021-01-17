import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { PagarPrestamoComponent } from './pagar-prestamo.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { PrestamoService } from '../../shared/service/prestamo.service';
import { HttpService } from 'src/app/core/services/http.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

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
        FormsModule
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

  it('Registrando prestamo', () => {
    expect(component.prestamoForm.valid).toBeFalsy();
    component.prestamoForm.controls.id.setValue(2);
    component.prestamoForm.controls.numeroDocumento.setValue("4552111")
    expect(component.prestamoForm.valid).toBeTruthy();

    component.cerar();

    // Aca validamos el resultado esperado al enviar la petición
    // TODO adicionar expect
  });
});
