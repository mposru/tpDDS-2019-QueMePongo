package domain.usuario;

import domain.clima.AccuWeather;
import domain.clima.Clima;
import domain.clima.Meteorologo;
import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.annotations.Transactional;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

@Transactional
@Observable
@org.hibernate.annotations.Entity
public class Evento extends Entity {

    @GeneratedValue
    @Id
    long id;

    private LocalDateTime fecha;
    private String nombre;
    private String ubicacion;
    private Integer antelacionEnHoras = 1;
    @Enumerated
    private Periodo tipoDeActualizacion;
    private Boolean tieneSugerencia = false;
    private Meteorologo meteorologo;

    public Evento(){
    }
    public Evento(String nombre, String ubicacion, LocalDateTime fecha,Periodo tipoDeActualizacion,Integer antelacionEnHoras) {
        this.fecha = requireNonNull(fecha, "Debe ingresar una fecha para el evento");
        this.nombre = requireNonNull(nombre, "Debe ingresar un nombre para el evento");
        this.ubicacion = requireNonNull(ubicacion, "Debe ingresar una ubicación para el evento");
        this.antelacionEnHoras = requireNonNull(antelacionEnHoras,"Debe ingresar la antelacion del evento");
        this.tipoDeActualizacion=requireNonNull(tipoDeActualizacion,"Debe ingresar el tipo de periodicidad");
    }

    public void setearMeteorologo(Meteorologo meteorologo) {
        this.meteorologo = meteorologo;
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
        double horas = obtenerComparacionDeHora();
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

    public Clima obtenerClima() {
        // todo: poner bien el dia
       return meteorologo.obtenerClima(fecha.toLocalDate());
    }
}
