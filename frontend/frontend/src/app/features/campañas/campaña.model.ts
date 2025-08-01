export interface CampañaDTO {
    id?: number;
    titulo: string;
    descripcion: string;
    fechaInicio: string;  // YYYY-MM-DD
    fechaFin: string;     // YYYY-MM-DD
    ongId?: number; // <- debe ser opcional
  ubicacion_CampañaId?: number;
  }
  