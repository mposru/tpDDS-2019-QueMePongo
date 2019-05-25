package domain;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import exceptions.*;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class Guardarropa {

   // private Map<Categoria, Set<Prenda>> prendasSuperiores = new HashSet<>();
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

    public void definirUsuario(Usuario usuario)  {
        if(!isNull(this.usuario)) {
            throw new GuardarropaOcupadoException("Ya tengo dueño/a, no me podes asignar a otra/o");
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


    private void validarPrendas()  {
        String mensajeDeError = "";
        if(prendasInferiores.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan prendas inferiores. ");
        }
        if(prendasSuperiores.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan prendas superiores. ");
        }
        if(calzados.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan zapatos. ");
        }
        if(accesorios.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan accesorios. ");
        }
        if (mensajeDeError != "") {
            throw new FaltaPrendaException(mensajeDeError);
        }
    }

    public List<Atuendo> generarSugerencia() {
        this.validarPrendas();
        return Sets.cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios)
                .stream()
                .map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3)))
                .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardarropa guardarropa = (Guardarropa) o;
        return Objects.equals(prendasInferiores, guardarropa.obtenerPrendasInferiores()) &&
                Objects.equals(prendasSuperiores, guardarropa.obtenerPrendasSuperiores()) &&
                Objects.equals(calzados, guardarropa.obtenerCalzados()) &&
                Objects.equals(accesorios, guardarropa.obtenerAccesorios()) &&
                Objects.equals(usuario, guardarropa.obtenerUsuario());
    }
}
