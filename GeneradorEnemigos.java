import java.util.Random;
public class GeneradorEnemigos {
    private final int maxColumnas;
    private final int maxFilas;
    private final Random rand;
    public GeneradorEnemigos(int columnas, int filas) {
        this.maxColumnas = columnas;
        this.maxFilas = filas;
        this.rand = new Random();
    }
    public Enemigo generarEnemigo() {
        int x = rand.nextInt(maxColumnas);
        return new Enemigo(x, 0, 1, 1);
    }
    public Acorazado generarAcorazado() {
        int x = rand.nextBoolean() ? 0 : maxColumnas - 1;
        int y = rand.nextInt(maxFilas / 3);
        int velocidad = 1;
        int salud = 30;
        return new Acorazado(x, y, 1, 30);
    }}