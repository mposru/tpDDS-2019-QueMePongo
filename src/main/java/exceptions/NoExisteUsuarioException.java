package exceptions;

public class NoExisteUsuarioException extends RuntimeException {
    public NoExisteUsuarioException (String mensaje) { super(mensaje); }
}
