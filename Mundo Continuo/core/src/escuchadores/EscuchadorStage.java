package escuchadores;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class EscuchadorStage extends InputListener {
    private Stage stage;
    private int actorActual;

    public EscuchadorStage(Stage s){
        this.stage=s;
        actorActual=0;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch(keycode){
            case Input.Keys.F:
                this.actorActual++;
                if(this.actorActual>=stage.getActors().size){
                    this.actorActual=0;
                }
                stage.setKeyboardFocus(stage.getActors().get(actorActual));
            break;
        }
        return super.keyDown(event, keycode);
    }
}
