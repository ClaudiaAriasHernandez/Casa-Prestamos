import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { ListarPrestamoComponent } from './listar-prestamo.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { PrestamoService } from '../../shared/service/prestamo.service';
import { Prestamo } from '../../shared/model/prestamo';
import { HttpService } from 'src/app/core/services/http.service';
import { Cliente } from '@shared/model/cliente';
import { TipoDocumento } from '@shared/model/tipodocumento';
import { NotificationService } from 'src/app/notification.service';

describe('ListarPrestamoComponent', () => {
  let component: ListarPrestamoComponent;
  let fixture: ComponentFixture<ListarPrestamoComponent>;
  let prestamoService: PrestamoService;
  const dtoTipoDocumento: TipoDocumento = new TipoDocumento(4, 'NUIP', 'Identificacion unica');
  const dtoCliente: Cliente = new Cliente(3, 'Karen Garcia', 'Calle 62', '1037221034', 'kren@gmail.com', '5982252', 1, dtoTipoDocumento);
  const listaPrestamos: Prestamo[] = [new Prestamo(
    1, new Date('2020-12-27'), new Date('2021-01-10'), new Date('2021-01-10'),
     1000000.0, 30000, 0.0, 0.0, 1030000, 'P', '1037641034', 'CC' , dtoCliente
    )];

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ListarPrestamoComponent],
      imports: [
        CommonModule,
        HttpClientModule,
        RouterTestingModule,
        NotificationService
      ],
      providers: [PrestamoService, HttpService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarPrestamoComponent);
    component = fixture.componentInstance;
    prestamoService = TestBed.inject(PrestamoService);
    spyOn(prestamoService, 'consultar').and.returnValue(
      of(listaPrestamos)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    prestamoService.consultar().subscribe(resultado => {
      expect(1).toBe(resultado.length);
  });
});
});
