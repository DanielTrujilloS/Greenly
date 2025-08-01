export class JwtHelper {
    static decodeToken(token: string): any {
      if (!token) return null;
      try {
        const payload = token.split('.')[1];
        // atob decodifica base64
        return JSON.parse(atob(payload));
      } catch (e) {
        return null;
      }
    }
  
    static getTokenExpiration(token: string): Date | null {
      const decoded = JwtHelper.decodeToken(token);
      if (!decoded || !decoded.exp) return null;
      return new Date(decoded.exp * 1000);
    }
  
    static isTokenExpired(token: string): boolean {
      const expiration = JwtHelper.getTokenExpiration(token);
      if (!expiration) return true;
      return expiration.getTime() < Date.now();
    }
  }
  