import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Colisionador {
    public static void verificarColisiones(List<Nave> naves, List<Enemigo> enemigos,
                                           List<Proyectil> proyectiles, List<Escudo> escudos,
                                           Puntuacion puntuacion, List<Acorazado> acorazados) {
        for (Nave nave : naves) {
            Iterator<Escudo> escIt = escudos.iterator();
            while (escIt.hasNext()) {
                Escudo escudo = escIt.next();
                if (escudo.estaActivo() && nave.getx() == escudo.getX() && nave.gety() == escudo.getY()) {
                    nave.setTieneEscudo(true);
                    escIt.remove();
                    break;
                }
            }
        }
        for (Nave nave : naves) {
            Iterator<Enemigo> enemIt = enemigos.iterator();
            while (enemIt.hasNext()) {
                Enemigo enemigo = enemIt.next();
                if (nave.getx() == enemigo.getX() && nave.gety() == enemigo.getY()) {
                    if (nave.getTieneEscudo()) {
                        nave.setTieneEscudo(false);
                    } else {
                        nave.recibirDaño(20);
                    }
                    enemIt.remove();
                    break;
                }
            }
            Iterator<Acorazado> acorIt = acorazados.iterator();
            while (acorIt.hasNext()) {
                Acorazado acorazado = acorIt.next();
                if (nave.getx() == acorazado.getX() && nave.gety() == acorazado.getY()) {
                    if (nave.getTieneEscudo()) {
                        nave.setTieneEscudo(false);
                    } else {
                        nave.recibirDaño(30);
                    }
                    acorIt.remove();
                    break;
                }
            }
        }
        List<Proyectil> proyectilesAEliminar = new ArrayList<>();

        for (Proyectil proyectil : new ArrayList<>(proyectiles)) {
            boolean proyectilEliminado = false;
            if (proyectil.esAliado()) {
                Iterator<Enemigo> enemIt = enemigos.iterator();
                while (enemIt.hasNext() && !proyectilEliminado) {
                    Enemigo enemigo = enemIt.next();
                    if (proyectil.getX() == enemigo.getX() && proyectil.getY() == enemigo.getY()) {
                        enemigo.recibirDaño(proyectil.getDaño());
                        proyectilesAEliminar.add(proyectil);
                        proyectilEliminado = true;
                        if (enemigo.estaDestruido()) {
                            enemIt.remove();
                            puntuacion.sumar(10);
                        }
                    }
                }
                Iterator<Acorazado> acorIt = acorazados.iterator();
                while (acorIt.hasNext() && !proyectilEliminado) {
                    Acorazado acorazado = acorIt.next();
                    if (proyectil.getX() == acorazado.getX() && proyectil.getY() == acorazado.getY()) {
                        acorazado.recibirDanio(proyectil.getDaño());
                        proyectilesAEliminar.add(proyectil);
                        proyectilEliminado = true;
                        if (acorazado.estaDestruido()) {
                            acorIt.remove();
                            puntuacion.sumar(20);
                        }
                    }
                }
                Iterator<Escudo> escIt = escudos.iterator();
                while (escIt.hasNext() && !proyectilEliminado) {
                    Escudo escudo = escIt.next();
                    if (proyectil.getX() == escudo.getX() && proyectil.getY() == escudo.getY()) {
                        proyectilesAEliminar.add(proyectil);
                        proyectilEliminado = true;
                    }
                }
            } else {
                for (Nave nave : naves) {
                    if (!proyectilEliminado && proyectil.getX() == nave.getx() && proyectil.getY() == nave.gety()) {
                        if (nave.getTieneEscudo()) {
                            nave.setTieneEscudo(false);
                        } else {
                            nave.recibirDaño(proyectil.getDaño());
                        }
                        proyectilesAEliminar.add(proyectil);
                        proyectilEliminado = true;
                    }
                }
                Iterator<Escudo> escIt = escudos.iterator();
                while (escIt.hasNext() && !proyectilEliminado) {
                    Escudo escudo = escIt.next();
                    if (proyectil.getX() == escudo.getX() && proyectil.getY() == escudo.getY()) {
                        proyectilesAEliminar.add(proyectil);
                        proyectilEliminado = true;
                        escudo.absorberDaño();
                        if (!escudo.estaActivo()) {
                            escIt.remove();
                        }
                    }
                }
            }
            if (!proyectilEliminado) {
                for (Proyectil otroProyectil : new ArrayList<>(proyectiles)) {
                    if (proyectil != otroProyectil &&
                            proyectil.getX() == otroProyectil.getX() &&
                            proyectil.getY() == otroProyectil.getY() &&
                            proyectil.esAliado() != otroProyectil.esAliado()) {

                        proyectilesAEliminar.add(proyectil);
                        proyectilesAEliminar.add(otroProyectil);
                        proyectilEliminado = true;
                        break;
                    }
                }
            }
        }
        proyectiles.removeAll(proyectilesAEliminar);
    }
}