package personajes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Jugador {
    Sprite sprite;
    OrthographicCamera camara; //La necesito para que me siga
    Vector3 posicionTiles;
    Batch batch;// La uso para dibujar en este batch al jugador. Podría pasarlo por constructor. Es decisión nuestra como programadoeres.

    public Jugador(OrthographicCamera camara) {
        this.sprite = new Sprite(new Texture("sprites/playerFemale.png"));
        this.camara = camara;
        posicionTiles=this.camara.position;
        batch=new SpriteBatch();
        //Como siempre estaré en el medio, voy a poner el sprite en el medio
        //Como la cámara está centrada en el medio, voy a necesitar coger el tile de ahi
        Vector3 posPixels = camara.project(
                new Vector3(camara.position.x,camara.position.y,0));
        sprite.setPosition(posPixels.x,posPixels.y);
    }

    public void dibujar(){
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void dispose(){
        batch.dispose();
    }




}
