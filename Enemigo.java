public class Enemigo {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;

    public Enemigo(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void mover() {
        y += velocidad;
    }

    public boolean estaFueraDePantalla(int alturaPantalla) {
        return y > alturaPantalla;
    }

    public String toString() {
        return "Enemigo[x=" + x + ", y=" + y + ", vel=" + velocidad + "]";
    }
}