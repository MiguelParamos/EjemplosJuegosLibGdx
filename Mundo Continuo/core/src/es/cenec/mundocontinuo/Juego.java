package es.cenec.mundocontinuo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import actores.Juanka;

public class Juego extends Game {
	Stage pantalla1;

	@Override
	public void create () {
		pantalla1=new Stage(new ScreenViewport());
		pantalla1.addActor(new Juanka());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		pantalla1.draw();
	}
	
	@Override
	public void dispose () {
		pantalla1.dispose();
	}
}
