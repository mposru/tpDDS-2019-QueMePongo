package exceptions;

public class FaltaPrendaException extends RuntimeException{
    public FaltaPrendaException(String mensaje) {
        super(mensaje);
    }
}
