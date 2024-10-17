public class Contador {
    private int contador = 0;

    public void incrementar() {
        synchronized (this) {
            contador++;
        }
    }

    public int getContador() {
        return contador;
    }
}
