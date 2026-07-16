package UTILIS;

public class Validaciones {
    public static boolean estaVacio(String texto){
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean soloLetras(String texto){
        if(estaVacio(texto))
            return false;

        return texto.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+");
    }

    public static boolean edadValida(int edad){
        return edad >= 18 && edad <= 100;
    }
}
