package escuchadores;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import actores.Personaje;

public class EscuchadorJugador extends InputListener {
    Personaje jugador;

    public EscuchadorJugador(Personaje j){
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
                jugador.startMoving(1);
               /* mba.setDuration(1);
                mba.setAmount(jugador.getVelocidad(),0);
                jugador.addAction(mba); */
                break;
            case Input.Keys.S:
                jugador.startMoving(2);
                /*mba.setDuration(1);
                mba.setAmount(0,-jugador.getVelocidad());
                jugador.addAction(mba); */
                break;
            case Input.Keys.A:
                jugador.startMoving(3);
              /*  mba.setDuration(1);
                mba.setAmount(-jugador.getVelocidad(),0);
                jugador.addAction(mba); */
                break;
            case Input.Keys.W:
                jugador.startMoving(0);
               /* mba.setDuration(1);
                mba.setAmount(0,jugador.getVelocidad());
                jugador.addAction(mba); */
                break;
            case Input.Keys.R: //Reset, volver al 0,0
                mta.setPosition(0,0);
                mta.setDuration(2);
                RotateToAction rta=new RotateToAction();
                rta.setRotation(0);
                rta.setDuration(2);
                ScaleToAction sta=new ScaleToAction();
                sta.setScale(1);
                sta.setDuration(2);
                ParallelAction pa=new ParallelAction(mta,rta,sta);
                jugador.addAction(pa);
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
            case Input.Keys.F:

                break;
            case Input.Keys.X:
                SequenceAction parpadear=new SequenceAction(new SequenceAction(
                        Actions.alpha(0.1f,0.2f),
                        Actions.alpha(1f,0.2f),
                        Actions.alpha(0.1f,0.2f),
                        Actions.alpha(1f,0.2f)
                ),new SequenceAction(
                        Actions.alpha(0.1f,0.2f),
                        Actions.alpha(1f,0.2f),
                        Actions.alpha(0.1f,0.2f),
                        Actions.alpha(1f,0.2f)));
                ScaleByAction danioDisminuir=new ScaleByAction();
                danioDisminuir.setAmount(-1f,-0.5f);
                danioDisminuir.setDuration(0.4f);
                ScaleByAction danioDisminuir2=new ScaleByAction();
                danioDisminuir2.setAmount(+0.5f,-0.5f);
                danioDisminuir2.setDuration(0.4f);
                ScaleByAction danioAumentar=new ScaleByAction();
                danioAumentar.setAmount(+0.5f,+0.5f);
                danioAumentar.setDuration(0.4f);
                ScaleByAction danioAumentar2=new ScaleByAction();
                danioAumentar2.setAmount(+0,+0.5f);
                danioAumentar2.setDuration(0.4f);
                SequenceAction escalarseDanio=new SequenceAction(danioDisminuir,
                        danioDisminuir2,danioAumentar,danioAumentar2);
                jugador.addAction(new ParallelAction(parpadear,escalarseDanio));
                break;
        }
        return super.keyDown(event, keycode);

    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        switch (keycode){
            case Input.Keys.W:
                jugador.stopMoving(0);
                break;
            case Input.Keys.A:
                jugador.stopMoving(3);
                break;
            case Input.Keys.S:
                jugador.stopMoving(2);
                break;
            case Input.Keys.D:
                jugador.stopMoving(1);
                break;

        }

        return super.keyUp(event, keycode);

    }



        }
