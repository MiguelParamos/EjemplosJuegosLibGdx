package es.cenecmalaga.fisicas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.org.apache.xpath.internal.operations.Or;

import Personajes.Jackie;
import Personajes.Pollo;
import basededatos.BaseDeDatos;
import constantes.Constantes;
import input.Teclado;

public class Juego extends Game {
	private World world;
	private Pollo pollo;
	private Pollo pollo2;
	private Jackie jackie;
	private Box2DDebugRenderer debugRenderer;
	private SpriteBatch batch;
	private OrthographicCamera camara;
	private TiledMap mapa;
	private OrthogonalTiledMapRenderer renderer;
	private static final float pixelsPorCuadro=16f;
	private Teclado teclado;
	private int puntuacion;
	private SpriteBatch batchTexto;
	private Body paredFinal;
	BitmapFont textoPuntuacion;
	BaseDeDatos baseDeDatos;
	private boolean esAndroid;

	public Juego(BaseDeDatos bd,boolean android){
		baseDeDatos=bd;
		this.esAndroid=android;
		Constantes.init(this.esAndroid);
	}

	@Override
	public void create () {
		puntuacion=baseDeDatos.cargar();
		batch=new SpriteBatch();
		world=new World(new Vector2(0,-9.8f),true);
		pollo=new Pollo(world);
		pollo2=new Pollo(world);
		jackie=new Jackie(world);
		camara=new OrthographicCamera(10,10);
		this.debugRenderer=new Box2DDebugRenderer();
		camara.position.x=pollo.getX();
		camara.position.y=pollo.getY();
		textoPuntuacion = new BitmapFont();
		batchTexto=new SpriteBatch();
		//Era un suelo alternativo, creado sin mapa, lo quitamos para probar mapa
		//BodyDef propiedadesSuelo= new BodyDef(); //Establecemos las propiedades del cuerpo
		//propiedadesSuelo.type = BodyDef.BodyType.StaticBody;
		//Body suelo = world.createBody(propiedadesSuelo);
		/*FixtureDef propiedadesFisicasSuelo=new FixtureDef();
		propiedadesFisicasSuelo.shape = new PolygonShape();
		((PolygonShape)propiedadesFisicasSuelo.shape).setAsBox(100/2f, 1/2f);
		propiedadesFisicasSuelo.density = 1f;*/
		//Era un suelo alternativo, creado sin mapa, lo quitamos para probar mapa
		//suelo.createFixture(propiedadesFisicasSuelo);
		//Fin suelo alternativo, que no está cargado del tmx

		//El unitScale, en mundos contínuos, en lugar de servir para entender tiles, sirve para entender metros
		mapa=new TmxMapLoader().load("mapas/mimapa.tmx");
		renderer = new OrthogonalTiledMapRenderer(mapa, 1/pixelsPorCuadro);


		//Creamos el cuerpo físico de todos los rectángulos del tmx
		for (MapObject objeto:mapa.getLayers().get("objetos").getObjects()){
			BodyDef propiedadesRectangulo= new BodyDef(); //Establecemos las propiedades del cuerpo
			propiedadesRectangulo.type = BodyDef.BodyType.StaticBody;
			Body rectanguloSuelo = world.createBody(propiedadesRectangulo);
			FixtureDef propiedadesFisicasRectangulo=new FixtureDef();
			Shape formaRectanguloSuelo=getRectangle((RectangleMapObject)objeto);
			propiedadesFisicasRectangulo.shape = formaRectanguloSuelo;
			propiedadesFisicasRectangulo.density = 1f;
			rectanguloSuelo.createFixture(propiedadesFisicasRectangulo);
			//Cojo la pared final como objeto especial
			if(objeto.getName()!=null&&objeto.getName().equals("paredFinal")){
				paredFinal=rectanguloSuelo;
			}
		}

		teclado=new Teclado(pollo);
		Gdx.input.setInputProcessor(teclado);

		//Se comparan cuerpos para saber si hay colisión, y qué colisiona con qué.
		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				Gdx.app.log("Contacto comenzado!",contact.getFixtureA()+" : "+contact.getFixtureB());
				Gdx.app.log("Es figura A pollo?",(contact.getFixtureA().getBody()==pollo.getCuerpo())+"");
				Gdx.app.log("Es figura B pollo?",(contact.getFixtureB().getBody()==pollo.getCuerpo())+"");
				if(contact.getFixtureA().getBody()==pollo.getCuerpo()&&
				contact.getFixtureB().getBody()==pollo2.getCuerpo()){
					Gdx.app.log("Pollo","Choque de pollos");
				}
				if(contact.getFixtureA().getBody()==pollo.getCuerpo()&&
						contact.getFixtureB().getBody()==jackie.getCuerpo()){
					pollo.getCuerpo().applyForceToCenter(300,500,true);
					puntuacion++;
					baseDeDatos.guardar(puntuacion);
					Gdx.app.log("Puntación",puntuacion+"");
				}
				if(contact.getFixtureA().getBody()==pollo2.getCuerpo()&&
						contact.getFixtureB().getBody()==jackie.getCuerpo()){
					pollo2.getCuerpo().applyForceToCenter(300,500,true);
					puntuacion++;
					baseDeDatos.guardar(puntuacion);
				}

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
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.7f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		pollo.seguir(camara);
		renderer.setView(camara);
		renderer.render();
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		jackie.draw(batch,0);
		pollo.draw(batch,0);
		pollo2.draw(batch,0);
		batch.end();

		batchTexto.begin();
		if(!esAndroid) {
			textoPuntuacion.draw(batchTexto, puntuacion + " puntos", Gdx.graphics.getHeight() / 30, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 30, Gdx.graphics.getWidth(), -1, false);
		}else{
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fuente/arial.ttf"));
			FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
			parameter.size = 60;
			parameter.borderColor=new Color(0.1f,0.1f,0.1f,1);
			parameter.borderWidth=3f;
			parameter.incremental=true;
			textoPuntuacion = generator.generateFont(parameter);
			textoPuntuacion.draw(batchTexto, puntuacion + " puntos", Gdx.graphics.getHeight() / 30, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 30, Gdx.graphics.getWidth(), -1, false);
		}
		batchTexto.end();

		camara.update();
		debugRenderer.render(world, camara.combined);
	}
	
