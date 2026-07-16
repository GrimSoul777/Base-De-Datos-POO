import java.util.ArrayList;
import java.util.Scanner;
import MODELS.Usuario;
import MODELS.Tarjeta;
import DAO.UsuarioDAO;
import DAO.TarjetaDAO;
import UTILIS.Validaciones;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static UsuarioDAO dao = new UsuarioDAO();

    public static void main(String[] args) {
        int opcion=0;
        do {
            menu();
            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        mostrarUsuario();
                        break;

                    case 2:
                        registrarUsuario();
                        break;

                    case 3:
                        buscarUsuario();
                        break;

                    case 4:
                        eliminarUsuario();
                        break;

                    case 5:
                        System.out.println("\nSALIENDO...");
                        break;

                    default:
                        System.out.println("\nOpción inválida.");
                }
            } catch (Exception e) {
                System.out.println("\nDebe ingresar un número.");
            }
            if (opcion != 5) {
                System.out.println("\nPresione ENTER para continuar...");
                sc.nextLine();
            }
        } while (opcion != 5);
    }

    public static void menu() {
        System.out.println();
        System.out.println("===========================================");
        System.out.println("       SISTEMA DE USUARIOS");
        System.out.println("===========================================");
        System.out.println("1. Mostrar usuarios");
        System.out.println("2. Registrar usuarios");
        System.out.println("3. Buscar usuarios");
        //System.out.println("4. Editar usuarios");
        System.out.println("4. Eliminar usuarios");
        System.out.println("5. Salir");
        System.out.println("===========================================");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarUsuario() {
        ArrayList<Usuario> lista = dao.listar();

        if (lista.isEmpty()) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }
        System.out.println();
        System.out.printf("%-5s %-15s %-30s %-8s %-10s %-10s%n",
                "ID",
                "NOMBRE",
                "APELLIDO PATERNO",
                "APELIIDO MATERNO",
                "EDAD",
                "EMAIL",
                "ACTIVO");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Usuario usuario : lista) {
            System.out.printf("%-5d %-15s %-30s %-8d %-10s %-10s%n",
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getApellido_p(),
                    usuario.getApellido_m(),
                    usuario.getEdad(),
                    usuario.getEmail(),
                    usuario.isActivo() ? "SI" : "NO");
        }
    }

    public static void registrarUsuario() {
        Usuario usuario = new Usuario();
        String nombre;

        do {
            System.out.print("Ingrese el nombre del usuario: ");
            nombre = sc.nextLine();

            if (!Validaciones.soloLetras(nombre)) {
                System.out.println("El nombre solo puede contener letras.");
            }
        } while (!Validaciones.soloLetras(nombre));

        String apellido_p;
        do {
            System.out.println("Ingrese el apellido paterno del usuario: ");
            apellido_p = sc.nextLine();

            if (!Validaciones.soloLetras(apellido_p)) {
                System.out.println("El apellido paterno solo puede contener letras.");
            }
        } while (!Validaciones.soloLetras(apellido_p));

        String apellido_m;
        do{
            System.out.println("Ingrese el apellido materno del usuario: ");
            apellido_m = sc.nextLine();

            if (!Validaciones.soloLetras(apellido_m)) {
                System.out.println("El apellido materno solo puede contener letras.");
            }
        } while (!Validaciones.soloLetras(apellido_m));

        int edad = 0;
        boolean edadValida = false;

        while (!edadValida) {
            try {
                System.out.print("Ingrese la edad del usuario: ");
                edad = Integer.parseInt(sc.nextLine());

                if (Validaciones.edadValida(edad)) {
                    edadValida = true;
                }
                else {
                    System.out.println("Edad inválida.");
                }
            }
            catch (Exception e) {
                System.out.println("Debe ingresar un número.");
            }
        }

        String email;
        do{
            System.out.println("Ingrese el email del usuario: ");
            email = sc.nextLine();
            if (Validaciones.estaVacio(email)) {
                System.out.println("El email solo puede quedar vacio");
            }
        } while (!Validaciones.estaVacio(email));

        String password;
        do{
            System.out.println("Cree una contraseña para el usuario: ");
            password = sc.nextLine();
            if (Validaciones.estaVacio(password)) {
                System.out.println("La contraseña no puede estar vacia");
            }
        } while (!Validaciones.estaVacio(password));

        usuario.setNombre(nombre);
        usuario.setApellido_p(apellido_p);
        usuario.setApellido_m(apellido_m);
        usuario.setEdad(edad);
        usuario.setEmail(email);
        usuario.setContra(password);
        usuario.setActivo(true);

        if (dao.guardar(usuario)) {
            System.out.println("\nUsuario registrado correctamente.");
        }
        else {
            System.out.println("\nNo fue posible registrar al usuario");
        }
    }

    public static void buscarUsuario() {
        int id = 0;
        boolean correcto = false;

        while (!correcto) {
            try {
                System.out.print("Ingrese el ID del usuario: ");
                id = Integer.parseInt(sc.nextLine());
                correcto = true;
            }
            catch (Exception e) {
                System.out.println("Debe ingresar un número.");
            }
        }

        Usuario usuario = dao.buscar(id);

        if (usuario == null) {
            System.out.println("\nUsuario no encontrado.");
            return;
        }

        System.out.println();
        System.out.println("==============================");
        System.out.println("      DATOS DEL USUARIO");
        System.out.println("==============================");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Apellido paterno: " + usuario.getApellido_p());
        System.out.println("Apellido materno: " + usuario.getApellido_m());
        System.out.println("Edad: " + usuario.getEdad());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Contra: " + usuario.getContra());
        System.out.println("Activo: " + (usuario.isActivo() ? "SI" : "NO"));
    }

    public static void eliminarUsuario() {
        int id = 0;

        try {
            System.out.print("Ingrese el ID del usuario a eliminar: ");
            id = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e) {
            System.out.println("ID inválido.");
            return;
        }

        Usuario usuario = dao.buscar(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println();
        System.out.println("Usuario encontrado:");
        System.out.println(usuario.getNombre());
        System.out.print("¿Está seguro de eliminarlo? (S/N): ");
        String respuesta = sc.nextLine().toUpperCase();

        if (!respuesta.equals("S")) {
            System.out.println("Operación cancelada.");
            return;
        }

        if (dao.eliminar(id)) {
            System.out.println("Usuario eliminado correctamente.");
        }
        else {
            System.out.println("No fue posible eliminar al usuario.");
        }
    }
    public static void registrarTarjeta() {
        //relacion usuario tarjetas
        TarjetaDAO tarjetaDao = new TarjetaDAO();
        Tarjeta tarjeta = new Tarjeta();
        // Tipo de tarjeta
        System.out.print("Tipo de tarjeta (D = Débito, C = Crédito): ");
        String tipo = sc.nextLine().toUpperCase();
        double saldo = 0;
        double credito = 0;
        try {
            if (tipo.equals("D")) {
                System.out.print("Saldo inicial: ");
                saldo = Double.parseDouble(sc.nextLine());
            } else if (tipo.equals("C")) {
                System.out.print("Límite de crédito: ");
                credito = Double.parseDouble(sc.nextLine());
            } else {
                System.out.println("Tipo inválido.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Número inválido.");
            return;
        }
        tarjeta.setTipo(tipo);
        tarjeta.setSaldo(saldo);
        tarjeta.setCredito(credito);
    }
}
