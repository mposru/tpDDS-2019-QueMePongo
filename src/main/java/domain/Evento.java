package domain;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class Evento {
    private LocalDateTime fecha=null;
    private String nombre;
    private String ubicacion = "CABA";

    public Evento(String nombre) {
        this.nombre = requireNonNull(nombre, "Usted no ingreso un nombre para evento");
    }

    public void indicarFecha(LocalDateTime fecha) {
        requireNonNull(fecha, "Usted no ingreso una fecha para evento");
        this.fecha = fecha;
    }

    public boolean verSiEsProximo(LocalDateTime fecha) {
        requireNonNull(fecha, "Usted no ingreso una fecha para comparar");
        return this.fecha.equals(fecha);
    }
}
