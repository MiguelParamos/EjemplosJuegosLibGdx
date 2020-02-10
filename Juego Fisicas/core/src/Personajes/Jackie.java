package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

public class Calipo {
    protected Sprite sprite;
    private World mundo;
    private BodyDef propiedadesCuerpo;
    private Body cuerpo;
    private FixtureDef propiedadesFisicasCuerpo;


    public Pollo(World w,int posx,int posy){
        this.mundo=w;
        sprite=new Sprite(new Texture("personajes/pollo.png"));
        Random r=new Random();
        int anchuraSprite=1; //Hablamos de metros
        int alturaSprite=1; //Hablamos de metros
        sprite.setBounds(posx,
                posy,
                anchuraSprite,alturaSprite);

        this.propiedadesCuerpo= new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.DynamicBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());

        cuerpo = mundo.createBody(propiedadesCuerpo); //Creamos el cuerpo dentro del mundo con las propiedades especificadas

        propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        propiedadesFisicasCuerpo.filter.groupIndex =
                this instanceof Pollo
                        ? (short)1
                        : (short)2;
        //Recibe la media anchura y la media altura. Por algún motivo alguien decidió que eso era buena idea y para nada confuso :P
        //Asegurarse siempre de que se hace la cuenta con flotantes, no con enteros, o saltará un error
        ((PolygonShape)propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite/2f, alturaSprite/2f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);
        //Como esta línea se haga antes del setAsBox, vamos a tener un comportamiento de rotación erróneo
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);

    }

    public void seguir(OrthographicCamera camara){
        camara.position.x=this.sprite.getX();
        camara.position.y=this.sprite.getY();
    }


    public void draw(Batch batch, float parentAlpha) {
        //Esta cuenta hace falta por lo de la media altura. Ese absurdo cálculo...
        sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
        //Sprite quiere la rotación en grados, el cuerpo la da en radianes. Esta constante convierte de uno a otro.
        sprite.setRotation(MathUtils.radiansToDegrees*cuerpo.getAngle());
        sprite.draw(batch);
    }

    public void logPosition(){
        Gdx.app.log("Posicion",sprite.getX()+" : "+sprite.getY()+" || "+cuerpo.getPosition().x+" : "+cuerpo.getPosition().y);
    }

    public Body getCuerpo(){
        return cuerpo;
    }
}
