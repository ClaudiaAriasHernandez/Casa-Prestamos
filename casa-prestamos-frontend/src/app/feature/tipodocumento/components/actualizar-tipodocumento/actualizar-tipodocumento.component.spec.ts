import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { ActualizarTipoDocumentoComponent } from './actualizar-tipodocumento.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { HttpService } from 'src/app/core/services/http.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CrearTipoDocumentoComponent } from 'src/app/feature/tipodocumento/components/crear-tipodocumento/crear-tipodocumento.component';
import { NotificationService } from 'src/app/notification.service';

describe('ActualizarTipoDocumentoComponent', () => {
  let component: ActualizarTipoDocumentoComponent;
  let componentCliente: CrearTipoDocumentoComponent;
  let fixture: ComponentFixture<ActualizarTipoDocumentoComponent>;
  let tipodocumentoService: TipoDocumentoService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActualizarTipoDocumentoComponent , CrearTipoDocumentoComponent],
      imports: [
        CommonModule,
        HttpClientModule,
        RouterTestingModule,
        ReactiveFormsModule,
        FormsModule,
        NotificationService
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

  it('Actualizar tipo documento', () => {

    expect(component.tipodocumentoForm.valid).toBeFalsy();
    component.tipodocumentoForm.controls.id.setValue(12);
    component.tipodocumentoForm.controls.tipoIdentificacion.setValue('CC');
    component.tipodocumentoForm.controls.descripcion.setValue('Cedula de Extranjeria');
    expect(component.tipodocumentoForm.valid).toBeTruthy();

    componentCliente.crear();
    expect(component.tipodocumentoForm.valid).toBeFalsy();
    component.tipodocumentoForm.controls.id.setValue(12);
    component.tipodocumentoForm.controls.tipoIdentificacion.setValue('CC');
    component.tipodocumentoForm.controls.descripcion.setValue('Cedula de Ciudadania');
    expect(component.tipodocumentoForm.valid).toBeTruthy();

    component.actualizar();

    // Aca validamos el resultado esperado al enviar la petición
    // TODO adicionar expect
  });
});
