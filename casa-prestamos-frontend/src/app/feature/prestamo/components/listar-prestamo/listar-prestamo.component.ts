import { Component, OnInit, ViewChild } from '@angular/core';
import { PrestamoService } from '@prestamo/shared/service/prestamo.service';
import { Prestamo } from '@prestamo/shared/model/prestamo';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-listar-prestamo',
  templateUrl: './listar-prestamo.component.html',
  styleUrls: ['./listar-prestamo.component.scss']
})
export class ListarPrestamoComponent implements OnInit {
  public listaPrestamos: MatTableDataSource<Prestamo>;
  public displayedColumns: string[] = ['id', 'dtoCliente', 'fechaSolicitud', 'fechaEstimadaPago',
  'fechaPago', 'valor', 'valorMora', 'valorInteres', 'valorRecargo', 'valorTotal', 'estado'
];

@ViewChild(MatSort, { static: true }) sort: MatSort;
@ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    protected prestamoService: PrestamoService,
  ) { }

  ngOnInit() {
    this.prestamoService.consultar().subscribe((respuesta) => {
      this.listaPrestamos = new MatTableDataSource(respuesta);
      this.listaPrestamos.sort = this.sort;
      this.listaPrestamos.paginator = this.paginator;
    });
  }
}
