import { TipoDocumento } from './tipodocumento';

export class Cliente {
    id: number;
    nombre: string;
    direccion: string;
    numeroDocumento: string;
    correo: string;
    telefono: string;
    idTipoDocumento: number;
    dtoTipoDocumento: TipoDocumento;

    constructor(id: number, nombre: string, direccion: string, numeroDocumento: string, correo: string,
                telefono: string, idTipoDocumento: number, dtoTipoDocumento: TipoDocumento) {
      this.id = id;
      this.nombre = nombre;
      this.direccion = direccion;
      this.numeroDocumento = numeroDocumento;
      this.correo = correo;
      this.telefono = telefono;
      this.idTipoDocumento = idTipoDocumento;
      this.dtoTipoDocumento = dtoTipoDocumento;
    }
}
