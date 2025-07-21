public class Proyectil {
    private int x;
    private int y;
    private final int direccion;
    private final int velocidad;
    private final int daño;
    private final String simbolo;
    private final boolean esAliado;

    public Proyectil(int x, int y, int direccion, int velocidad, int daño, boolean esAliado) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.velocidad = velocidad;
        this.daño = daño;
        this.esAliado = esAliado;
        this.simbolo = direccion == 1 ? "↓" : "↑";
    }
    public void mover() {
        y += velocidad * direccion;
    }

    public boolean estaFueraDePantalla(int alturaPantalla) {
        return y < 0 || y >= alturaPantalla;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getDaño() { return daño; }
    public String getSimbolo() { return simbolo; }
    public boolean esAliado() { return esAliado; }}