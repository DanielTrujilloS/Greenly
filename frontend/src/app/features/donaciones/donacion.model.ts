// donacion.model.ts
export interface Donacion {
    id?: number;
    name: string;
    descripcion: string;
    montoDonado: number;
    metodoEntrega: string;
    fechaDonacion: string; 
    idDonante?: number;
    idCampania?: number;
    idTipoDonacion?: number;
    idEstadoDonacion?: number;
  }
  export interface Donante {
    id: number;
    name: string;
  }
  export interface Campania {
    id: number;
    name: string;
  }
  export interface TipoDonacion {
    id: number;
    nombre: string;
  }
  export interface EstadoDonacion {
    id: number;
    nombre: string;
  }
  