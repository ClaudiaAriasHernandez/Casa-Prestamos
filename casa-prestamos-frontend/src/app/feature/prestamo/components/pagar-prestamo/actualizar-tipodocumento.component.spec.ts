import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { ActualizarTipoDocumentoComponent } from './actualizar-tipodocumento.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { HttpService } from 'src/app/core/services/http.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

describe('ActualizarTipoDocumentoComponent', () => {
  let component: ActualizarTipoDocumentoComponent;
  let fixture: ComponentFixture<ActualizarTipoDocumentoComponent>;
  let tipodocumentoService: TipoDocumentoService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActualizarTipoDocumentoComponent ],
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
    fixture = TestBed.createComponent(ActualizarTipoDocumentoComponent);
    component = fixture.componentInstance;
    tipodocumentoService = TestBed.inject(TipoDocumentoService);
    spyOn(tipodocumentoService, 'guardar').and.returnValue(
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
    component.tipodocumentoForm.controls.tipoIdentificacion.setValue("CC")
    expect(component.tipodocumentoForm.valid).toBeTruthy();

    component.cerar();

    // Aca validamos el resultado esperado al enviar la petición
    // TODO adicionar expect
  });
});
