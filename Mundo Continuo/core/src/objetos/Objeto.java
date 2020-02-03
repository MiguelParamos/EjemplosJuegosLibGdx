package objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import escuchadores.EscuchadorJugador;

public abstract class Objeto extends Actor {
    protected Sprite sprite;

    public Objeto(String rutaTextura){
        sprite=new Sprite(new Texture(rutaTextura));
        sprite.setBounds(0,0, Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
    }

    /**
     * Constructor con todos los parámetros de objeto
     * @param rutaTextura la textura que usamos
     * @param x posicion x inicial
     * @param y posicion y inicial
     * @param w anchura inicial
     * @param h altura inicial
     */
    public Objeto(String rutaTextura,float x,float y,float w,float h) {
        //Cambio Posición del Sprite
        sprite=new Sprite(new Texture(rutaTextura));
        sprite.setBounds(x,y, w,h);
        this.setPosition(x,y); //Cambio posición del actor
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.setPosition(getX(),getY());
        sprite.setScale(this.getScaleX(),getScaleY());
        sprite.setRotation(this.getRotation());
        sprite.setColor(getColor().r,getColor().g,getColor().b,getColor().a);
        sprite.draw(batch);
    }
}
