export interface Usuario {
    id?: number;
    username: string;
    password?: string;      // Solo para el registro o edición
    authorities?: string;   // Rol, puede venir como "ROLE_ADMIN", etc.
    enabled?: boolean;
  }
  