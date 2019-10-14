package domain.usuario;

import javax.persistence.Embeddable;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Double> sensibilidades = new HashMap<>();

    public Sensibilidad() {
        sensibilidades.put("general", new Double(0.0));
        sensibilidades.put("cuello", new Double(0.0));
        sensibilidades.put("manos", new Double(0.0));
    }

    public void calificarSensibilidad(CalificacionSensibilidad calificacionSensibilidad, String tipoDeSensibilidad) {
        Double sensibilidadAnterior = this.sensibilidades.get(tipoDeSensibilidad);
        double sumador = 0.0;
        if (calificacionSensibilidad == CalificacionSensibilidad.CALOR) {
            sumador = -0.1;
        }
        if (calificacionSensibilidad == CalificacionSensibilidad.FRIO) {
            sumador = 0.1;
        }
        this.sensibilidades.replace(tipoDeSensibilidad, new Double(sensibilidadAnterior.doubleValue() + sumador));
    }

    public double getFactorSensibilidad(String tipoDeSensibilidad) {
        return this.sensibilidades.get(tipoDeSensibilidad).doubleValue();
    }

}
