package basedatos;

import basededatos.BaseDeDatos;

public class BaseDeDatosEscritorio implements BaseDeDatos {
    @Override
    public int cargar() {
        return 9; //Numero cualquiera, para saber que cargo desde desktop
    }

    @Override
    public void guardar(int nuevaPuntuacion) {
        //No hago nada, en escritorio esto es mentira.
    }
}
