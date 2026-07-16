package INTERFACES;

import java.util.ArrayList;
import MODELS.Tarjeta;

public interface InterTarjetaDAO {
    boolean guardar(Tarjeta tarjeta);
    ArrayList<Tarjeta> listar(int idUser);
    Tarjeta buscar(int id);
    //boolean editar(Tarjeta tarjeta);
    boolean eliminar(int id);
}
