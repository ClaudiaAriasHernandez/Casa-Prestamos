import { Component, OnInit, ViewChild } from '@angular/core';
import { ClienteService } from '@cliente/shared/service/cliente.service';
import { Cliente } from '@cliente/shared/model/cliente';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { NotificationService } from 'src/app/notification.service';

@Component({
  selector: 'app-listar-cliente',
  templateUrl: './listar-cliente.component.html',
  styleUrls: ['./listar-cliente.component.scss']
})
export class ListarClienteComponent implements OnInit {
  public listaClientes: MatTableDataSource<Cliente>;
  public displayedColumns: string[] = ['id', 'dtoTipoDocumento','numeroDocumento', 'nombre', 'direccion','telefono', 'correo'];
  


  @ViewChild(MatSort, { static: true }) sort: MatSort;
  
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(protected clienteService: ClienteService,
  private readonly notificationService: NotificationService,)
 { }

  ngOnInit() {
    this.clienteService.consultar().subscribe((respuesta) => {
      console.log(respuesta);
      this.listaClientes = new MatTableDataSource(respuesta);
     
      this.listaClientes.sort = this.sort;
      this.listaClientes.paginator = this.paginator;
    }, (error) => {
      this.notificationService.error(error.error.mensaje);
    });
  }



}
