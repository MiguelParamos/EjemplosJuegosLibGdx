package actores;

public class Antonio extends Personaje {
    public Antonio() {
        super("jugadores/antonioAsimila.png");
        this.nombre="Antonio";
    }

    public Antonio( float x, float y) {
        super("jugadores/antonioAsimila.png",x,y);
        this.nombre="Antonio";
    }
}
