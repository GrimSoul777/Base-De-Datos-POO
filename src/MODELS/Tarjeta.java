package MODELS;

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

    public Tarjeta() {}

    public Tarjeta(int id, String clabe, String numero, int mes_exp, int año_exp, double saldo, String tipo, double credito, boolean activo) {
        this.id = id;
        this.clabe = clabe;
        this.numero = numero;
        this.mes_exp = mes_exp;
        this.año_exp = año_exp;
        this.saldo = saldo;
        this.tipo = tipo;
        this.credito = credito;
        this.activo = activo;
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
}
