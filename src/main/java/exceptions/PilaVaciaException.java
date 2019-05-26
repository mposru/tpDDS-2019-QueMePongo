package exceptions;

public class PilaVaciaException extends RuntimeException{
    public PilaVaciaException(String mensaje) {
        super(mensaje);
    }
}
