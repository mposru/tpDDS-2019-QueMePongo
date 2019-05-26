package exceptions;

public class NoSePuedeRechazarException extends RuntimeException {
    public NoSePuedeRechazarException(String mensaje) {
        super(mensaje);
    }
}
