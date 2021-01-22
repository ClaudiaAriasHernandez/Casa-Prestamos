import { Component } from '@angular/core';


@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent {

  public cardInfo: any[] = [
    {
      title: 'Actualizar',
      image: 'actualizar.png',
      content: 'Permite actualizar las información del cliente.',
      url: '/cliente/actualizar',

    },
    {
      title: 'Crear',
      image: 'crear.png',
      content: 'Permite crear un nuevo cliente.',
      url: '/cliente/crear',
      id: 'linkCrearCliente'
    },
    {
      title: 'Eliminar',
      image: 'eliminar.png',
      content: 'Permite eliminar un cliente.',
      url: '/cliente/borrar',
    },
    {
      title: 'Consultar',
      image: 'consultar.png',
      content: 'Permite consultar un cliente.',
      url: '/cliente/listar',
      id: 'linkListarClientes',
    }
  ];

}
