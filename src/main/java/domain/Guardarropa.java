package domain;

import com.google.common.collect.Sets;
import domain.usuario.Evento;
import domain.usuario.Sensibilidad;
import exceptions.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import domain.guardarropa.TipoDeGuardarropa;


import static java.util.Objects.requireNonNull;
@Entity
public class Guardarropa {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private Set<Prenda> accesorios = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "prenda_superiore_id")
    private Set<Prenda> prendasSuperiores = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "prenda_inferior_id")
    private Set<Prenda> prendasInferiores = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "calzado_id")
    private Set<Prenda> calzados = new HashSet<>();

    @ManyToMany
    private Set<Usuario> usuarios;

    private TipoDeGuardarropa tipoDeGuardarropa;

    public Guardarropa(Set<Usuario> usuarios, TipoDeGuardarropa tipoDeGuardarropa) {
        this.usuarios = requireNonNull(usuarios, "Debe ingresar un conjunto de usuarios");
        this.tipoDeGuardarropa = requireNonNull(tipoDeGuardarropa,"Debe ingresar el tipo de guardarropa");
    }
    public void setLimiteDePrendas(int limiteDePrendas) {
        this.tipoDeGuardarropa.setLimiteDePrendas(limiteDePrendas);
    }

    public boolean tieneLimiteDePrendas() {
        return this.tipoDeGuardarropa.tieneLimiteDePrendas();
    }

    public int getlimiteDePrendas() {
        return this.tipoDeGuardarropa.getLimiteDePrendas();
    }

    public void cambiarTipoDeGuardarropa(TipoDeGuardarropa tipoDeGuardarropa) {
        this.tipoDeGuardarropa = tipoDeGuardarropa;
    }

    public int obtenerCantidadDePrendas() {
        return (this.prendasSuperiores.size() + this.prendasInferiores.size() + this.accesorios.size() + this.calzados.size());
    }

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

    public Set<Prenda> obtenerPrendasSuperioresDisponibles() {
        return prendasSuperiores.stream().filter(prenda -> prenda.getDisponibilidad()).collect(Collectors.toSet());
    }

    public Set<Prenda> obtenerPrendasInferioresDisponibles() {
        return prendasInferiores.stream().filter(prenda -> prenda.getDisponibilidad()).collect(Collectors.toSet());
    }

    public Set<Prenda> obtenerCalzadosDisponibles() {
        return calzados.stream().filter(prenda -> prenda.getDisponibilidad()).collect(Collectors.toSet());
    }

    public Set<Prenda> obtenerAccesoriosDisponibles() {
        return accesorios.stream().filter(prenda -> prenda.getDisponibilidad()).collect(Collectors.toSet());
    }

    public Set<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public void verificarLimiteDePrendas() {
        if (this.tieneLimiteDePrendas() && this.obtenerCantidadDePrendas() >= this.getlimiteDePrendas()) {
            throw new SuperaLimiteDePrendasException("Se supera el límite de " + this.getlimiteDePrendas() + " prendas definido para el tipo de guardarropa");
        }
    }

    public void guardarPrenda(Prenda prenda) {
        this.verificarLimiteDePrendas();
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
            case ACCESORIO_CUELLO:
            case ACCESORIO_CABEZA:
            case ACCESORIO_MANOS:
                accesorios.add(prenda);
                break;
        }
    }

    private void validarPrendas(Set<Prenda> prendasSuperioresValidas, Set<Prenda> prendasInferioresValidas,
                                Set<Prenda> calzadosValidos, Set<Prenda> accesoriosValidos) {
        //todo: mandar en el mensaje de error el clima. se concatena en la misma linea que se tira error
        String mensajeDeError = "";
        if (prendasSuperioresValidas.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan prendas superiores adecuadas para el clima del evento. ");
        }
        if (prendasInferioresValidas.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan prendas inferiores adecuadas para el clima del evento. ");
        }
        if (calzadosValidos.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan zapatos adecuados para el clima del evento. ");
        }
        if (accesoriosValidos.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan accesorios adecuados para el clima del evento. ");
        }
        if (mensajeDeError != "") {
            throw new FaltaPrendaException(mensajeDeError);
        }
    }

    public List<Atuendo> generarSugerencia(Evento evento, Sensibilidad sensibilidad) {
        this.validarPrendas(this.prendasSuperiores,this.prendasInferiores,this.calzados,this.accesorios);
        // pedirle al evento el clima y sensibilidad,

        // clima del dia (y ver si llueve o no) y evento (por si es formal o no???) como param
        // en generar sugerencia, para obtener las prendas validas, se le
        // va a preguntar a los usuarios "dueños" el listado de atuendosAceptados y esas
        // prendas no van a poder ser usadas
        // si las prendas que el usuario está pidiendo *si* pueden usarse acá, habria que tambien pasar el usuario por parametro
        // y de esa manera sí se van a poder devolver prendas que pertenezcan a sus atuendosAceptados
        // solo por una cantidad de tiempo no es usable
        // delegar en otro objeto

        // se filtra despues de crear sugerencia

        // guardarropas trackea cuales estan libres y cuales no
        Set<Set<Prenda>> prendasInferioresAdecuadas = FiltradorDePrendas.getInstance().filtrarPrendas(this.obtenerPrendasInferioresDisponibles(), evento.obtenerClima(), sensibilidad, "general");
        Set<Set<Prenda>> calzadosAdecuados = FiltradorDePrendas.getInstance().filtrarPrendas(this.obtenerCalzadosDisponibles(), evento.obtenerClima(), sensibilidad, "general");
        Set<Set<Prenda>> accesoriosAdecuados = FiltradorDePrendas.getInstance().filtrarPrendas(this.obtenerAccesoriosDisponibles(), evento.obtenerClima(), sensibilidad, "general");
        Set<Set<Prenda>> prendasSuperioresAdecuadas = FiltradorDePrendas.getInstance().filtrarPrendas(this.obtenerPrendasSuperioresDisponibles(), evento.obtenerClima(), sensibilidad, "general");

        return Sets.cartesianProduct(prendasSuperioresAdecuadas, prendasInferioresAdecuadas, calzadosAdecuados, accesoriosAdecuados)
                .stream()
                .map(atuendo -> new Atuendo(atuendo.get(0), atuendo.get(1), atuendo.get(2), atuendo.get(3)))
                .collect(Collectors.toList());
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
                Objects.equals(usuarios, guardarropa.obtenerUsuarios());
    }



}