	@Override
	public void dispose () {
		world.dispose();
		renderer.dispose();
		this.debugRenderer.dispose();
		this.batch.dispose();
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
			worldVertices[i] = vertices[i] / pixelsPorCuadro;
		}

		polygon.set(worldVertices);
		return polygon;
	}

	private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
		Rectangle rectangle = rectangleObject.getRectangle();
		PolygonShape polygon = new PolygonShape();
		Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) /pixelsPorCuadro,
				(rectangle.y + rectangle.height * 0.5f ) / pixelsPorCuadro);
		polygon.setAsBox(rectangle.width * 0.5f /pixelsPorCuadro,
				rectangle.height * 0.5f / pixelsPorCuadro,
				size,
				0.0f);
		return polygon;
	}

	private static CircleShape getCircle(CircleMapObject circleObject) {
		Circle circle = circleObject.getCircle();
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(circle.radius / pixelsPorCuadro);
		circleShape.setPosition(new Vector2(circle.x / pixelsPorCuadro, circle.y /pixelsPorCuadro));
		return circleShape;
	}

	private static ChainShape getPolyline(PolylineMapObject polylineObject) {
		float[] vertices = polylineObject.getPolyline().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length / 2];

		for (int i = 0; i < vertices.length / 2; ++i) {
			worldVertices[i] = new Vector2();
			worldVertices[i].x = vertices[i * 2] / pixelsPorCuadro;
			worldVertices[i].y = vertices[i * 2 + 1] / pixelsPorCuadro;
		}

		ChainShape chain = new ChainShape();
		chain.createChain(worldVertices);
		return chain;
	}
}
