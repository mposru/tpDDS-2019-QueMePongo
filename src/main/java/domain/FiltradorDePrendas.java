package domain;

import domain.clima.Clima;
import domain.prenda.NivelAbrigo;
import domain.usuario.Sensibilidad;

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

    public Set<Prenda> filtrarPrendas(Set<Prenda> prendas, Clima climaEvento, Sensibilidad sensibilidad, TipoDeSensibilidad tipoDeSensibilidad) {
        AtomicReference<Double> unidadesDeAbrigoAcumuladas = new AtomicReference<>(0.0);
        Set<Prenda> prendasFiltradas = prendas.stream().filter(prenda -> {
            double unidades = prenda.obtenerUnidadDeAbrigo();
            double unidadesTemporales = unidades + unidadesDeAbrigoAcumuladas.get();
            if (unidadesTemporales <= obtenerNivelDeAbrigo(climaEvento, sensibilidad, tipoDeSensibilidad)) {
                unidadesDeAbrigoAcumuladas.set(unidadesTemporales);
            }
            return unidadesTemporales <= obtenerNivelDeAbrigo(climaEvento, sensibilidad, tipoDeSensibilidad);
        }).collect(Collectors.toSet());
        // pensar como hacer para armar los sets
        return prendasFiltradas;
    }

    private double obtenerNivelDeAbrigo(Clima climaEvento, Sensibilidad sensibilidad, TipoDeSensibilidad tipoDeSensibilidad) {
        double nivelDeAbrigoEvento = NivelAbrigo.getInstance().getNivelAbrigo(climaEvento.getTemperaturaMinima(), climaEvento.getTemperaturaMaxima());
        // le sumo al nivel de abrigo un porcentaje que depende de la sensibilidad
        return nivelDeAbrigoEvento + sensibilidad.getFactorSensibilidad(tipoDeSensibilidad) * nivelDeAbrigoEvento;
    }
}
