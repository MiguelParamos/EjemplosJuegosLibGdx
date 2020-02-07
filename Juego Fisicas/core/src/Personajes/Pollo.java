package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class Pollo extends Actor {
    protected Sprite sprite;

    public Pollo(){
        sprite=new Sprite(new Texture("personajes/pollo.png"));
        Random r=new Random();
        int anchuraSprite=Gdx.graphics.getWidth()/10;
        int alturaSprite=Gdx.graphics.getHeight()/7;
        sprite.setBounds(r.nextInt((Gdx.graphics.getWidth()-anchuraSprite)),
                r.nextInt((Gdx.graphics.getHeight()-alturaSprite)),
                anchuraSprite,alturaSprite);
        this.setPosition(sprite.getX(),sprite.getY());
        this.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { sprite.draw(batch);
        //No necesitamos inicializar ni finalizar el batch: Se hace automáticamente.
    }

}
