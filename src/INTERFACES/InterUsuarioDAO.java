package INTERFACES;

import java.util.ArrayList;
import MODELS.Usuario;

public interface InterUsuarioDAO {
    public boolean guardar(Usuario usuario);

    public ArrayList<Usuario> listar();

    public Usuario buscar(int id);

    public boolean editar(Usuario usuario);

    public boolean eliminar(int id);

}
