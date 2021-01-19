import { Component } from '@angular/core';


@Component({
  selector: 'app-tipodocumento',
  templateUrl: './tipodocumento.component.html',
  styleUrls: ['./tipodocumento.component.scss']
})
export class TipoDocumentoComponent {

  public cardInfo: any[] = [
    {
      title: 'Actualizar',
      image: 'actualizar.png',
      content: 'Permite actualizar las información del tipo de documento.',
      url: '/tipodocumento/actualizar',
    },
    {
      title: 'Crear',
      image: 'crear.png',
      content: 'Permite crear un nuevo tipo de documento.',
      url: '/tipodocumento/crear',
    },
    {
      title: 'Eliminar',
      image: 'eliminar.png',
      content: 'Permite eliminar un tipo de documento.',
      url: '/tipodocumento/borrar',
    },
    {
      title: 'Consultar',
      image: 'consultar.png',
      content: 'Permite consultar los tipos de documentos.',
      url: '/tipodocumento/listar',
    }
  ];

}
