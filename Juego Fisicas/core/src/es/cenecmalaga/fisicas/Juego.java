package es.cenecmalaga.fisicas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.org.apache.xpath.internal.operations.Or;

import Personajes.Pollo;

public class Juego extends Game {
	private World world;
	private Pollo pollo;
	private Box2DDebugRenderer debugRenderer;
	private SpriteBatch batch;
	private OrthographicCamera camara;
	
	@Override
	public void create () {
		batch=new SpriteBatch();
		world=new World(new Vector2(0,-9.8f),true);
		pollo=new Pollo(world);
		camara=new OrthographicCamera(10,10);
		this.debugRenderer=new Box2DDebugRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		pollo.draw(batch,0);
		batch.end();

		camara.update();
		debugRenderer.render(world, camara.combined);
	}
	
	@Override
	public void dispose () {
		world.dispose();
	}
}
