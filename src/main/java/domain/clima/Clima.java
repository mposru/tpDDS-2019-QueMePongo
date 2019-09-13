package domain.clima;

import org.springframework.format.datetime.joda.LocalDateParser;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Clima {
    private long fecha;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private double precipitacionDia;
    private double precipitacionNoche;

    public Clima(long fecha, double maxima, double minima, double precipitacionDia, double precipitacionNoche){
        this.fecha = fecha;
        this.temperaturaMaxima = maxima;
        this.temperaturaMinima = minima;
        this.precipitacionDia = precipitacionDia;
        this.precipitacionNoche = precipitacionNoche;
    }

    public long getFecha() {
        return fecha;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public double getPrecipitacionDia() {
        return precipitacionDia;
    }

    public double getPrecipitacionNoche() {
        return precipitacionNoche;
    }

    public boolean esDelDia(LocalDate dia) {
        return Instant.ofEpochMilli(fecha*1000).atZone(ZoneId.systemDefault()).toLocalDate().isEqual(dia);
    }
}
