package constantes;

public class Constantes {
    public static int fuerzaLanzamientoX;
    public static int fuerzaLanzamientoY;

    public static void init(boolean esAndroid){
        if(esAndroid){
            fuerzaLanzamientoX=20;
            fuerzaLanzamientoY=20;
        }else{
            fuerzaLanzamientoX=100;
            fuerzaLanzamientoY=100;
        }
    }
}
