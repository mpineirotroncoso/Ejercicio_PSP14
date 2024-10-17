import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Menu menu = new Menu("Ejercicio 14");
        menu.addEntrada("Contar vocales (Scanner)");
        menu.addEntrada("Contar vocales (Fichero)");

        while (true) {
            int opcion = menu.generarMenu();
            switch (opcion) {
                case 1:
                    contarVocalesScanner();
                    break;
                case 2:
                    contarVocalesFichero();
            }
        }
    }

    private static void contarVocalesScanner() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una cadena de texto");
        System.out.print("->");
        String texto = sc.nextLine();
        iniciarHilos(texto);
    }

    private static void contarVocalesFichero() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del fichero a leer");
        System.out.print("->");
        String ruta = sc.nextLine();

        try {
            String texto = Files.readString(Paths.get(ruta));
            iniciarHilos(texto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void iniciarHilos(String texto) {
        Contador contador = new Contador();

        ContarVocalesHilo hiloA = new ContarVocalesHilo(contador, 'a', texto);
        ContarVocalesHilo hiloE = new ContarVocalesHilo(contador, 'e', texto);
        ContarVocalesHilo hiloI = new ContarVocalesHilo(contador, 'i', texto);
        ContarVocalesHilo hiloO = new ContarVocalesHilo(contador, 'o', texto);
        ContarVocalesHilo hiloU = new ContarVocalesHilo(contador, 'u', texto);
        hiloA.start();
        hiloE.start();
        hiloI.start();
        hiloO.start();
        hiloU.start();

        try {
            hiloA.join();
            hiloE.join();
            hiloI.join();
            hiloO.join();
            hiloU.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("El total de vocales es: " + contador.getContador());
    }
}