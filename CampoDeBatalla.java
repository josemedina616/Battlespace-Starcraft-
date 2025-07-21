import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class CampoDeBatalla {
    private final int filas;
    private final int columnas;
    private final String[][] matriz;
    private final List<Nave> naves;
    private final List<Enemigo> enemigos;
    private final List<Escudo> escudos = new ArrayList<>();
    private final List<Proyectil> proyectiles = new ArrayList<>();
    private final List<Acorazado> acorazados;

    public CampoDeBatalla(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new String[filas][columnas];
        this.naves = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.acorazados = new ArrayList<>();
        inicializarMatriz();
    }
    private void inicializarMatriz() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = ".";
            }
        }
    }
    public void agregarNave (Nave nave) {
        if (esPosicionValida(nave.getx(), nave.gety())) {
            naves.add(nave);
            actualizarMatriz();
        } else {
            throw new IllegalArgumentException("Posición fuera de los límites del campo");
        }
    }
    public List<Proyectil> getProyectiles() {
        return new ArrayList<>(proyectiles);
    }

    public List<Escudo> getEscudos() {
        return new ArrayList<>(escudos);
    }
    public void agregarEnemigo(Enemigo enemigo) {
        if (esPosicionValida(enemigo.getX(), enemigo.getY())) {
            enemigos.add(enemigo);
            actualizarMatriz();
        }
    }
    public void agregarProyectil(Proyectil proyectil) {
        if (esPosicionValida(proyectil.getX(), proyectil.getY())) {
            proyectiles.add(proyectil);
        }
    }
    public void agregarAcorazado(Acorazado acorazado) {
        if (esPosicionValida(acorazado.getX(), acorazado.getY())) {
            acorazados.add(acorazado);
        }
    }
    public void agregarEscudoAleatorio() {
        Escudo escudo = Escudo.generarEscudoAleatorio(filas, columnas);
        if (esPosicionValida(escudo.getX(), escudo.getY())) {
            escudos.add(escudo);
            actualizarMatriz();
        }
    }
    public void actualizarMatriz() {
        inicializarMatriz();
        for (Nave nave : naves) {
            if (!nave.estaDestruida() && esPosicionValida(nave.getx(), nave.gety())) {
                matriz[nave.gety()][nave.getx()] = nave.getSimbolo();
            }
        }
        for (Enemigo enemigo : enemigos) {
            if (!enemigo.estaDestruido() && esPosicionValida(enemigo.getX(), enemigo.getY())) {
                matriz[enemigo.getY()][enemigo.getX()] = enemigo.getSimbolo();
            }
        }
        for (Proyectil p : proyectiles) {
            if (!p.estaFueraDePantalla(filas) && esPosicionValida(p.getX(), p.getY())) {
                matriz[p.getY()][p.getX()] = p.getSimbolo();
            }
        }
        for (Escudo escudo : escudos) {
            if (escudo.estaActivo() && esPosicionValida(escudo.getX(), escudo.getY())) {
                matriz[escudo.getY()][escudo.getX()] = escudo.getSimbolo();
            }
        }
        for (Acorazado acorazado : acorazados) {
            if (!acorazado.estaDestruido() && esPosicionValida(acorazado.getX(), acorazado.getY())) {
                matriz[acorazado.getY()][acorazado.getX()] = acorazado.getSimbolo();
            }
        }
    }
    private boolean esPosicionValida(int x, int y) {
        return y >= 0 && y < filas && x >= 0 && x < columnas;
    }
    public void moverNave (Nave nave, int nuevoX, int nuevoY) {
        if (esPosicionValida(nuevoX, nuevoY)) {
            nave.mover(nuevoX, nuevoY);
            actualizarMatriz();
        }
    }
    public void moverEnemigos() {
        enemigos.forEach(Enemigo::mover);
        enemigos.removeIf(e -> e.estaFueraDePantalla(filas));
        acorazados.forEach(Acorazado::mover);
        acorazados.forEach(a -> {
            if (a.debeDisparar()) {
                proyectiles.add(a.disparar());
            }
        });
        acorazados.removeIf(a -> a.estaFueraDePantalla(columnas) || a.estaDestruido());
    }
    public void moverProyectiles() {
        proyectiles.forEach(Proyectil::mover);
        proyectiles.removeIf(p -> p.estaFueraDePantalla(filas));
    }
    public void imprimirCampo() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void moverEscudos() {
        Iterator<Escudo> it = escudos.iterator();
        while(it.hasNext()) {
            Escudo escudo = it.next();
            int nuevaY = escudo.getY() + 1;

            if(nuevaY >= filas) {
                it.remove();
            } else {
                escudo.setY(nuevaY);
            }
        }
        actualizarMatriz();
    }
    public List<Nave> getNaves() {
        return new ArrayList<>(naves);
    }
    public List<Enemigo> getEnemigos() { return new ArrayList<>(enemigos); }
    public List<Acorazado> getAcorazados() {
        return new ArrayList<>(acorazados);
    }}