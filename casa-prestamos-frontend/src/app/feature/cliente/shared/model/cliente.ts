export class Cliente {  
    id: number;
    nombre: string;
    direccion: string;
    numeroDocumento: string;
    correo: string;
    telefono: string;
    idTipoDocumento: number;

    constructor(id: number, nombre: string, direccion: string, numeroDocumento: string, correo: string, telefono: string, idTipoDocumento: number ) {
      this.id=id;
      this.nombre=nombre;
      this.direccion=direccion;
      this.numeroDocumento=numeroDocumento;
      this.correo=correo;
      this.telefono=telefono;
      this.idTipoDocumento=idTipoDocumento;
    }
}
