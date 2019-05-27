package domain;

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

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public void setPrecipitacionDia(double precipitacionDia) {
        this.precipitacionDia = precipitacionDia;
    }

    public void setPrecipitacionNoche(double precipitacionNoche) {
        this.precipitacionNoche = precipitacionNoche;
    }
}
