import { Component, OnInit, ViewChild } from '@angular/core';
import { ClienteService } from '@cliente/shared/service/cliente.service';
import { Cliente } from '@shared/model/cliente';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-listar-cliente',
  templateUrl: './listar-cliente.component.html',
  styleUrls: ['./listar-cliente.component.scss']
})
export class ListarClienteComponent implements OnInit {
  public listaClientes: MatTableDataSource<Cliente>;
  public displayedColumns: string[] = ['id', 'dtoTipoDocumento', 'numeroDocumento', 'nombre', 'direccion', 'telefono', 'correo'];

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    protected clienteService: ClienteService,
  ) { }

  ngOnInit() {
    this.clienteService.consultar().subscribe((respuesta) => {
      this.listaClientes = new MatTableDataSource(respuesta);
      this.listaClientes.sort = this.sort;
      this.listaClientes.paginator = this.paginator;
    });
  }
}
