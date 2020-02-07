package es.cenecmalaga.fisicas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Personajes.Pollo;

public class Juego extends Game {
	Stage stage;
	
	@Override
	public void create () {
		stage=new Stage(new ScreenViewport());
		stage.addActor(new Pollo());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();

	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
