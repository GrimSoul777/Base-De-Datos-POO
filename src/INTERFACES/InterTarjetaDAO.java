package INTERFACES;

public interface InterTarjetaDAO {
    boolean guardar(Tarjeta tarjeta);
    ArrayList<Tarjeta> listar();
    Tarjeta buscar(int id);
    //boolean editar(Tarjeta tarjeta);
    boolean eliminar(int id);
}
