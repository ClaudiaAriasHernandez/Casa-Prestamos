export class TipoDocumento {
    id: number;
    tipoIdentificacion: string;
    descripcion: string;

    constructor(id: number, tipoIdentificacion: string, descripcion: string ) {
      this.id = id;
      this.tipoIdentificacion = tipoIdentificacion;
      this.descripcion = descripcion;
    }
}
