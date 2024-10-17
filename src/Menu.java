import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Marcos
 * @version 1.0
 * La clase Menu representa un menú textual que se puede mostrar en la consola.
 * Permite agregar opciones y generar un menú formateado para la interacción del usuario.
 */
public class Menu {
    private final String NOMBRE_MENU;
    private final ArrayList<String> OPCIONES = new ArrayList<>();
    private final String ESPACIADOR = "-";
    private final String ESPACIADOR_VERTICAL = "|";
    private final String ESPACIO = " ";
    private int longitud = 40;

    /**
     * Construye un Menu con un nombre especificado.
     *
     * @param nombreMenu el nombre del menú
     */
    public Menu(String nombreMenu) {
        this.NOMBRE_MENU = nombreMenu;
    }

    /**
     * Construye un Menu con un nombre y una longitud especificados.
     *
     * @param nombreMenu el nombre del menú
     * @param longitud la longitud del menú
     */
    public Menu(String nombreMenu, int longitud) {
        this.NOMBRE_MENU = nombreMenu;
        this.longitud = longitud;
    }

    /**
     * Genera y muestra el menú en la consola, y solicita al usuario que seleccione una opción.
     *
     * @return la opción seleccionada como un entero
     */
    public int generarMenu() {
        int opcion;
        int longitudEspaciadorVertical = ESPACIADOR_VERTICAL.length();
        String espaciadorRepetido = repetidorTexto(ESPACIADOR, longitud);
        System.out.println(espaciadorRepetido);
        // Imprime el nombre del menú centrado
        System.out.println(ESPACIADOR_VERTICAL + repetidorTexto(ESPACIO, (longitud / 2) - (NOMBRE_MENU.length() / 2) - longitudEspaciadorVertical) +
                NOMBRE_MENU + repetidorTexto(ESPACIO, (longitud / 2) - (NOMBRE_MENU.length() / 2) - longitudEspaciadorVertical - 1) + ESPACIADOR_VERTICAL);

        System.out.println(espaciadorRepetido);

        OPCIONES.forEach((nombreOpcion) -> { // Itera sobre cada opción y la imprime en el menú
            int longitudNombre = nombreOpcion.length();
            int longitudTotal = longitudNombre + longitudEspaciadorVertical;
            System.out.println(ESPACIADOR_VERTICAL + ESPACIO + (OPCIONES.indexOf(nombreOpcion) + 1) + ESPACIO + nombreOpcion +
                    repetidorTexto(ESPACIO, (longitud - longitudTotal) - 4) + ESPACIADOR_VERTICAL);
        });

        System.out.println(ESPACIADOR_VERTICAL + ESPACIO + "0" + ESPACIO + "Salir" +
                repetidorTexto(ESPACIO, longitud - 10) + ESPACIADOR_VERTICAL);

        System.out.println(espaciadorRepetido);

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una opción");
        System.out.print("->");
        try {
            opcion = sc.nextInt();
            if (opcion > OPCIONES.size() || opcion < 0) {  // Verifica si la opción es válida
                System.out.println("Debes introducir un número valido.");
                return generarMenu();
            } else if (opcion == 0) {
                System.out.println("Saliendo...");
                System.exit(0);
            }
            return opcion;
        } catch (InputMismatchException e) {
            System.out.println("Debes introducir un número valido.");
        }
        return generarMenu();
    }

    /**
     * Repite un texto dado un número especificado de veces.
     *
     * @param texto el texto a repetir
     * @param veces el número de veces que se repetirá el texto
     * @return el texto repetido
     */
    private String repetidorTexto(String texto, int veces) {
        String textoOrig = texto;
        for (int i = 0; i < veces; i++) {
            texto = texto + textoOrig;
        }
        return texto;
    }

    /**
     * Agrega una opción al menú.
     *
     * @param nombre el nombre de la opción a agregar
     * @return true si la opción se agregó correctamente
     */
    public boolean addEntrada(String nombre) {
        OPCIONES.add(nombre);
        return true;
    }
}