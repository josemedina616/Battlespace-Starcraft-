import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final int FILAS = 15;
    private static final int COLUMNAS = 15;
    private static final Random rand = new Random();
    public static void main(String[] args) {
        Puntuacion puntuacion = new Puntuacion();
        Scanner scanner = new Scanner(System.in);
        CampoDeBatalla
                campo = new CampoDeBatalla(FILAS, COLUMNAS);
        GeneradorEnemigos generador = new GeneradorEnemigos(COLUMNAS, FILAS);
        Nave naveJugador = new Nave(COLUMNAS / 2, FILAS - 1, 100, 10, 1, 20);
        campo.agregarNave(naveJugador);
        System.out.println("STARCRAFT BATTLE SPACE");
        System.out.println("Controles: A (izquierda), D (derecha),W (arriba),S (abajo) P (disparar)");
        System.out.println("Presiona Enter");
        scanner.nextLine();
        boolean juegoActivo = true;
        int turno = 0;
        while (juegoActivo) {
            if (turno % 2 == 0 && rand.nextDouble() < 0.2) {
                campo.agregarEnemigo(generador.generarEnemigo());
            }
            if (turno % 2 == 0 && rand.nextDouble() < 0.2) {
                campo.agregarEscudoAleatorio();
            }
            if (rand.nextDouble() < 0.3) {
                campo.agregarAcorazado(generador.generarAcorazado());
            }
            System.out.println("\nTurno: " + turno);
            System.out.println("Salud: " + naveJugador.getSalud() +
                    " | Escudo: " + (naveJugador.getTieneEscudo() ? "ACTIVO" : "INACTIVO"));
            campo.imprimirCampo();
            System.out.println("Controles: A (izquierda), D (derecha), W (arriba), S (abajo), P (disparar)");
            String input = scanner.nextLine().toUpperCase();

            int nuevoX = naveJugador.getx();
            int nuevoY = naveJugador.gety();
            switch (input) {
                case "A":
                    nuevoX = Math.max(0, naveJugador.getx() - 1);
                    break;
                case "D":
                    nuevoX = Math.min(COLUMNAS - 1, naveJugador.getx() + 1);
                    break;
                case "W":
                    nuevoY = Math.max(0, naveJugador.gety() - 1);
                    break;
                case "S":
                    nuevoY = Math.min(FILAS - 1, naveJugador.gety() + 1);
                    break;
                case "P":
                    Proyectil nuevoProyectil = naveJugador.disparar();
                    campo.agregarProyectil(nuevoProyectil);
                    System.out.println("¡Disparaste!");
                    break;
                default:
                    System.out.println("Entrada no válida");
            }
            if (input.equals("A") || input.equals("D") || input.equals("W") || input.equals("S")) {
                campo.moverNave(naveJugador, nuevoX, nuevoY);
            }
            campo.moverEnemigos();
            campo.moverProyectiles();
            campo.moverEscudos();
            campo.actualizarMatriz();
            Colisionador.verificarColisiones(
                    campo.getNaves(),
                    campo.getEnemigos(),
                    campo.getProyectiles(),
                    campo.getEscudos(),
                    puntuacion,
                    campo.getAcorazados()
            );
            campo.actualizarMatriz();
            if (naveJugador.estaDestruida()) {
                juegoActivo = false;
                System.out.println("\n PERDISTE");
                System.out.println("\n¡PERDISTE! Puntuación final: " + puntuacion.getPuntos());
            }
            if (turno >= 100) {
                juegoActivo = false;
                System.out.println("\n GANASTE ");
                System.out.println("\n¡GANASTE! Puntuación final: " + puntuacion.getPuntos());
            }
            turno++;
        }
        scanner.close();}}