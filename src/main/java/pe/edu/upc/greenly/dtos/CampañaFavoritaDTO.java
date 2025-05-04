package pe.edu.upc.greenly.dtos;

public class CampañaFavoritaDTO {
    private int idCampañaFav;
    private int campañaId;
    private int donanteId;

    public int getIdCampañaFav() {
        return idCampañaFav;
    }

    public void setIdCampañaFav(int idCampañaFav) {
        this.idCampañaFav = idCampañaFav;
    }

    public int getCampañaId() {
        return campañaId;
    }

    public void setCampañaId(int campañaId) {
        this.campañaId = campañaId;
    }

    public int getDonanteId() {
        return donanteId;
    }

    public void setDonanteId(int donanteId) {
        this.donanteId = donanteId;
    }
}
