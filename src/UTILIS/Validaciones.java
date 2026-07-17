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

    public static boolean correoValido(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
