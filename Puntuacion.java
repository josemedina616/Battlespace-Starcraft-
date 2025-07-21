public class Puntuacion {
    private int puntos;

    public Puntuacion() {
        this.puntos = 0;
    }

    public void sumar(int cantidad) {
        puntos += cantidad;
    }

    public void restar(int cantidad) {
        puntos = Math.max(0, puntos - cantidad); // Evita puntos negativos
    }

    public void resetear() {
        puntos = 0;
    }

    public int getPuntos() {
        return puntos;
    }
}
