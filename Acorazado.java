import java.util.Random;
public class Acorazado {
    private int x;
    private int y;
    private int salud;
    private final int velocidad;
    private final String simbolo;
    private final int danoProyectil;
    private final Random rand;
    private int direccion;
    public Acorazado(int x, int y, int velocidad, int salud) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.salud = salud;
        this.simbolo = "Ω";
        this.danoProyectil = 15;
        this.rand = new Random();
        this.direccion = rand.nextBoolean() ? 1 : -1;
    }
    public void mover() {
        x += velocidad * direccion;
    }
    public Proyectil disparar() {
        return new Proyectil(x, y + 1, 1, 1, danoProyectil, false);
    }
    public boolean debeDisparar() {
        return rand.nextDouble() < 0.2;
    }
    public boolean estaFueraDePantalla(int anchoPantalla) {
        return x < 0 || x >= anchoPantalla;
    }
    public void recibirDanio(int cantidad) {
        salud -= cantidad;
    }
    public boolean estaDestruido() {
        return salud <= 0;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getSimbolo() { return simbolo; }
    public int getSalud() { return salud; }
}