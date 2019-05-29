package domain;

import com.google.common.collect.Sets;
import domain.clima.Meteorologo;
import exceptions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class Guardarropa {

    private Set<Prenda> prendasSuperiores = new HashSet<>();
    private Set<Prenda> prendasInferiores = new HashSet<>();
    private Set<Prenda> calzados = new HashSet<>();
    private Set<Prenda> accesorios = new HashSet<>();
    private Usuario usuario;
    private Meteorologo meteorologo;


    //harcodeo para test, después lo cambiamos
    private int limiteDePrendas = 20; //usuario.limiteDePrendas(); // el guardarropas queda seteado con el limite que tenga el usuario dueño del mismo
    private int cantidadDePrendas;

    public Set<Prenda> obtenerPrendasSuperiores() {
        return prendasSuperiores;
    }

    //Agregamos el usuario en el constructor porque necesitamos saber
    // el tipo de usuario que tiene asociado para saber el límite de prendas que se pueden agregar

    public Guardarropa(Usuario usuario) {
        this.usuario = requireNonNull(usuario, "Debe ingresar un usuario");
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

        if(this.usuario.tieneLimiteDePrendas() && this.cantidadDePrendas>=this.usuario.limiteDePrendas()) {
            throw new SuperaLimiteDePrendasException ("Se supera el límite de "+this.usuario.limiteDePrendas() + " prendas definido para el tipo de usuario del guardarropa");
        }
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
        this.cantidadDePrendas++;

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
        // obtener prendas de cada clima adecuado y pasar esas por param
        // si hace menos de x cantidad de grados, el producto cartesiano cambia
        // porque atuendo puede tener mas de una prenda superior
        return Sets.cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios)
                .stream()
                .map((list) -> new Atuendo((list.get(0)), list.get(1), list.get(2), list.get(3)))
                .collect(toList());
    }

    public void definirMeteorologo(Meteorologo meteorologo) {
        this.meteorologo = meteorologo;
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
