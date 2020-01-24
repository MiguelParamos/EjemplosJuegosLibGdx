package escuchadores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import personajes.Jugador;

public class AndroidJugador implements GestureDetector.GestureListener {
    Jugador jugador;

    public AndroidJugador(Jugador j){
        jugador=j;
    }
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(x< Gdx.graphics.getWidth()/3){
            jugador.mover('l');
        }
        if(x>Gdx.graphics.getWidth()/3*2){
            jugador.mover('r');
        }
        if(y>Gdx.graphics.getHeight()/3*2){
            jugador.mover('d');
        }
        if(y< Gdx.graphics.getHeight()/3){
            jugador.mover('u');
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        /*jugador.getCamara().translate(-deltaX * 16 * jugador.getCamara().zoom,
                deltaY * 16 * jugador.getCamara().zoom); //Traslada la cámara dependiendo de deltaX y deltaY (Desplazamiento en píxeles), unitScale, que nos permite tener la cuenta en tiles, no en pixeles, y el zoom.
        jugador.getCamara().update(); //y la actualizamos*/
        return true; //impedimos que se propague el evento.

    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        jugador.getCamara().zoom = jugador.getCamara().zoom *
                ( (initialDistance) / (distance)); //Modificamos el zoom como una proporción de la distancia entre nuestros dedos.
        if (jugador.getCamara().zoom <0.5f){jugador.getCamara().zoom=0.5f;} //Nos aseguramos de que el zoom nunca sea menor que 0.1
        if (jugador.getCamara().zoom >1){jugador.getCamara().zoom=1;} //Nos aseguramos de que el zoom nunca sea mayor que 1
        jugador.getCamara().update(); //actualizamos la cámara

        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
