package exceptions;

public class PrendaInvalidaException extends RuntimeException {
    public PrendaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
