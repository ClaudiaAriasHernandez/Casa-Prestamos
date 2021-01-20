import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ClienteService } from './cliente.service';
import { environment } from 'src/environments/environment';
import { HttpService } from 'src/app/core/services/http.service';
import { Cliente } from '../model/cliente';
import { HttpResponse } from '@angular/common/http';
import { TipoDocumento } from 'src/app/feature/tipodocumento/shared/model/tipodocumento';

describe('ClienteService', () => {
  let httpMock: HttpTestingController;
  let service: ClienteService;
  const apiEndpointClienteConsulta = `${environment.endpoint}/clientes`;
  const apiEndpointClientes = `${environment.endpoint}/clientes`;
  const apiEndpointClientesActualizar = `${environment.endpoint}/clientes/1`;
  beforeEach(() => {
    const injector = TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClienteService, HttpService]
    });
    httpMock = injector.inject(HttpTestingController);
    service = TestBed.inject(ClienteService);
  });

  it('should be created', () => {
    const clienteService: ClienteService = TestBed.inject(ClienteService);
    expect(clienteService).toBeTruthy();
  });

  it('deberia listar clientes', () => {
    const dummyDtoTipoDocumento: TipoDocumento = new TipoDocumento(4, 'NUIP', 'Identificacion unica');
    const dummyClientes = [
      new Cliente(1, 'Claudia Arias', 'Calle 62', '1037641034', 'claarher@gmail.com', '5982252', 1, dummyDtoTipoDocumento )
    ];
    service.consultar().subscribe(clientes => {
      expect(clientes.length).toBe(2);
      expect(clientes).toEqual(dummyClientes);
    });
    const req = httpMock.expectOne(apiEndpointClienteConsulta);
    expect(req.request.method).toBe('GET');
    req.flush(dummyClientes);
  });

  it('deberia crear un clinte', () => {
    const dummyDtoTipoDocumento: TipoDocumento = new TipoDocumento(4, 'NUIP', 'Identificacion unica');
    const dummyCliente =
      new Cliente(1, 'Claudia Arias', 'Calle 62', '1037641034', 'claarher@gmail.com', '5982252', 1, dummyDtoTipoDocumento );

    service.guardar(dummyCliente).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(apiEndpointClientes);
    expect(req.request.method).toBe('POST');
    req.event(new HttpResponse<boolean>({body: true}));
  });
  it('deberia actualizar un clinte', () => {
    const dummyDtoTipoDocumento: TipoDocumento = new TipoDocumento(4, 'NUIP', 'Identificacion unica');

    const dummyCliente =
      new Cliente(1, 'Claudia Arias', 'Calle 62', '1037641034', 'claarher@gmail.com', '5982252', 1, dummyDtoTipoDocumento );
    service.actualizar(dummyCliente).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(apiEndpointClientesActualizar);
    expect(req.request.method).toBe('PUT');
    req.event(new HttpResponse<boolean>({body: true}));
  });

  it('deberia eliminar un producto', () => {
    const dummyDtoTipoDocumento: TipoDocumento = new TipoDocumento(4, 'NUIP', 'Identificacion unica');
    const dummyCliente = new Cliente(2, 'Sara Perez', 'Calle 62', '1037444034', 'sara.perez@gmail.com',
     '5982252', 1, dummyDtoTipoDocumento
     );
    service.eliminar(dummyCliente).subscribe((respuesta) => {
      expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(`${apiEndpointClientes}/2`);
    expect(req.request.method).toBe('DELETE');
    req.event(new HttpResponse<boolean>({body: true}));
  });
});
