public class GeneradorEnemigos {
    private final Random rand = new Random();
    private final int anchoCampo;

    public GeneradorEnemigos(int anchoCampo) {
        this.anchoCampo = anchoCampo;
    }

    public Enemigo generarEnemigo() {
        int x = rand.nextInt(anchoCampo);
        return new Enemigo(x, 0, 1, 1); // Aparece en Y=0, velocidad=1, salud=1
    }
}