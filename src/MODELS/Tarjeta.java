package MODELS;

import java.util.Random;

public class Tarjeta {
    private int id;
    private String clabe;
    private String numero;
    private int mes_exp;
    private int año_exp;
    private double saldo;
    private String tipo;
    private double credito;
    private boolean activo;
    private int id_user;
    private String fecha_exp;

    Random random = new Random();

    public Tarjeta() {}

    public Tarjeta(int id, String clabe, String numero, int mes_exp, int año_exp, double saldo, String tipo, double credito, boolean activo, int id_user,  String fecha_exp) {
        this.id = id;
        this.saldo = saldo;
        this.tipo = tipo;
        this.credito = credito;
        this.id_user = id_user;
        this.fecha_exp = fecha_exp;

        this.clabe = generarCLABE();
        this.numero = generarNumero();
        this.mes_exp = generarMes();
        this.año_exp = generarAño();
        this.activo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getMes_exp() {
        return mes_exp;
    }

    public void setMes_exp(int mes_exp) {
        this.mes_exp = mes_exp;
    }

    public int getAño_exp() {
        return año_exp;
    }

    public void setAño_exp(int año_exp) {
        this.año_exp = año_exp;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId_user() {return id_user;}

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFecha_exp() {
        return fecha_exp;
    }

    public void setFecha_exp(String fecha_exp) {
        this.fecha_exp = fecha_exp;
    }

    private String generarNumero() {
        String numero = "";
        for(int i = 0; i < 16; i++){
            numero += random.nextInt(10);
        }
        return numero;
    }

    private String generarCLABE(){
        String clabe="";
        for(int i=0;i<18;i++){
            clabe+=random.nextInt(10);
        }
        return clabe;
    }

    private int generarMes(){
        int mes = random.nextInt(12)+1;
        return mes;
    }

    private int generarAño(){
        int año = random.nextInt(6)+2026;
        return año;
    }
}
