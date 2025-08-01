export interface PostDTO {
  id?: number;
  titulo: string;
  contenido: string;
  fecha: string; // YYYY-MM-DD
  imagen?: string;
  campaniaId: number;
}
