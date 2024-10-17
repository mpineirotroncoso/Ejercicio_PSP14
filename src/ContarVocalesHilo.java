public class ContarVocalesHilo extends Thread{
    private char vocal;
    private String texto;
    private Contador contador;

    public ContarVocalesHilo(Contador contador, char vocal, String texto) {
        this.vocal = vocal;
        this.texto = texto;
        this.contador = contador;
    }

    @Override
    public void run(){
        int numero = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == vocal ) {
                numero++;
                contador.incrementar();
            }
        }
        // System.out.println("La vocal " + vocal + " aparece " + numero + " veces");
    }
}
