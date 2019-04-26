package domain;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import exceptions.*;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class Guardarropa {

    private Set<Prenda> prendasSuperiores;
    private Set<Prenda> prendasInferiores;
    private Set<Prenda> calzados;
    private Set<Prenda> accesorios;
    private Usuario usuario;

    public Guardarropa(Usuario usuario) {
        requireNonNull(usuario, "El guardarropa debe tener un usuario");
        this.usuario = usuario;
    }

    public void guardarPrenda(Prenda prenda) {
        switch (prenda.obtenerCategoria()) {
            case CALZADO:
                calzados.add(prenda);
            case PARTE_SUPERIOR:
                prendasSuperiores.add(prenda);
            case PARTE_INFERIOR:
                prendasInferiores.add(prenda);
            case ACCESORIO:
                accesorios.add(prenda);
        }
    }

    public List<Atuendo> generarSugerencia() {
        return Sets.cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios)
                .stream()
                .map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3)))
                .collect(toList());
    }



}
