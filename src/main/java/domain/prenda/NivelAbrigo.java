package domain.prenda;

//Singleton que me calcula el nivel de abrigo entre dos temperaturas. Tiene la recta unidadDeAbrigo/Temp.
public class NivelAbrigo {

    private static NivelAbrigo instanceOfNivelAbrigo;

    private NivelAbrigo() {
    }

    public static NivelAbrigo getInstance() {
        if (instanceOfNivelAbrigo == null) {
            instanceOfNivelAbrigo = new NivelAbrigo();
        }
        return instanceOfNivelAbrigo;
    }

    public double getNivelAbrigo(double temperaturaMin, double temperaturaMax) {
        double nivelDeAbrigo;
        nivelDeAbrigo = (-0.5*(0.5*(temperaturaMin + temperaturaMax)) + 25);
        return nivelDeAbrigo;
    }

    public double getTemperatura(double nivelAbrigo) {
        double temperatura;
        temperatura = -2 * nivelAbrigo + 50;
        return temperatura;
    }
}
