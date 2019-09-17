package domain;

import domain.clima.Clima;
import domain.prenda.NivelAbrigo;
import domain.usuario.Sensibilidad;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class FiltradorDePrendas {
    private static FiltradorDePrendas instanceOfFiltradorDePrendas;

    private FiltradorDePrendas() {
    }

    public static FiltradorDePrendas getInstance() {
        if (instanceOfFiltradorDePrendas == null) {
            instanceOfFiltradorDePrendas = new FiltradorDePrendas();
        }
        return instanceOfFiltradorDePrendas;
    }

    // cambiar nombre de metodo: filtrarYGenerarCombinacion
    public Set<Set<Prenda>> filtrarPrendas(Set<Prenda> prendas, Clima climaEvento, Sensibilidad sensibilidad, TipoDeSensibilidad tipoDeSensibilidad) {
        Set<Set<Prenda>> prendasFiltradas = new HashSet<>();
        double nivelDeAbrigo = obtenerNivelDeAbrigo(climaEvento, sensibilidad, tipoDeSensibilidad);
        prendas.forEach(prenda -> {
            AtomicReference<Double> unidadesDeAbrigoAcumuladas = new AtomicReference<>(0.0);
            double unidades = prenda.obtenerUnidadDeAbrigo();

            if (unidades <= nivelDeAbrigo) {
                // si el nivel de esta prenda no supera el nivel que tengo que armar, entro al otro foreach
                Set<Prenda> conjunto = new HashSet<>();
                unidadesDeAbrigoAcumuladas.set(unidades);
                conjunto.add(prenda);
                prenda.setDisponibilidad(false);
                prendas.forEach(otraPrenda -> {
                    // no voy a ponerme dos prendas iguales ni superar el nivelDeAbrigo
                    if (prenda != otraPrenda && unidadesDeAbrigoAcumuladas.get() < nivelDeAbrigo) {
                        double unidadesTemporales = unidades + unidadesDeAbrigoAcumuladas.get();
                        // si lo acumulado de niveles no supera lo que tengo que alcanzar lo agrego
                        if (unidadesTemporales <= nivelDeAbrigo) {
                            unidadesDeAbrigoAcumuladas.set(unidadesTemporales);
                            conjunto.add(otraPrenda);
                            prenda.setDisponibilidad(false);
                        }
                    }
                });
                prendasFiltradas.add(conjunto);
            }
        });
        return prendasFiltradas;
    }

    private double obtenerNivelDeAbrigo(Clima climaEvento, Sensibilidad sensibilidad, TipoDeSensibilidad tipoDeSensibilidad) {
        double nivelDeAbrigoEvento = NivelAbrigo.getInstance().getNivelAbrigo(climaEvento.getTemperaturaMinima(), climaEvento.getTemperaturaMaxima());
        // le sumo al nivel de abrigo un porcentaje que depende de la sensibilidad
        return nivelDeAbrigoEvento + sensibilidad.getFactorSensibilidad(tipoDeSensibilidad) * nivelDeAbrigoEvento;
    }
}
