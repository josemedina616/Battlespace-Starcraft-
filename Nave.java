public class Nave {
    private int x;
    private int y;
    private int salud;
    private int ataque;
    private final int velocidadProyectil;
    private final int dañoProyectil;
    private final String simbolo;
    private boolean tieneEscudo;
    private final int direccion;
    public Nave (int x, int y, int salud, int ataque, int velocidadProyectil, int dañoProyectil){
        this.x=x;
        this.y=y;
        this.salud=salud;
        this.ataque=ataque;
        this.simbolo="A";
        this.velocidadProyectil = velocidadProyectil;
        this.dañoProyectil = dañoProyectil;
        this.tieneEscudo = false;
        this.direccion =1 ;
    }
    public void mover() {
        y -= velocidadProyectil * direccion;
    }
    public void mover (int x2, int y2){
        this.x= x2 ;
        this.y= y2 ;
    }
    public void recibirDaño(int cantidad){
        if (tieneEscudo) {
            tieneEscudo = false;
            System.out.println("¡Escudo activo! Daño bloqueado.");
        } else {
            salud -= cantidad;
            if (salud < 0) salud = 0;
            System.out.println("¡Daño recibido! Salud restante: " + salud);
        }
    }
    public Proyectil disparar() {
        // Dispara hacia arriba (y-1) desde la posición actual
        return new Proyectil(x, y - 1, -1, velocidadProyectil, dañoProyectil, true);
    }
    public void atacar(Enemigo objetivo){
        objetivo.recibirDaño(this.ataque);
    }
    public boolean estaDestruida() {
        return salud<=0 ;}
    public int getx(){ return x;}
    public int gety(){ return y;}
    public String getSimbolo(){ return simbolo; }
    public int getSalud(){ return salud;}
    public int getAtaque() { return ataque; }
    public boolean getTieneEscudo() {
        return tieneEscudo;
    }
    public void setTieneEscudo(boolean tieneEscudo) {
        this.tieneEscudo = tieneEscudo;
        if (tieneEscudo) {
            System.out.println("¡Escudo activado!");
        }
    }
    @Override
    public String toString() {
        return String.format("Nave[x=%d, y=%d, salud=%d, ataque=%d]", x, y, salud, ataque);
    }
}
