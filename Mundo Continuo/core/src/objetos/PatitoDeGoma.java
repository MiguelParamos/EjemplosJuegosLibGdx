package objetos;

import java.util.Random;

public class PatitoDeGoma extends Objeto {
    private int fuerza;

    public PatitoDeGoma(float x, float y, float w, float h) {
        super("objetos/patitoGoma.png", x, y, w, h);
        Random r=new Random();
        if(r.nextBoolean()){
            fuerza=r.nextInt(101);
        }else{
            fuerza=-1*r.nextInt(101);
        }
    }

}
