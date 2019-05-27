package exceptions;

public class NoSePuedeAceptarException extends RuntimeException {
    public NoSePuedeAceptarException(String mensaje) {
        super(mensaje);
    }
}
