package objetos;

import com.badlogic.gdx.Gdx;

import java.util.Random;

public class PatitoDeGoma extends Objeto {
    private int fuerza;

    public PatitoDeGoma(float x, float y) {
        super("objetos/patitoGoma.png", x, y,
                Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        Random r=new Random();
        if(r.nextBoolean()){
            fuerza=r.nextInt(101);
        }else{
            fuerza=-1*r.nextInt(101);
        }
    }

    public int getFuerza(){
        return fuerza;
    }

}
