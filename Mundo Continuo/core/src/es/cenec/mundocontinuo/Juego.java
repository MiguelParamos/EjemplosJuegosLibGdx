package es.cenec.mundocontinuo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import actores.Juanka;
import escuchadores.EscuchadorJugador;
import escuchadores.EscuchadorStage;
import pantallas.BaseScreen;
import pantallas.Pantalla1;

public class Juego extends Game {
	private BaseScreen pantallaActual;
	@Override
	public void create () {

		this.setPantallaActual(new Pantalla1(this));
	}

	public void setPantallaActual(BaseScreen pa){
		pantallaActual=pa;
		setScreen(pantallaActual);
	}

	@Override
	public void render () {
		//Mal hecho, cuando veamos por qué no se llama, hay que cambiarlo.
		pantallaActual.render(Gdx.graphics.getDeltaTime());

	}
	
	@Override
	public void dispose () {
		pantallaActual.dispose();
	}
}
