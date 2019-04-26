package domain;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import exceptions.*;

import static java.util.Objects.isNull;
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

    public Usuario obtenerUsuario() {
        return usuario;
    }

    public void definirUsuario(Usuario usuario) throws Exception {
        if(!isNull(this.usuario)) {
            throw new Exception("Ya tengo dueño/a, no me podes asignar a otra/o");
        }
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


    private void validarPrendas() throws Exception {
        if(prendasInferiores.size() <= 0) {
            throw new FaltanPrendasInferioresException("Faltan prendas inferiores");
        }
        if(prendasSuperiores.size() <= 0) {
            throw new FaltanPrendasSuperioresException("Faltan prendas superiores");
        }
        if(calzados.size() <= 0) {
            throw new FaltanCalzadosException("Faltan zapatos");
        }
        if(accesorios.size() <= 0) {
            throw new FaltanAccesoriosException("Faltan accesorios");
        }
    }

    public List<Atuendo> generarSugerencia() throws Exception {
        this.validarPrendas();
        return Sets.cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios)
                .stream()
                .map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3)))
                .collect(toList());
    }

}
