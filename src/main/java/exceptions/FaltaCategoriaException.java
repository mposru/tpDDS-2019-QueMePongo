package exceptions;

public class FaltaCategoriaException extends RuntimeException {
    public FaltaCategoriaException (String mensaje) {
        super(mensaje);
    }
}