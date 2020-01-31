package pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import actores.Juanka;
import es.cenec.mundocontinuo.Juego;

public class Pantalla1 extends BaseScreen {

    public Pantalla1(Juego g) {
        super(g);
        this.fondo=new Texture("fondospantalla/pantalla1.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Juanka juanka1=(Juanka)pantalla.getActors().get(0);
        if(juanka1.getY()<0){
            juanka1.moverAPixel(juanka1.getX(),0);
        }else if(juanka1.getY()>= Gdx.graphics.getHeight()){
            juanka1.moverAPixel(juanka1.getX(),Gdx.graphics.getHeight());
        }else if(juanka1.getX()>=Gdx.graphics.getWidth()){
            game.setPantallaActual(new Pantalla2(this.game));
        }else if(juanka1.getX()<0){
            game.setPantallaActual(new Pantalla3(this.game));
        }
    }
}
