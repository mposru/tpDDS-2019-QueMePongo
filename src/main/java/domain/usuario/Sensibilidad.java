package domain.usuario;

import domain.Atuendo;

public class Sensibilidad {
    private double temperatura;
    private TipoSensibilidad tipoSensibilidad;
    private Atuendo atuendo;
    public Sensibilidad(double temperatura, TipoSensibilidad tipoSensibilidad, Atuendo atuendo) {
        this.temperatura = temperatura;
        this.tipoSensibilidad = tipoSensibilidad;
        this.atuendo = atuendo;
    }

    public double getTemperatura() {return this.temperatura;}
    public TipoSensibilidad getTipoSensibilidad() {return this.tipoSensibilidad;}

}
