package domain.usuario.tipoDeUsuario;

public class Gratuito implements TipoUsuario {
    private int limiteDePrendas=5;
    private static Gratuito instanceOfGratuito;

    private Gratuito ()  { }

    public static Gratuito getInstance() {
        if(instanceOfGratuito==null) {
            instanceOfGratuito = new Gratuito();
        }
        return instanceOfGratuito;

    }

    public int limiteDePrendas() {
        return this.limiteDePrendas;
    }
    public boolean tieneLimiteDePrendas() {return true;}
}
