package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import CONEXION.Conexion;
import MODELS.Usuario;
import INTERFACES.InterUsuarioDAO;

public class UsuarioDAO implements InterUsuarioDAO {


    @Override
    public boolean guardar(Usuario usuario) {
        try {
            Connection con = Conexion.conectar();
            String sql = "INSERT INTO usuarios "
                    + "(id_user,nombre,apellido_p,apellido_m,edad,email,contra,activo) "
                    + "VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido_p());
            ps.setString(4, usuario.getApellido_m());
            ps.setInt(5, usuario.getEdad());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getContra());
            ps.setBoolean(8, usuario.isActivo());
            ps.execute();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("" + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> lista = new ArrayList<>();

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM usuarios ORDER BY id_user";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido_p(rs.getString("apellido_p"));
                usuario.setApellido_m(rs.getString("apellido_m"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setEmail(rs.getString("email"));
                usuario.setContra(rs.getString("contra"));
                usuario.setActivo(rs.getBoolean("activo"));
                lista.add(usuario);
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println("" + e.getLocalizedMessage());
        }
        return lista;
    }

    @Override
    public Usuario buscar(int id) {
        Usuario usuario = null;

        try {
            Connection con = Conexion.conectar();
            String sql = "SELECT * FROM usuarios WHERE id_user=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido_p(rs.getString("apellido_p"));
                usuario.setApellido_m(rs.getString("apellido_m"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setEmail(rs.getString("email"));
                usuario.setContra(rs.getString("contra"));
                usuario.setActivo(rs.getBoolean("activo"));
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println("" + e.getLocalizedMessage());
        }
        return usuario;
    }

    @Override
    public boolean eliminar(int id) {
        try {
            Connection con = Conexion.conectar();
            String sql = "DELETE FROM usuarios WHERE id_user=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            con.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("" + e.getLocalizedMessage());
            return false;
        }
    }
}
