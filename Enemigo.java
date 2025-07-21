public class Enemigo {
    private int x;
    private int y;
    private int salud;
    private final int velocidad;
    private static final String simbolo="@";
    public Enemigo(int x, int y, int velocidad,int salud) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.salud = salud;
    }
    public void mover() {
        y += velocidad;
    }
    public boolean estaDestruido() {
        return salud <= 0;
    }
    public void recibirDaño(int cantidad) {
        salud -= cantidad;
        if (salud < 0) salud = 0;
    }
    public boolean estaFueraDePantalla(int alturaPantalla) {
        return y >= alturaPantalla;
    }
    //Solo Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public String getSimbolo() { return simbolo; }
    public int getVelocidad() { return velocidad; }
    public int getSalud() { return salud; }
    public String toString() {
        return "Enemigo[x=" + x + ", y=" + y + ", vel=" + velocidad + "]";
    }
}