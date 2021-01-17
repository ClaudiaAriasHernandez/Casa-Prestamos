import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { CrearTipoDocumentoComponent } from './crear-tipodocumento.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { HttpService } from 'src/app/core/services/http.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

describe('CrearTipoDocumentoComponent', () => {
  let component: CrearTipoDocumentoComponent;
  let fixture: ComponentFixture<CrearTipoDocumentoComponent>;
  let tipoDocumentoService: TipoDocumentoService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearTipoDocumentoComponent ],
      imports: [
        CommonModule,
        HttpClientModule,
        RouterTestingModule,
        ReactiveFormsModule,
        FormsModule
      ],
      providers: [TipoDocumentoService, HttpService],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearTipoDocumentoComponent);
    component = fixture.componentInstance;
    tipoDocumentoService = TestBed.inject(TipoDocumentoService);
    spyOn(tipoDocumentoService, 'guardar').and.returnValue(
      of(true)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('formulario es invalido cuando esta vacio', () => {
    expect(component.tipodocumentoForm.valid).toBeFalsy();
  });

  it('Registrando tipo documento', () => {
    expect(component.tipodocumentoForm.valid).toBeFalsy();
    component.tipodocumentoForm.controls.id.setValue(2);
    component.tipodocumentoForm.controls.nombre.setValue("PEP")
    expect(component.tipodocumentoForm.valid).toBeTruthy();

    component.cerar();

    // Aca validamos el resultado esperado al enviar la petición
    // TODO adicionar expect
  });
});
