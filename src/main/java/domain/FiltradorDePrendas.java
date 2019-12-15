package domain;

import com.google.common.collect.Sets;
import domain.prenda.*;
import domain.usuario.Evento;
import domain.usuario.Sensibilidad;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public List<Atuendo> filtrarYGenerarCombinacion(Set<Prenda> prendasSuperiores, Set<Prenda> prendasInferiores,
                                                   Set<Prenda> calzados, Set<Prenda> accesorios,
                                                   Set<Prenda> accesoriosCuello, Set<Prenda> accesoriosManos,
                                                   Evento evento, Sensibilidad sensibilidad) {

        // si es un set los elementos no pueden ser iguales entonces no valido que el esten las ropas repetidas
        Set<Set<Prenda>> prendasSuperioresArmadas = Sets.cartesianProduct(prendasSuperiores, prendasSuperiores, prendasSuperiores)
                .stream()
                .map(conjuntoSuperior -> new HashSet<Prenda>(conjuntoSuperior))
                .filter(conjuntoSuperior -> this.esConjuntoValido(conjuntoSuperior))
                .collect(Collectors.toSet());

        List<Atuendo> sugerencias =  Sets.cartesianProduct(prendasSuperioresArmadas, prendasInferiores,
                calzados, accesorios, accesoriosCuello, accesoriosManos)
                .stream()
                .map(atuendo -> new Atuendo((Set<Prenda>) atuendo.get(0), (Prenda) atuendo.get(1), (Prenda) atuendo.get(2),
                        (Prenda) atuendo.get(3), (Prenda) atuendo.get(4), (Prenda) atuendo.get(5)))
                .collect(Collectors.toList());

        double nivelDeAbrigoEvento = NivelAbrigo.getInstance().getNivelAbrigo(evento.obtenerClima().getTemperaturaMinima(), evento.obtenerClima().getTemperaturaMaxima());
        // le sumo al nivel de abrigo un porcentaje que depende de la sensibilidad
        double nivelDeAbrigoGeneral = nivelDeAbrigoEvento + sensibilidad.getFactorSensibilidad("general") * nivelDeAbrigoEvento;
        double nivelDeAbrigoCuello = nivelDeAbrigoEvento + sensibilidad.getFactorSensibilidad("cuello") * nivelDeAbrigoEvento;
        double nivelDeAbrigoManos = nivelDeAbrigoEvento + sensibilidad.getFactorSensibilidad("manos") * nivelDeAbrigoEvento;

        return sugerencias;
        /*return sugerencias.stream().filter(sugerencia ->
                sugerencia.obtenerPrendaInferior().obtenerUnidadDeAbrigo() >= nivelDeAbrigoGeneral
                && sugerencia.obtenerPrendasSuperiores()
                    .stream()
                    .map(prenda -> prenda.obtenerUnidadDeAbrigo())
                    .collect(Collectors.summingDouble(Double::doubleValue)) >= nivelDeAbrigoGeneral
                && sugerencia.obtenerCalzado().obtenerUnidadDeAbrigo() >= nivelDeAbrigoGeneral
                && sugerencia.obtenerAccesorioCuello().obtenerUnidadDeAbrigo() >= nivelDeAbrigoCuello
                && sugerencia.obtenerAccesorioManos().obtenerUnidadDeAbrigo() >= nivelDeAbrigoManos
        ).collect(Collectors.toList());*/
    }

    private boolean esConjuntoValido(Set<Prenda> conjunto) {
        // evito sugerir dos buzos o dos remeras, etc
        switch(conjunto.size()) {
            case 1:
                return conjunto.stream().allMatch(prenda -> prenda.obtenerCategoria() == Categoria.PARTE_SUPERIOR_ABAJO);
            case 2:
                return conjunto.stream().anyMatch(prenda -> prenda.obtenerCategoria() == Categoria.PARTE_SUPERIOR_ABAJO)
                        && conjunto.stream().anyMatch(prenda -> prenda.obtenerCategoria() == Categoria.PARTE_SUPERIOR_MEDIO);
            case 3:
                return conjunto.stream().anyMatch(prenda -> prenda.obtenerCategoria() == Categoria.PARTE_SUPERIOR_ABAJO)
                        && conjunto.stream().anyMatch(prenda -> prenda.obtenerCategoria() == Categoria.PARTE_SUPERIOR_MEDIO)
                        && conjunto.stream().anyMatch(prenda -> prenda.obtenerCategoria() == Categoria.PARTE_SUPERIOR_ARRIBA);
            default:
                return false;
        }
    }
}
