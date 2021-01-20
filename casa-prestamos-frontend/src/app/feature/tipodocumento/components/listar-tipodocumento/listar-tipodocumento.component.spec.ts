import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { ListarTipoDocumentoComponent } from './listar-tipodocumento.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { TipoDocumentoService } from '../../shared/service/tipodocumento.service';
import { TipoDocumento } from '../../shared/model/tipodocumento';
import { HttpService } from 'src/app/core/services/http.service';
import { MatTableDataSource } from '@angular/material/table';

describe('ListarTipoDocumentoComponent', () => {
  let component: ListarTipoDocumentoComponent;
  let fixture: ComponentFixture<ListarTipoDocumentoComponent>;
  let tipoDocumentoService: TipoDocumentoService;
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
      providers: [TipoDocumentoService, HttpService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarTipoDocumentoComponent);
    component = fixture.componentInstance;
    tipoDocumentoService = TestBed.inject(TipoDocumentoService);
    spyOn(tipoDocumentoService, 'consultar').and.returnValue(
      of(listarTipoDocumento)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    tipoDocumentoService().subscribe(resultado => {
      expect(1).toBe(resultado.length);
  });
});

});
