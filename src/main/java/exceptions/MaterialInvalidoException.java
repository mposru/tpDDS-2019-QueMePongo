package exceptions;

public class MaterialInvalidoException extends RuntimeException {
    public MaterialInvalidoException(String mensaje) {
        super(mensaje);
    }
}
