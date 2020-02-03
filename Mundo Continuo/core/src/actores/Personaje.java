package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import escuchadores.EscuchadorJugador;
import objetos.Objeto;

public abstract class Personaje extends Actor {
    protected Sprite sprite;
    protected ArrayList<Objeto> objetos;
    protected int fuerza;

    public Personaje(String rutaTextura){
        fuerza=100;
        objetos=new ArrayList<Objeto>();
        sprite=new Sprite(new Texture(rutaTextura));
        sprite.setBounds(0,0, Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
        addListener(new EscuchadorJugador(this));
    }

    public int getFuerza(){
        return fuerza;
    }

    public void cambiarFuerza(int f){
        fuerza+=f;
    }

    public void addObjeto(Objeto o){
        this.objetos.add(o);
    }

    public Personaje(String rutaTextura, float x, float y) {
        //Cambio Posición del Sprite
        sprite=new Sprite(new Texture(rutaTextura));
        sprite.setBounds(x,y, Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        this.setPosition(x,y); //Cambio posición del actor
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
        addListener(new EscuchadorJugador(this));
    }

    public void moverAPixel(float x,float y){
        this.setPosition(x,y);
        sprite.setPosition(x,y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.setPosition(getX(),getY());
        sprite.setScale(this.getScaleX(),getScaleY());
        sprite.setRotation(this.getRotation());
        sprite.setColor(getColor().r,getColor().g,getColor().b,getColor().a);
        sprite.draw(batch);
        //para cada objeto
        for (Objeto o:this.objetos){
            //lo muevo con el sprite
            o.moverA(sprite.getX(),sprite.getY());
            //lo dibujo
            o.draw(batch,parentAlpha);
        }
    }

}
