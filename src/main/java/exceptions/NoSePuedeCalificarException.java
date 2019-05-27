package exceptions;

public class NoSePuedeCalificarException extends RuntimeException {
    public NoSePuedeCalificarException(String mensaje) {
        super(mensaje);
    }
}
