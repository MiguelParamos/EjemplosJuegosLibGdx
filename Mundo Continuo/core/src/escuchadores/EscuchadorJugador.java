package escuchadores;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;

import actores.Juanka;

public class EscuchadorJugador extends InputListener {
    Juanka jugador;

    public EscuchadorJugador(Juanka j){
        this.jugador=j;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        RotateByAction rba=new RotateByAction();
        MoveByAction mba=new MoveByAction();
        MoveToAction mta=new MoveToAction();
        ScaleByAction sba=new ScaleByAction();
        switch (keycode){
            case Input.Keys.D:
                mba.setDuration(1);
                mba.setAmount(100,0);
                jugador.addAction(mba);
                break;
            case Input.Keys.S:
                mba.setDuration(1);
                mba.setAmount(0,-100);
                jugador.addAction(mba);
                break;
            case Input.Keys.A:
                mba.setDuration(1);
                mba.setAmount(-100,0);
                jugador.addAction(mba);
                break;
            case Input.Keys.W:
                mba.setDuration(1);
                mba.setAmount(0,100);
                jugador.addAction(mba);
                break;
            case Input.Keys.R: //Reset, volver al 0,0
                mta.setPosition(0,0);
                mta.setDuration(2);
                jugador.addAction(mta);
                break;
            case Input.Keys.E:
                rba.setAmount(90);
                rba.setDuration(1);
                jugador.addAction(rba);
                break;
            case Input.Keys.Q:
                rba.setAmount(-90);
                rba.setDuration(1);
                jugador.addAction(rba);
                break;
            case Input.Keys.Z:
                sba.setAmount(-0.5f);
                sba.setDuration(1);
                jugador.addAction(sba);
                break;
            case Input.Keys.C:
                sba.setAmount(0.5f);
                sba.setDuration(1);
                jugador.addAction(sba);
                break;
        }
        return super.keyDown(event, keycode);

    }

}
