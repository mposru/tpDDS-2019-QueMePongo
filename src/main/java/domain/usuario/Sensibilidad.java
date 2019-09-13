package domain.usuario;

import domain.TipoDeSensibilidad;

import javax.persistence.Embeddable;

@Embeddable
public class Sensibilidad {
    /*El criterio que tomamos es
        caluroso < 0
        normal = 0
        friolento > 0
    Esto es un factor porcentual en donde al momento de generar un atuendo debo tener en cuenta la sensibilidad del usuario para
    buscar prendas con mayor o menor nivel de abrigo según corresponda.
    por ejemplo, la temperatura del evento me dice que el nivel de abrigo requerido según nuestra recta nivelAbirgo/temperatura
    es de 20 unidades de abrigo, pero el usuario tiene como factor de sensibilidad en manos 0.1 entonces el nivel de abrigo debe ser un
    10% mayor a lo que la función indica.
    Si el usuario vuelve a calificar indicando que tuvo frio, ese factor va a aumentar a 0.2 por ejemplo y el proximo evento el
    nivel de abrigo deberá ser 20% mayor al que devuelve el singleton nivel de abrigo.

     */
    //todos los factores arrancan en cero, después se van a ir modificando a medida que el usuario califique
    private double sensibilidadGeneral = 0; //cubre parte superior en inferior
    private double sensibilidadEnCuello = 0;
    private double sensibilidadEnManos = 0;

    public void calificarSensibilidadGeneral(CalificacionSensibilidad calificacionSensibilidad) {
        if (calificacionSensibilidad == CalificacionSensibilidad.CALOR) {
            this.sensibilidadGeneral -= 0.1;
        }
        if (calificacionSensibilidad == CalificacionSensibilidad.FRIO) {
            this.sensibilidadGeneral += 0.1;
        }
    }

    public double getFactorSensibilidad(TipoDeSensibilidad tipoDeSensibilidad) {
        switch (tipoDeSensibilidad) {
            case MANOS:
                return this.getFactorSensibilidadEnManos();
            case CUELLO:
                return this.getFactorSensibilidadEnCuello();
            case GENERAL:
                return this.getFactorSensibilidadGeneral();
        }
        return 0.0;
    }

    public void calificarSensibilidadEnCuello(CalificacionSensibilidad calificacionSensibilidad) {
        if (calificacionSensibilidad == CalificacionSensibilidad.CALOR) {
            this.sensibilidadEnCuello -= 0.1;
        }
        if (calificacionSensibilidad == CalificacionSensibilidad.FRIO) {
            this.sensibilidadEnCuello += 0.1;
        }
    }

    public void calificarSensibilidadEnManos(CalificacionSensibilidad calificacionSensibilidad) {
        if (calificacionSensibilidad == CalificacionSensibilidad.CALOR) {
            this.sensibilidadEnManos -= 0.1;
        }
        if (calificacionSensibilidad == CalificacionSensibilidad.FRIO) {
            this.sensibilidadEnManos += 0.1;
        }
    }

    public double getFactorSensibilidadGeneral() {
        return this.sensibilidadGeneral;
    }

    public double getFactorSensibilidadEnManos() {
        return this.sensibilidadEnManos;
    }

    public double getFactorSensibilidadEnCuello() {
        return this.sensibilidadEnCuello;
    }

}
