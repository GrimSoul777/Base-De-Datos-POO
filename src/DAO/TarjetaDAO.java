package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import CONEXION.Conexion;
import MODELS.Tarjeta;
import INTERFACES.InterTarjetaDAO;

public class TarjetaDAO implements InterTarjetaDAO {


    @Override
    public boolean guardar(Tarjeta tarjeta) {
        try {
            Connection con = Conexion.conectar();
            String sql = "INSERT INTO tarjetas "
                    + "(clabe, numero, mes_exp, año_exp, saldo, tipo, credito, activo, id_user) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, tarjeta.getId());
            ps.setString(1, tarjeta.getClabe());
            ps.setString(2, tarjeta.getNumero());
            ps.setInt(3, tarjeta.getMes_exp());
            ps.setInt(4, tarjeta.getAño_exp());
            ps.setDouble(5, tarjeta.getSaldo());
            ps.setString(6, tarjeta.getTipo());
            ps.setDouble(7, tarjeta.getCredito());
            ps.setBoolean(8, tarjeta.isActivo());
            ps.setInt(9, tarjeta.getId_user());
            ps.execute();
            con.close();

            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Tarjeta> listar(int idUser) {
        ArrayList<Tarjeta> lista = new ArrayList<>();
        try {
            Connection con = Conexion.conectar();
            String sql = "SELECT * FROM tarjetas WHERE id_user = ? ORDER BY id_tarjeta";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta(0, null, null, 0, 0, 0, null , 0 ,true, 0, null);
                tarjeta.setId(rs.getInt("id_tarjeta"));
                tarjeta.setClabe(rs.getString("clabe"));
                tarjeta.setNumero(rs.getString("numero"));
                tarjeta.setMes_exp(rs.getInt("mes_exp"));
                tarjeta.setAño_exp(rs.getInt("año_exp"));
                tarjeta.setSaldo(rs.getDouble("saldo"));
                tarjeta.setTipo(rs.getString("tipo"));
                tarjeta.setCredito(rs.getDouble("credito"));
                tarjeta.setActivo(rs.getBoolean("activo"));
                tarjeta.setId_user(rs.getInt("id_user"));

                lista.add(tarjeta);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return lista;
    }

    @Override
    public Tarjeta buscar(int id) {
        Tarjeta tarjeta = null;

        try {
            Connection con = Conexion.conectar();
            String sql = "SELECT * FROM tarjetas WHERE id_tarjeta = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tarjeta = new Tarjeta(0, null, null, 0, 0, 0, null , 0 ,true, 0, null);
                tarjeta.setId(rs.getInt("id_tarjeta"));
                tarjeta.setClabe(rs.getString("clabe"));
                tarjeta.setNumero(rs.getString("numero"));
                tarjeta.setMes_exp(rs.getInt("mes_exp"));
                tarjeta.setAño_exp(rs.getInt("año_exp"));
                tarjeta.setSaldo(rs.getDouble("saldo"));
                tarjeta.setTipo(rs.getString("tipo"));
                tarjeta.setCredito(rs.getDouble("credito"));
                tarjeta.setActivo(rs.getBoolean("activo"));
                tarjeta.setId_user(rs.getInt("id_user"));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return tarjeta;
    }
    @Override
    public boolean eliminar(int id) {
        try {
            Connection con = Conexion.conectar();
            if (con == null) return false;

            String sql = "DELETE FROM tarjetas WHERE id_tarjeta = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return false;
        }
    }

    public boolean tieneTarjetas(int idUser) {
        try {
            Connection con = Conexion.conectar();
            String sql = "SELECT COUNT(*) FROM tarjetas WHERE id_user = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return false;
    }

    public boolean actualizarEstado(Tarjeta tarjeta){
        try {
            Connection con = Conexion.conectar();
            String sql = "UPDATE tarjetas SET activo = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, tarjeta.isActivo());
            ps.setInt(2, tarjeta.getId());

            return ps.executeUpdate() > 0;

        } catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }
}
