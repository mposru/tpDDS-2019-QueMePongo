package domain;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class Guardarropa {

    private Set<Prenda> prendasSuperiores = new HashSet<>();
    private Set<Prenda> prendasInferiores = new HashSet<>();
    private Set<Prenda> calzados = new HashSet<>();
    private Set<Prenda> accesorios = new HashSet<>();
    private Usuario usuario;

    public Set<Prenda> obtenerPrendasSuperiores() {
        return prendasSuperiores;
    }

    public Set<Prenda> obtenerPrendasInferiores() {
        return prendasInferiores;
    }

    public Set<Prenda> obtenerCalzados() {
        return calzados;
    }

    public Set<Prenda> obtenerAccesorios() {
        return accesorios;
    }

    public void definirUsuario(Usuario usuario) {
        this.usuario = requireNonNull(usuario, "El usuario no puede ser vacio");
    }

    public void guardarPrenda(Prenda prenda) {
        switch (prenda.obtenerCategoria()) {
            case CALZADO:
                calzados.add(prenda);
                break;
            case PARTE_SUPERIOR:
                prendasSuperiores.add(prenda);
                break;
            case PARTE_INFERIOR:
                prendasInferiores.add(prenda);
                break;
            case ACCESORIO:
                accesorios.add(prenda);
                break;
        }
    }

    public List<Atuendo> generarSugerencia() {
        return Sets.cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios)
                .stream()
                .map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3)))
                .collect(toList());
    }
}
