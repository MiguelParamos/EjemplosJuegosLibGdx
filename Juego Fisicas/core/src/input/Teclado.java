package Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import Actors.Calipo;

/**
 * Created by Miguel on 17/02/2019.
 */

public class Teclado implements InputProcessor {
    Calipo actor;

    public Teclado (Calipo j){
        this.actor=j;
    }


    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("eventoDown","Input "+keycode);
        switch (keycode) {
            case Input.Keys.LEFT:
                actor.getCuerpo().applyForceToCenter(-1000,1000,true);
                break;
            case Input.Keys.R:
                actor.getCuerpo().setLinearVelocity(new Vector2(0,0));
                actor.getCuerpo().setAngularVelocity(0);
                actor.getCuerpo().setTransform(0,30,0);
                break;
            case Input.Keys.RIGHT:
                actor.getCuerpo().applyForceToCenter(1000,1000,true);
                break;
            case Input.Keys.UP:
                actor.getCuerpo().applyForceToCenter(0,3000,true);
                break;
    }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
