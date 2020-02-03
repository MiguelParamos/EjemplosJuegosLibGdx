package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import escuchadores.EscuchadorJugador;

public class Juanka extends Personaje {

    public Juanka(){
        super("jugadores/juankaAlien.png");
        this.nombre="Juanka";
    }

    public Juanka(float x,float y){
        super("jugadores/juankaAlien.png",x,y);
        this.nombre="Juanka";
    }

}
