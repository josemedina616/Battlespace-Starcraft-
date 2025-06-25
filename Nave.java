public class Nave {
    private int x;
    private int y;
    private int salud;
    private int ataque;
    private final String simbolo;
     public Nave (int x, int y, int salud, int ataque){
         this.x=x;
         this.y=y;
         this.salud=salud;
         this.ataque=ataque;
         this.simbolo="A";
     }
     public void mover (int x2, int y2){
         this.x= x2 ;
         this.y= y2 ;
     }
     public void dañar(int cantidad){
         salud-= cantidad;
         if (salud<0) salud = 0;
     }
     public void atacar(Nave objetivo){
         objetivo.dañar(this.ataque);
     }
     public boolean estaDestruida() {
         return salud<=0 ;
     }
     public int getx(){ return x;}
    public int gety(){ return y;}
    public String getSimbolo(){ return simbolo; }
    public int getSalud(){ return salud;}
}
