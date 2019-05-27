package domain;

public class Gratuito implements TipoUsuario {
    private int limiteDePrendas=20;

    public int limiteDePrendas() {
        return 20; //harcodeado para probar AtuendoTest
    }
}
