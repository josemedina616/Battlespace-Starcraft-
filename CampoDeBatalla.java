import java.util.ArrayList;
import java.util.List;

public class CampoDeBatalla {
    private final int filas;
    private final int columnas;
    private final String[][] matriz;
    private final List<Nave> naves;

    public CampoDeBatalla(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new String[filas][columnas];
        this.naves = new ArrayList<>();
        inicializarMatriz();
    }
    private void inicializarMatriz() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = "·"; // Símbolo de espacio vacío
            }
        }
    }
    public void agregarNave(Nave nave) {
        if (esPosicionValida(nave.getx(), nave.gety())) {
            naves.add(nave);
            actualizarMatriz();
        } else {
            throw new IllegalArgumentException("Posición fuera de los límites del campo");
        }
    }
    private void actualizarMatriz() {
        inicializarMatriz(); // Limpia el campo antes de actualizar
        for (Nave nave : naves) {
            if (!nave.estaDestruida()) {
                matriz[nave.getx()][nave.gety()] = nave.getSimbolo();
            }
        }
    }
    private boolean esPosicionValida(int x, int y) {
        return x >= 0 && x < filas && y >= 0 && y < columnas;
    }
    public void moverNave(Nave nave, int nuevoX, int nuevoY) {
        if (esPosicionValida(nuevoX, nuevoY)) {
            nave.mover(nuevoX, nuevoY);
            actualizarMatriz();
        }
    }
    public void realizarAtaque(Nave atacante, Nave objetivo) {
        if (naves.contains(atacante) && naves.contains(objetivo)) {
            atacante.atacar(objetivo);
            if (objetivo.estaDestruida()) {
                System.out.println("¡Nave enemiga destruida!");
            }
            actualizarMatriz();
        }
    }
    public void imprimirCampo() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    public List<Nave> getNaves() {
        return new ArrayList<>(naves); // Devuelve una copia para evitar modificaciones externas
    }
}