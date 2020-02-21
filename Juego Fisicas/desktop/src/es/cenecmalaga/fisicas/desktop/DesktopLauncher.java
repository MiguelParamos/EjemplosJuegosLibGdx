package es.cenecmalaga.fisicas.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import basedatos.BaseDeDatosEscritorio;
import es.cenecmalaga.fisicas.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Juego(new BaseDeDatosEscritorio(),false), config);
	}
}
