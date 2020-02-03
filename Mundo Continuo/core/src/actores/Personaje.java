package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import java.util.ArrayList;

import escuchadores.EscuchadorJugador;
import objetos.Objeto;

public abstract class Personaje extends Actor {
    protected Sprite sprite;
    protected ArrayList<Objeto> objetos;
    protected int fuerza;
    protected boolean colliding; //Nos detecta si está colisionando o no
    private int velocidad;
    protected String nombre;
    private int moving; //Indica si mi personaje se mueve o no.



    public Personaje(String rutaTextura){
        this.moving=-1;
        velocidad=10;
        fuerza=100;
        colliding=false;
        objetos=new ArrayList<Objeto>();
        sprite=new Sprite(new Texture(rutaTextura));
        sprite.setBounds(0,0, Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        this.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
        addListener(new EscuchadorJugador(this));
    }


    public Personaje(String rutaTextura, float x, float y) {
        //Cambio Posición del Sprite
        this.moving=-1;
        sprite=new Sprite(new Texture(rutaTextura));
        fuerza=100;
        velocidad=10;
        objetos=new ArrayList<Objeto>();
        sprite.setBounds(x,y, Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        this.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        this.setPosition(x,y); //Cambio posición del actor
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
        addListener(new EscuchadorJugador(this));
    }



    public String getNombre(){
        return nombre;
    }

    public void cambiarVelocidad(int velocidad) {
        this.velocidad += velocidad;
    }

    public int getVelocidad(){
        return velocidad;
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

    public Rectangle getHitBox(){
        return sprite.getBoundingRectangle();
    }

    public boolean checkCollision(Objeto c){
        boolean overlaps=getHitBox().overlaps(c.getHitBox());
        if(overlaps&&colliding==false){
            colliding=true;
            Gdx.app.log("Colisionando","con "+c.getClass().getName());
        }else if(!overlaps){
            colliding=false;
        }
        return colliding;
    }

    public int getMoving(){
        return moving;
    }

    public void setMoving(int moving) {
        this.moving = moving;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        switch (moving){
            case 0:
                //  moveUp(delta);
                break;
            case 1:
                //moveRight(delta);
                break;
            case 2:
               // moveDown(delta);
                break;
            case 3:
                moveLeft(delta);
                break;
        }
    }

    public void moveLeft(float delta) {
        if(getX()<= -getWidth()){
            setX(Gdx.graphics.getWidth());
        }else {
            MoveByAction moveLeftAction = new MoveByAction();
            moveLeftAction.setAmount(-this.velocidad, 0);
            moveLeftAction.setDuration(delta);
            addAction(moveLeftAction);
        }}

}
