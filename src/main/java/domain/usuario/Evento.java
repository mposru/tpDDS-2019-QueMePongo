package domain.usuario;

import domain.Atuendo;
import domain.LocalDateTimeConverter;
import domain.clima.AccuWeather;
import domain.clima.Clima;
import domain.clima.DarkSky;
import domain.clima.Meteorologo;
import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.annotations.Transactional;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Transactional
@Observable
@Entity
@Table(name = "eventos")
public class Evento {

    @GeneratedValue
    @Id
    @Column(name= "evento_id", columnDefinition = "int(11) NOT NULL")
    long id;

   // @Transient
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime fecha;

    private String nombre;

    private String ubicacion;

    @Column(name="antelacion_horas")
    private Integer antelacionEnHoras = 1;

    @Column(name="periodo", nullable = false, length = 8 )
    @Enumerated(value = EnumType.STRING)
    private Periodo tipoDeActualizacion;

    @Column(name = "tiene_sugerencia")
    private Boolean tieneSugerencia = false;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "evento_id")
    List<Atuendo> sugerencias;

    @Transient
    //TODO sacar de acá al meteorologo, no va en evento
    private Meteorologo meteorologo = new AccuWeather();

    public Evento(){
    }
    public Evento(String nombre, String ubicacion, LocalDateTime fecha,Periodo tipoDeActualizacion,Integer antelacionEnHoras) {
        this.fecha = requireNonNull(fecha, "Debe ingresar una fecha para el evento");
        this.nombre = requireNonNull(nombre, "Debe ingresar un nombre para el evento");
        this.ubicacion = requireNonNull(ubicacion, "Debe ingresar una ubicación para el evento");
        this.antelacionEnHoras = requireNonNull(antelacionEnHoras,"Debe ingresar la antelacion del evento");
        this.tipoDeActualizacion=requireNonNull(tipoDeActualizacion,"Debe ingresar el tipo de periodicidad");
    }

    public boolean tieneAtuendoAceptado() {
        return this.sugerencias.stream().anyMatch(atuendo -> atuendo.obtenerEstadoAtuendo().estaAceptado());
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
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getAntelacionEnHoras() {
        return antelacionEnHoras;
    }

    public void setAntelacionEnHoras(Integer antelacionEnHoras) {
        this.antelacionEnHoras = antelacionEnHoras;
    }

    public Periodo getTipoDeActualizacion() {
        return tipoDeActualizacion;
    }

    public void setTipoDeActualizacion(Periodo tipoDeActualizacion) {
        this.tipoDeActualizacion = tipoDeActualizacion;
    }

    public void guardarSugerencias(List<Atuendo> sugerencias) {
        this.sugerencias = sugerencias;
    }

    public List<Atuendo> obtenerSugerencias() {
        return this.sugerencias;
    }

}
