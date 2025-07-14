public class Colisionador {
    public static void verificarColisiones(List<Nave> naves, List<Enemigo> enemigos, List<Proyectil> proyectiles) {
        // Colisiones nave vs enemigos (choque directo)
        for (Nave nave : naves) {
            Iterator<Enemigo> it = enemigos.iterator();
            while (it.hasNext()) {
                Enemigo e = it.next();
                if (nave.getx() == e.getX() && nave.gety() == e.getY()) {
                    nave.recibirDaño(1);
                    it.remove();
                }
            }
        }

        // Colisiones proyectiles aliados vs enemigos
        Iterator<Proyectil> pit = proyectiles.iterator();
        while (pit.hasNext()) {
            Proyectil p = pit.next();
            if (p.esAliado()) { // Solo proyectiles de la nave
                Iterator<Enemigo> eit = enemigos.iterator();
                while (eit.hasNext()) {
                    Enemigo e = eit.next();
                    if (p.getX() == e.getX() && p.getY() == e.getY()) {
                        e.recibirDaño(p.getDaño());
                        pit.remove();
                        if (e.estaDestruido()) {
                            eit.remove();
                        }
                        break;
                    }
                }
            }
        }
    }
}