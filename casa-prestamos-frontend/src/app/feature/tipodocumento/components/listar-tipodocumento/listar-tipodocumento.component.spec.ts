import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { ListarTipoDocumentoComponent } from './listar-tipodocumento.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ConsultaTipoDocumentoService } from '@shared/service/consulta-tipodocumento.service';
import { TipoDocumento } from '@shared/model/tipodocumento';
import { HttpService } from 'src/app/core/services/http.service';
import { MatTableDataSource } from '@angular/material/table';

describe('ListarTipoDocumentoComponent', () => {
  let component: ListarTipoDocumentoComponent;
  let fixture: ComponentFixture<ListarTipoDocumentoComponent>;
  let consultaTipoDocumentoService: ConsultaTipoDocumentoService;
  const listarTipoDocumento: TipoDocumento[] = [new TipoDocumento(3, 'CE', 'Cedula de extranjeria')];

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ListarTipoDocumentoComponent],
      imports: [
        CommonModule,
        HttpClientModule,
        RouterTestingModule,
        MatTableDataSource
      ],
      providers: [ConsultaTipoDocumentoService, HttpService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarTipoDocumentoComponent);
    component = fixture.componentInstance;
    consultaTipoDocumentoService = TestBed.inject(ConsultaTipoDocumentoService);
    spyOn(consultaTipoDocumentoService, 'consultar').and.returnValue(
      of(listarTipoDocumento)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    consultaTipoDocumentoService.consultar().subscribe(resultado => {
      expect(1).toBe(resultado.length);
  });
});

});
