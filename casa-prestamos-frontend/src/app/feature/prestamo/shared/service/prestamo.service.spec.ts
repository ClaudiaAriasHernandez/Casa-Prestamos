import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { PrestamoService } from './prestamo.service';
import { environment } from 'src/environments/environment';
import { HttpService } from 'src/app/core/services/http.service';
import { Prestamo } from '../model/prestamo';
import { HttpResponse } from '@angular/common/http';

describe('PrestamoService', () => {
  let httpMock: HttpTestingController;
  let service: PrestamoService;
  const apiEndpointPrestamoConsulta = `${environment.endpoint}/tiposFamilia`;
  const apiEndpointPrestamos= `${environment.endpoint}/prestamos`;

  beforeEach(() => {
    const injector = TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PrestamoService, HttpService]
    });
    httpMock = injector.inject(HttpTestingController);
    service = TestBed.inject(PrestamoService);
  });

  it('should be created', () => {
    const prestamoService: PrestamoService = TestBed.inject(PrestamoService);
    expect(prestamoService).toBeTruthy();
  });

  it('deberia listar prestamos', () => {
    const dummyPrestamos =      new Prestamo(1,new Date("2020-12-27"), new Date( "2021-01-10"), new Date("2021-01-10"), 1000000.0, 30000, 0.0, 0.0, 1030000, "P","1037641034", "CC" );
    service.consultar(dummyPrestamos).subscribe((respuesta) => {
      expect(respuesta);
    });
    const req = httpMock.expectOne(apiEndpointPrestamoConsulta);
    expect(req.request.method).toBe('GET');
    req.flush(dummyPrestamos);
  });

  it('deberia crear un pretamoa', () => {
    const dummyPrestamo = new Prestamo(1,new Date("2020-12-27"), new Date( "2021-01-10"), new Date("2021-01-10"), 1000000.0, 30000, 0.0, 0.0, 1030000, "P","1037641034", "CC" );
    service.guardar(dummyPrestamo).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(apiEndpointPrestamos);
    expect(req.request.method).toBe('POST');
    req.event(new HttpResponse<boolean>({body: true}));
  });
  it('deberia pagara un prestamo', () => {
    const dummyPrestamo = new Prestamo(1,new Date("2020-12-27"), new Date( "2021-01-10"), new Date("2021-01-10"), 1000000.0, 30000, 0.0, 0.0, 1030000, "P","1037641034", "CC" );
    service.pagar(dummyPrestamo).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(apiEndpointPrestamos);
    expect(req.request.method).toBe('POST');
    req.event(new HttpResponse<boolean>({body: true}));
  });

});
