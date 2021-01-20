import { Component, OnInit, ViewChild } from '@angular/core';
import { TipoDocumentoService } from '@tipodocumento/shared/service/tipodocumento.service';
import { TipoDocumento } from '@tipodocumento/shared/model/tipodocumento';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { NotificationService } from 'src/app/notification.service';
import { ConsultaTipoDocumentoService } from '@shared/service/consulta-tipodocumento.service';


@Component({
  selector: 'app-listar-tipodocumento',
  templateUrl: './listar-tipodocumento.component.html',
  styleUrls: ['./listar-tipodocumento.component.scss']
})
export class ListarTipoDocumentoComponent implements OnInit {

  public listaTipoDocumentos: MatTableDataSource<TipoDocumento>;
  public displayedColumns: string[] = ['id', 'tipoIdentificacion', 'descripcion'];

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(protected tipoDocumentoService: TipoDocumentoService,
              private readonly notificationService: NotificationService,
              private readonly consultaTipoDocumentoService: ConsultaTipoDocumentoService,
  ) { }

  ngOnInit() {
    this.listar();
  }
  listar() {
  this.consultaTipoDocumentoService.consultar().subscribe((respuesta) => {
    this.listaTipoDocumentos = new MatTableDataSource(respuesta);
    this.listaTipoDocumentos.sort = this.sort;
    this.listaTipoDocumentos.paginator = this.paginator;
  }, (error) => {
    this.notificationService.error(error.error.mensaje);
  });
}
}
