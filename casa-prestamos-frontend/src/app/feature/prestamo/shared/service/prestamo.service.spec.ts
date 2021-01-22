import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PrestamoService } from './prestamo.service';
import { environment } from 'src/environments/environment';
import { HttpService } from 'src/app/core/services/http.service';
import { Prestamo } from '../model/prestamo';
import { HttpResponse } from '@angular/common/http';
import { Cliente } from '@shared/model/cliente';
import { TipoDocumento } from '@shared/model/tipodocumento';

describe('PrestamoService', () => {
  let httpMock: HttpTestingController;
  let service: PrestamoService;
  const apiEndpointPrestamoConsulta = `${environment.endpoint}/prestamos`;
  const apiEndpointPrestamos = `${environment.endpoint}/prestamos`;
  const apiEndpointPrestamosPagar = `${environment.endpoint}/prestamos/tipoidentificacion/NUIP/numerodocumento/1037221034`;
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
    const dtoTipoDocumento: TipoDocumento = new TipoDocumento(4, 'NUIP', 'Identificacion unica');
    const dtoCliente: Cliente = new Cliente(3, 'Karen Garcia', 'Calle 62', '1037221034', 'kren@gmail.com', '5982252', 1, dtoTipoDocumento);
    const dummyPrestamos = new Prestamo(
      1, new Date('2020-12-27'), new Date('2021-01-10'), new Date('2021-01-10'), 1000000.0
      , 30000, 0.0, 0.0, 1030000, 'P', '1037641034', 'CC', dtoCliente
    );
    service.consultar().subscribe((respuesta) => {
      expect(respuesta);
    });
    const req = httpMock.expectOne(apiEndpointPrestamoConsulta);
    expect(req.request.method).toBe('GET');
    req.flush(dummyPrestamos);
  });

  it('deberia crear un pretamoa', () => {
    const dtoTipoDocumento: TipoDocumento = new TipoDocumento(4, 'NUIP', 'Identificacion unica');
    const dtoCliente: Cliente = new Cliente(
      3, 'Karen Garcia', 'Calle 62', '1037221034', 'kren@gmail.com', '5982252', 1, dtoTipoDocumento
    );
    const dummyPrestamo = new Prestamo(
      1, new Date('2020-12-27'), new Date( '2021-01-10'),
      new Date('2021-01-10'), 1000000.0, 30000, 0.0, 0.0,
      1030000, 'P', '1037641034', 'CC' , dtoCliente
    );
    service.guardar(dummyPrestamo).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(apiEndpointPrestamos);
    expect(req.request.method).toBe('POST');
    req.event(new HttpResponse<boolean>({body: true}));
  });
  it('deberia pagara un prestamo', () => {
    const dtoTipoDocumento: TipoDocumento = new TipoDocumento(4, 'NUIP', 'Identificacion unica');
    const dtoCliente: Cliente = new Cliente(3, 'Karen Garcia', 'Calle 62', '1037221034', 'kren@gmail.com', '5982252', 1, dtoTipoDocumento);
    const dummyPrestamo = new Prestamo(
    1, new Date('2020-12-27'), new Date( '2021-01-10'),
    new Date('2021-01-10'), 1000000.0, 30000, 0.0, 0.0,
     1030000, 'P', '1037641034', 'CC' , dtoCliente
     );
    service.pagar(dummyPrestamo).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(apiEndpointPrestamosPagar);
    expect(req.request.method).toBe('PUT');
    req.event(new HttpResponse<boolean>({body: true}));
  });

});
