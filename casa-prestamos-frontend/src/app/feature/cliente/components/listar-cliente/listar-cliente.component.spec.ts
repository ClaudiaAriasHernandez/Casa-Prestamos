import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { ListarClienteComponent } from './listar-cliente.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { ClienteService } from '../../shared/service/cliente.service';
import { Cliente } from '../../shared/model/cliente';
import { HttpService } from 'src/app/core/services/http.service';

describe('ListarClienteComponent', () => {
  let component: ListarClienteComponent;
  let fixture: ComponentFixture<ListarClienteComponent>;
  let clienteService: ClienteService;
  const listaClientes: Cliente[] = [new Cliente(2, 'Kelly Espinosa', 'Calle 62', '1007741034', 'kelly@gmail.com', '5982252', 1), new Cliente(3, 'Karen Garcia', 'Calle 62', '1037221034', 'kren@gmail.com', '5982252', 1)];

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ListarClienteComponent],
      imports: [
        CommonModule,
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
    component.listaClientes.subscribe(resultado => {
      expect(2).toBe(resultado.length);
  });
});

});
