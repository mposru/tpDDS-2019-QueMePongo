package domain.usuario.tipoDeUsuario;

public class Premium implements TipoUsuario {
    private static Premium instanceOfPremium;
    private Premium(){}
    public static Premium getInstance() {
        if(instanceOfPremium==null) {
            instanceOfPremium = new Premium();
        }
        return instanceOfPremium;

    }
    public int limiteDePrendas() {
        return 0;
    }
    public boolean tieneLimiteDePrendas() {return false;}
}
