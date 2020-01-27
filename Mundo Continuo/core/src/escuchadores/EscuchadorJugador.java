package escuchadores;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import actores.Juanka;

public class EscuchadorJugador extends InputListener {
    Juanka jugador;

    public EscuchadorJugador(Juanka j){
        this.jugador=j;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode){
            case Input.Keys.RIGHT:
                MoveByAction mba=new MoveByAction();
                mba.setDuration(1);
                mba.setAmount(100,0);
                jugador.addAction(mba);
                break;
        }
        return super.keyDown(event, keycode);

    }

}
