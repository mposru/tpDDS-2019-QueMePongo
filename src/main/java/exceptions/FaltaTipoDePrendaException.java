package exceptions;

public class FaltaTipoDePrendaException extends RuntimeException {
    public FaltaTipoDePrendaException(String mensaje) {
        super(mensaje);
    }
}
