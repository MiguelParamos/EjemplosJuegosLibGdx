package es.cenecmalaga.fisicas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Personajes.Jackie;
import Personajes.Pollo;
import input.Teclado;

public class Juego extends Game {
	World mundo;
	Box2DDebugRenderer debugRenderer;
	OrthographicCamera camera;
	Batch batch;
	Pollo pollo;
	Pollo pollo2;
	Jackie jackie;
	private TiledMap mapa;
	private OrthogonalTiledMapRenderer renderer;
	private int anchuraMapa;
	private int alturaMapa;
	private static final float unitScale=1/16f;
	private Teclado teclado;

	@Override
	public void create () {
		batch=new SpriteBatch();
		Box2D.init();
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(10,10);
		mundo=new World(new Vector2(0, -9.8f), true);
		debugRenderer = new Box2DDebugRenderer();
		pollo=new Pollo(mundo,6,12);
		pollo2=new Pollo(mundo,15,20);
		jackie=new Jackie(mundo,25,20);


		mapa=new TmxMapLoader().load("mapa/granja.tmx");
		renderer = new OrthogonalTiledMapRenderer(mapa, unitScale);
		anchuraMapa = ((TiledMapTileLayer) mapa.getLayers().get(0)).getWidth();
		alturaMapa = ((TiledMapTileLayer) mapa.getLayers().get(0)).getHeight();


		BodyDef propiedadesSuelo= new BodyDef(); //Establecemos las propiedades del cuerpo
		propiedadesSuelo.type = BodyDef.BodyType.StaticBody;
		Body suelo = mundo.createBody(propiedadesSuelo);
		FixtureDef propiedadesFisicasSuelo=new FixtureDef();
		PolygonMapObject objetoSuelo=(PolygonMapObject)mapa.getLayers().get("solidosSuelo").getObjects().get(0);
		Shape formaSuelo=getPolygon(objetoSuelo);
		propiedadesFisicasSuelo.shape = formaSuelo;
		//Explicar cómo sacar partido de isSensor.
		propiedadesFisicasSuelo.density = 1f;
		propiedadesFisicasSuelo.filter.categoryBits = 0x0002;
		propiedadesFisicasSuelo.filter.maskBits = 0x0001;
		suelo.createFixture(propiedadesFisicasSuelo);

		teclado=new Teclado(pollo);
		Gdx.input.setInputProcessor(teclado);

		//Se comparan cuerpos para saber si hay colisión, y qué colisiona con qué.
		mundo.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				Gdx.app.log("Contacto comenzado!",contact.getFixtureA()+" : "+contact.getFixtureB());
				Gdx.app.log("Es figura A pollo?",(contact.getFixtureA().getBody()==pollo.getCuerpo())+"");
				Gdx.app.log("Es figura B pollo?",(contact.getFixtureB().getBody()==pollo.getCuerpo())+"");
			}

			@Override
			public void endContact(Contact contact) {

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

			}
		});
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		pollo.seguir(camera);
		renderer.setView(camera);
		mundo.step(Gdx.graphics.getDeltaTime(), 6, 2);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		renderer.render();
		batch.begin();
		pollo.draw(batch,0);
		pollo2.draw(batch,0);
		jackie.draw(batch,0);
		batch.end();
		debugRenderer.render(mundo, camera.combined);

	}
	
	@Override
	public void dispose () {
		mundo.dispose();
		renderer.dispose();
		debugRenderer.dispose();
	}

	/**
	 * LOS POLÍGONOS DE MÁS DE 9 VÉRTICES DAN ERROR. Usa más polígonos más simples.
	 * @param polygonObject
	 * @return
	 */
	private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
		PolygonShape polygon = new PolygonShape();
		float[] vertices = polygonObject.getPolygon().getTransformedVertices();

		float[] worldVertices = new float[vertices.length];

		for (int i = 0; i < vertices.length; ++i) {
			worldVertices[i] = vertices[i] / 16;
		}

		polygon.set(worldVertices);
		return polygon;
	}

	//16 son los pixels por tile
	private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
		Rectangle rectangle = rectangleObject.getRectangle();
		PolygonShape polygon = new PolygonShape();
		Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) /16,
				(rectangle.y + rectangle.height * 0.5f ) / 16);
		polygon.setAsBox(rectangle.width * 0.5f /16,
				rectangle.height * 0.5f / 16,
				size,
				0.0f);
		return polygon;
	}

	private static CircleShape getCircle(CircleMapObject circleObject) {
		Circle circle = circleObject.getCircle();
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(circle.radius / 16);
		circleShape.setPosition(new Vector2(circle.x / 16, circle.y /16));
		return circleShape;
	}



	private static ChainShape getPolyline(PolylineMapObject polylineObject) {
		float[] vertices = polylineObject.getPolyline().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length / 2];

		for (int i = 0; i < vertices.length / 2; ++i) {
			worldVertices[i] = new Vector2();
			worldVertices[i].x = vertices[i * 2] / 16;
			worldVertices[i].y = vertices[i * 2 + 1] / 16;
		}

		ChainShape chain = new ChainShape();
		chain.createChain(worldVertices);
		return chain;
	}
}
