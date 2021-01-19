import { Component } from '@angular/core';

@Component({
  selector: 'app-prestamo',
  templateUrl: './prestamo.component.html',
  styleUrls: ['./prestamo.component.scss']
})
export class PrestamoComponent {

  public cardInfo: any[] = [
    {
      title: 'Pagar',
      image: 'actualizar.png',
      content: 'Permite pagar un prestamo.',
      url: '/prestamo/pagar',
    },
    {
      title: 'Crear',
      image: 'crear.png',
      content: 'Permite crear un nuevo prestamo a un cliente.',
      url: '/prestamo/crear',
    },
    {
      title: 'Consultar',
      image: 'consultar.png',
      content: 'Permite consultar los prestamos.',
      url: '/prestamo/listar',
    }
  ];

}
