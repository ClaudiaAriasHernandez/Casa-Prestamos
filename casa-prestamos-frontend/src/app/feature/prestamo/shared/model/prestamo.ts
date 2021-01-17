export class Prestamo {  
  id: number;
   fechaSolicitud: Date;
   fechaEstimadaPago: Date;
   fechaPago: Date;
   valor: number;
   valorMora: number;
   valorInteres: number;
   valorRecargo: number;
   valorTotal: number;
   estado: string; 
   numeroDocumento: string;
   tipoIdentificacion: string;
     
    constructor(id,fechaSolicitud: Date, fechaEstimadaPago: Date, fechaPago: Date, valor: number,valorMora: number, valorInteres: number, valorRecargo: number, valorTotal: number, estado: string,  numeroDocumento: string,tipoIdentificacion: string) {
      this.id=id;
      this.fechaSolicitud=fechaSolicitud;
      this.fechaEstimadaPago=fechaEstimadaPago;
      this.fechaPago=fechaPago;
      this.valor=valor;
      this.valorMora=valorMora;
      this.valorInteres=valorInteres;
      this.valorRecargo=valorRecargo;
      this.valorTotal=valorTotal;
      this.estado=estado;
      this.numeroDocumento=numeroDocumento;
      this.tipoIdentificacion=tipoIdentificacion;
    }
}
