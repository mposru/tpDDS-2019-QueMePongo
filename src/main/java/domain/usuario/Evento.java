package domain.usuario;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class Evento {
    private LocalDateTime fecha;
    private String nombre;
    private String ubicacion;

    public Evento(String nombre, String ubicacion, LocalDateTime fecha) {
        this.fecha = requireNonNull(fecha, "Usted no ingreso una fecha para evento");
        this.nombre = requireNonNull(nombre, "Usted no ingreso un nombre para evento");
        this.ubicacion = requireNonNull(ubicacion, "Debe ingresar una ubicación para el evento");
    }

    public boolean esHoy() {
        return this.fecha.toLocalDate().equals(LocalDateTime.now().toLocalDate());
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
