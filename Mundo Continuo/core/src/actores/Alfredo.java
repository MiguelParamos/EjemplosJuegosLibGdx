package actores;

public class Alfredo extends Personaje {
    public Alfredo() {
        super("jugadores/alfCalabacin.png");
        this.nombre="Alfredo";
    }

    public Alfredo(float x, float y) {
        super("jugadores/alfCalabacin.png",x,y);
        this.nombre="Alfredo";
    }
}
