package exceptions;

public class GuardarropaOcupadoException extends RuntimeException {
    public GuardarropaOcupadoException(String mensaje) {
        super(mensaje);
    }
}