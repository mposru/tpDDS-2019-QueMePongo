package domain.usuario;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.annotations.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

@Transactional
@Observable
public class Evento extends Entity {
    private LocalDateTime fecha;
    private String nombre;
    private String ubicacion;
    private Integer antelacionEnHoras=1;
    private Periodo  tipoDeActualizacion;
    private Boolean tieneSugerencia = false;


    public Evento(){
    }
    public Evento(String nombre, String ubicacion, LocalDateTime fecha,Periodo tipoDeActualizacion,Integer antelacionEnHoras) {
        this.fecha = requireNonNull(fecha, "Usted no ingreso una fecha para evento");
        this.nombre = requireNonNull(nombre, "Usted no ingreso un nombre para evento");
        this.ubicacion = requireNonNull(ubicacion, "Debe ingresar una ubicación para el evento");
        this.antelacionEnHoras = requireNonNull(antelacionEnHoras,"Debe ingresar la antelacion del evento");
        this.tipoDeActualizacion=requireNonNull(tipoDeActualizacion,"Debe ingresar el tipo de periodicidad");
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

    public boolean esProximo(){
        double horas=obtenerComparacionDeHora();
        if(horas<=this.antelacionEnHoras&&horas>0){
            this.actualizacion();
            return true;
        }
        if(horas<0){
            this.actualizacion();
            return false;
        }
        return false;
    }
    public void actualizacion(){
        if(tipoDeActualizacion!=Periodo.NINGUNO){
            switch (tipoDeActualizacion){
                case DIARIO:
                    this.fecha=this.fecha.plusDays(1);
                    break;
                case SEMANAL:
                    this.fecha=this.fecha.plusWeeks(1);
                    break;
                case MENSUAL:
                    this.fecha=this.fecha.plusMonths(1);
                    break;
                case  ANUAL:
                    this.fecha=this.fecha.plusYears(1);
                    break;
            }
            if(obtenerComparacionDeHora()<0)actualizacion();
        }
    }

    private double obtenerComparacionDeHora(){
        return (double) Duration.between(LocalDateTime.now(),fecha).getSeconds()/3600;
    }

    public Boolean getTieneSugerencia() {
        return tieneSugerencia;
    }

    public void setTieneSugerencia(Boolean tieneSugerencia) {
        this.tieneSugerencia = tieneSugerencia;
    }
}
