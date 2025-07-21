import java.util.Random;

public class Escudo {
    private int x;
    private int y;
    private boolean activo;
    private final String simbolo;
    private final Random rand;

    public Escudo(int x, int y) {
        this.x = x;
        this.y = y;
        this.activo = true;
        this.simbolo = "0";
        this.rand = new Random();
    }
    public static Escudo generarEscudoAleatorio(int maxFilas, int maxColumnas) {
        Random rand = new Random();
        int x = rand.nextInt(maxColumnas);
        int y = rand.nextInt(maxFilas);
        return new Escudo(+x, +y);
    }
    public boolean absorberDaño() {
        if (activo) {
            activo = false;
            return true;
        }
        return false;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getSimbolo() { return simbolo; }
    public boolean estaActivo() { return activo; }
    public void setY(int y) {
        this.y = y;
    }
}
