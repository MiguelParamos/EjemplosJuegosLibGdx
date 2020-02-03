package actores;

public class Miguel extends Personaje {

    public Miguel() {
        super("jugadores/miguelCermuzo.png");
        this.nombre="Miguel";
    }

    public Miguel(float x, float y) {
        super("jugadores/miguelCermuzo.png",x,y);
        this.nombre="Miguel";
    }
}
