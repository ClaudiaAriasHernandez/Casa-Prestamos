import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { ListarClienteComponent } from './listar-cliente.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ClienteService } from '../../shared/service/cliente.service';
import { Cliente } from '../../shared/model/cliente';
import { HttpService } from 'src/app/core/services/http.service';
import { TipoDocumento } from 'src/app/feature/tipodocumento/shared/model/tipodocumento';
import { MatTableDataSource } from '@angular/material/table';

describe('ListarClienteComponent', () => {
  let component: ListarClienteComponent;
  let fixture: ComponentFixture<ListarClienteComponent>;
  let clienteService: ClienteService;
  const dtoTipoDocumento: TipoDocumento = new TipoDocumento(4, "NUIP", "Identificacion unica");

  const listaClientes: Cliente[] = [ new Cliente(3, 'Karen Garcia', 'Calle 62', '1037221034', 'kren@gmail.com', '5982252', 1, dtoTipoDocumento)];

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ListarClienteComponent],
      imports: [
        CommonModule,
        MatTableDataSource,
        HttpClientModule,
        RouterTestingModule
      ],
      providers: [ClienteService, HttpService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarClienteComponent);
    component = fixture.componentInstance;
   clienteService = TestBed.inject(ClienteService);
    spyOn(clienteService, 'consultar').and.returnValue(
      of(listaClientes)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
        expect(component).toBeTruthy();
        clienteService.consultar().subscribe(resultado => {
          expect(1).toBe(resultado.length);
      });
      
 
});

});
