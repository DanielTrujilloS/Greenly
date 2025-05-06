package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.entities.EstadoDonacion;
import pe.edu.upc.greenly.entities.TipoDonacion;

import java.util.List;

public interface TipoDonacionService {
    public TipoDonacion addTipoDonacion(TipoDonacion tipoDonacion);
    public void deleteTipoDonacion(Long id);
    public TipoDonacion findById(Long id);
    public List<TipoDonacion> listAll();
    public TipoDonacion editTipoDonacion(TipoDonacion tipoDonacion);
}
