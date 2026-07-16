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
    static TarjetaDAO daot = new TarjetaDAO();
    //esto es una idea, nose lo puedes quitar
    public static void main(String[] args) {
        int opcion=0;
        do {
            menuPrincipal();
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
                        seleccionarUsuario();
                        break;

                    case 5:
                        eliminarUsuario();
                        break;

                    case 6:
                        System.out.println("\nSALIENDO...");
                        break;

                    default:
                        System.out.println("\nOpción inválida.");
                }
            } catch (Exception e) {
                System.out.println("\nDebe ingresar un número.");
            }
            if (opcion != 6) {
                System.out.println("\nPresione ENTER para continuar...");
                sc.nextLine();
            }
        } while (opcion != 6);
    }

    public static void menuPrincipal() {
        System.out.println();
        System.out.println("===========================================");
        System.out.println("       MENU PRINCIPAL");
        System.out.println("===========================================");
        System.out.println("1. Mostrar usuarios");
        System.out.println("2. Registrar usuarios");
        System.out.println("3. Buscar usuarios");
        System.out.println("4. Seleccionar usuario");
        System.out.println("5. Eliminar usuarios");
        System.out.println("6. Salir");
        System.out.println("===========================================");
        System.out.print("Seleccione una opción: ");
    }
    public static void seleccionarUsuario() {
        String user;
        ArrayList<Usuario> lista = dao.listar();
        int id = 0;
        boolean correcto = false;

        if (lista.isEmpty()) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }
        System.out.println();
        System.out.printf("%-5s %-25s %-35s %-35s %-10s%n",
                "ID",
                "NOMBRE",
                "APELLIDO PATERNO",
                "APELLIDO MATERNO",
                "ACTIVO");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Usuario u : lista) {
            System.out.printf("%-5s %-25s %-35s %-35s %-10s%n",
                    u.getId(),
                    u.getNombre(),
                    u.getApellido_p(),
                    u.getApellido_m(),
                    u.isActivo() ? "Si" : "No");
        }
        while (!correcto) {
            try {
                System.out.println();
                System.out.println("Ingrese la id del usuario del usuario al que quiere entrar");
                id = Integer.parseInt(sc.nextLine());
                correcto = true;
            } catch (Exception e) {
                System.out.println("Debe ingresar un número.");
            }
        }

        Usuario usuario = dao.buscar(id);

        if (usuario == null) {
            System.out.println("\nUsuario no encontrado.");
            return;
        }
        if (!usuario.isActivo()) {
            System.out.println("El usuario está inactivo.");
            return;
        }
        System.out.println();
        System.out.println("ID: " + usuario.getId());
        System.out.println("USUARIO: " + usuario.getNombre() + " " + usuario.getApellido_p() + " " + usuario.getApellido_m());
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Ingrese la contraseña del usario:");
        String contra = sc.nextLine();

        int contador = 1;
        while (contador <=3) {
            if (contra.equals(usuario.getContra())) {
                System.out.println("CONTRASEÑA CORRECTA");
                System.out.println("INGRESANDO...");
                user = usuario.getNombre() + usuario.getApellido_p() + usuario.getApellido_m();
                menuTarjeta(usuario);
                break;
            } else {
                System.out.println("CONTRASEÑA INCORRECTA");
                System.out.println("INTENTO: "+contador+ " de 3");
                System.out.println("INGRESELA DE NUEVO: ");
                contra = sc.nextLine();
                contador++;
            }
        }
        if (contador == 3) {
            System.out.println("LIMITE DE INTENTOS ALCANZADO");
            System.out.println("REGRESANDO AL MENU PRINCIPAL");
        }

    }

    public static void menuTarjeta(Usuario usuario) {
        boolean seguir = true;
        while(seguir) {
            try {
                System.out.println();
                System.out.println("=========================================");
                System.out.println("           MENU DE TARJETAS");
                System.out.println("========================================");
                System.out.println("1. Ver tarjetas");
                System.out.println("2. Agregar tarjeta");
                System.out.println("3. Buscar tarjeta");
                System.out.println("4. Eliminar tarjeta");
                System.out.println("5. Regresar");
                System.out.println("Ingrese su opcion:");
                int opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        mostrarTarjetas(usuario.getId());
                        break;
                    case 2:
                        registrarTarjeta(usuario);
                        break;
                    case 3:
                        buscarTarjeta(usuario);
                        break;
                    case 4:
                        eliminarTarjeta(usuario);
                        break;
                    case 5:
                        System.out.println("Regresando...");
                        seguir = false;
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Ingrese solo numeros");
            }
        }
    }

    public static void mostrarUsuario() {
        ArrayList<Usuario> lista = dao.listar();

        if (lista.isEmpty()) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }
        System.out.println();
        System.out.printf("%-5s %-25s %-35s %-35s %-3s %-55s %-10s%n",
                "ID",
                "NOMBRE",
                "APELLIDO PATERNO",
                "APELIIDO MATERNO",
                "EDAD",
                "EMAIL",
                "ACTIVO");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Usuario usuario : lista) {
            System.out.printf("%-5d %-25s %-35s %-35s %-3d %-55s %-10s%n",
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
        ArrayList<Usuario> lista = dao.listar();

        if (lista.isEmpty()) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }

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
        ArrayList<Usuario> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }

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
        if (daot.tieneTarjetas(id)) {
            System.out.println("No es posible eliminar el usuario porque tiene tarjetas registradas.");
            return;
        }
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
    /*public static void menuTarjetas() {
            System.out.println("\n------------------------------------");
            System.out.println("      SUBMENÚ: GESTIÓN DE TARJETAS");
            System.out.println("------------------------------------");
            System.out.println("1. Registrar Tarjeta (Asociar a un ID)");
            System.out.println("2. Consultar Tarjeta por Número/CLABE");
            System.out.println("3. Mostrar Tarjetas de un Usuario");
            System.out.println("4. Activar/Desactivar una Tarjeta");
            System.out.println("5. Eliminar Tarjeta");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
    }
     */

    public static void mostrarTarjetas(int idUser) {
        ArrayList<Tarjeta> lista = daot.listar(idUser);

        if (lista.isEmpty()) {
            System.out.println("\nNo hay tarjetas registradas");
            return;
        }
        System.out.println();
        System.out.printf("%-5s %-18s %-16s %-10s %-8s %-10s %-10s %-10s%n",
                "ID",
                "CLABE",
                "NUMERO",
                "FECHA_EXP",
                "TIPO",
                "SALDO",
                "CREDITO",
                "ACTIVO");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Tarjeta tarjeta : lista) {
            System.out.printf("%-5d %-18s %-16s %-10s %-8s %-12.2f %-12.2f %-10s%n",
                    tarjeta.getId(),
                    tarjeta.getClabe(),
                    "**** **** **** " + tarjeta.getNumero().substring(tarjeta.getNumero().length() - 4),
                    tarjeta.getMes_exp() + "/" + tarjeta.getAño_exp(),
                    tarjeta.getTipo(),
                    tarjeta.getSaldo(),
                    tarjeta.getCredito(),
                    tarjeta.isActivo() ? "SI" : "NO");
        }
    }

    public static void registrarTarjeta(Usuario usuario) {
        Tarjeta tarjeta = new Tarjeta();

        System.out.print("Tipo de tarjeta (D/C): ");
        String tipo = sc.nextLine().toUpperCase();

        if (!tipo.equals("D") && !tipo.equals("C")) {
            System.out.println("Tipo inválido.");
            return;
        }

        tarjeta.setTipo(tipo);
        try {
            if (tipo.equals("D")) {
                System.out.print("Saldo inicial: ");
                tarjeta.setSaldo(Double.parseDouble(sc.nextLine()));
                tarjeta.setCredito(0);
            } else {
                System.out.print("Límite de crédito: ");
                tarjeta.setCredito(Double.parseDouble(sc.nextLine()));
                tarjeta.setSaldo(0);
            }
            tarjeta.setId_user(usuario.getId());

            if (daot.guardar(tarjeta)) {
                System.out.println("Tarjeta registrada correctamente.");
            } else {
                System.out.println("No se pudo registrar la tarjeta.");
            }

        } catch (Exception e) {
            System.out.println("Dato inválido.");
        }
    }

    public static void buscarTarjeta(Usuario usuario) {
        mostrarTarjetas(usuario.getId());

        System.out.print("\nIngrese el ID de la tarjeta: ");
        int id = Integer.parseInt(sc.nextLine());

        Tarjeta tarjeta = daot.buscar(id);

        if (tarjeta == null || tarjeta.getId_user() != usuario.getId()) {
            System.out.println("Tarjeta no encontrada.");
            return;
        }

        System.out.println("\n========== TARJETA ==========");
        System.out.println("ID: " + tarjeta.getId());
        System.out.println("CLABE: " + tarjeta.getClabe());
        System.out.println("Número: **** **** **** " + tarjeta.getNumero().substring(tarjeta.getNumero().length()-4));
        System.out.println("Expira: " + tarjeta.getMes_exp() + "/" + tarjeta.getAño_exp());
        System.out.println("Tipo: " + tarjeta.getTipo());
        System.out.println("Saldo: $" + tarjeta.getSaldo());
        System.out.println("Crédito: $" + tarjeta.getCredito());
        System.out.println("Activo: " + (tarjeta.isActivo() ? "SI" : "NO"));
    }

    public static void eliminarTarjeta(Usuario usuario) {
        mostrarTarjetas(usuario.getId());

        System.out.print("\nIngrese el ID de la tarjeta: ");
        int id = Integer.parseInt(sc.nextLine());

        Tarjeta tarjeta = daot.buscar(id);
        if (tarjeta == null || tarjeta.getId_user() != usuario.getId()) {
            System.out.println("Tarjeta no encontrada.");
            return;
        }

        System.out.print("¿Eliminar la tarjeta? (S/N): ");
        String resp = sc.nextLine().toUpperCase();

        if (!resp.equals("S")) {
            System.out.println("Operación cancelada.");
            return;
        }

        if (daot.eliminar(id)) {
            System.out.println("Tarjeta eliminada.");
        } else {
            System.out.println("No fue posible eliminar la tarjeta.");
        }
    }
    /*public static void registrarTarjeta() {
        //racion usuario tarjetas
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
    */

}
