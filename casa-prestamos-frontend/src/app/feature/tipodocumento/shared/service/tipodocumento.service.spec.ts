import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { TipoDocumentoService } from './tipodocumento.service';
import { environment } from 'src/environments/environment';
import { HttpService } from 'src/app/core/services/http.service';
import { TipoDocumento } from '../model/tipodocumento';
import { HttpResponse } from '@angular/common/http';

describe('TipoDocumentoService', () => {
  let httpMock: HttpTestingController;
  let service: TipoDocumentoService;
  const apiEndpointTipoDocumentoConsulta = `${environment.endpoint}/tipodocumentos`;
  const apiEndpointTipoDocumentos = `${environment.endpoint}/tipodocumentos`;

  beforeEach(() => {
    const injector = TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TipoDocumentoService, HttpService]
    });
    httpMock = injector.inject(HttpTestingController);
    service = TestBed.inject(TipoDocumentoService);
  });

  it('should be created', () => {
    const tipodocumentoService: TipoDocumentoService = TestBed.inject(TipoDocumentoService);
    expect(tipodocumentoService).toBeTruthy();
  });

  it('deberia listar tipo documentos', () => {
    const dummyTipoDocumentos = [
      new TipoDocumento(1, 'CC', 'Cedula de ciudadania'), new TipoDocumento(1, 'CC', 'Cedula de ciudadania')
    ];
    service.consultar().subscribe(tipoDocumentos => {
      expect(tipoDocumentos.length).toBe(2);
      expect(tipoDocumentos).toEqual(dummyTipoDocumentos);
    });
    const req = httpMock.expectOne(apiEndpointTipoDocumentoConsulta);
    expect(req.request.method).toBe('GET');
    req.flush(dummyTipoDocumentos);
  });

  it('deberia crear un tipo documento', () => {
    const dummyTipoDocumento = new TipoDocumento(1, 'CC', 'Cedula de ciudadania');
    service.guardar(dummyTipoDocumento).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(apiEndpointTipoDocumentos);
    expect(req.request.method).toBe('POST');
    req.event(new HttpResponse<boolean>({body: true}));
  });
  it('deberia actualizar un tipo documento', () => {
    const dummyTipoDocumento = new TipoDocumento(1, 'CC', 'Cedula de ciudadania');
    service.actualizar(dummyTipoDocumento).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(`${apiEndpointTipoDocumentos}/1`);
    expect(req.request.method).toBe('PUT');
    req.event(new HttpResponse<boolean>({body: true}));
  });

  it('deberia eliminar un tipo documento', () => {
    const dummyTipoDocumento = new TipoDocumento(1, 'CC', 'Cedula de ciudadania');
    service.eliminar(dummyTipoDocumento).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(`${apiEndpointTipoDocumentos}/1`);
    expect(req.request.method).toBe('DELETE');
    req.event(new HttpResponse<boolean>({body: true}));
  });
});
