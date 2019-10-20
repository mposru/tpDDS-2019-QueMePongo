package domain;

import domain.prenda.Categoria;
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

    private String nombreGuardarropa;

    private TipoDeGuardarropa tipoDeGuardarropa;

    public Guardarropa(String nombreGuardarropa, Set<Usuario> usuarios, TipoDeGuardarropa tipoDeGuardarropa) {
        this.nombreGuardarropa = requireNonNull(nombreGuardarropa, "Debe ingresar un nombre para el guardarropa");
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
        return accesorios.stream().filter(prenda -> prenda.getDisponibilidad() && prenda.esAccesorioGeneral()).collect(Collectors.toSet());
    }

    public Set<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public void verificarLimiteDePrendas() {
        if (this.tieneLimiteDePrendas() && this.obtenerCantidadDePrendas() >= this.getlimiteDePrendas()) {
            throw new SuperaLimiteDePrendasException("Se supera el límite de " + this.getlimiteDePrendas() + " guardarropas definido para el tipo de guardarropa");
        }
    }

    public void guardarPrenda(Prenda prenda) {
        this.verificarLimiteDePrendas();
        switch (prenda.obtenerCategoria()) {
            case CALZADO:
                calzados.add(prenda);
                break;
            case PARTE_SUPERIOR:
            case PARTE_SUPERIOR_ABAJO:
            case PARTE_SUPERIOR_MEDIO:
            case PARTE_SUPERIOR_ARRIBA:
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

    private void validarPrendas() {
        //todo: mandar en el mensaje de error el clima. se concatena en la misma linea que se tira error
        String mensajeDeError = "";
        if (this.obtenerPrendasSuperioresDisponibles().size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan guardarropas superiores. ");
        }
        if (this.obtenerPrendasInferioresDisponibles().size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan guardarropas inferiores. ");
        }
        if (this.obtenerCalzadosDisponibles().size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan zapatos. ");
        }
        if (this.obtenerAccesoriosDisponibles().size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan accesorios. ");
        }
        if (this.obtenerAccesorioCuelloDisponibles().size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan accesorios cuello. ");
        }
        if (this.obtenerAccesorioManosDisponibles().size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan accesorios manos. ");
        }
        if (mensajeDeError != "") {
            throw new FaltaPrendaException(mensajeDeError);
        }
    }

    public Set<Prenda> obtenerAccesorioCuelloDisponibles() {
        return accesorios
                .stream()
                .filter(prenda -> prenda.getDisponibilidad() && prenda.obtenerCategoria() == Categoria.ACCESORIO_CUELLO)
                .collect(Collectors.toSet());
    }

    public Set<Prenda> obtenerAccesorioManosDisponibles() {
        return accesorios
                .stream()
                .filter(prenda -> prenda.getDisponibilidad() && prenda.obtenerCategoria() == Categoria.ACCESORIO_MANOS)
                .collect(Collectors.toSet());
    }

    public List<Atuendo> generarSugerencia(Evento evento, Sensibilidad sensibilidad) {
        this.validarPrendas();
        // pedirle al evento el clima y sensibilidad,

        // clima del dia (y ver si llueve o no) y evento (por si es formal o no???) como param
        // en generar sugerencia, para obtener las guardarropas validas, se le
        // va a preguntar a los usuarios "dueños" el listado de atuendosAceptados y esas
        // guardarropas no van a poder ser usadas
        // si las guardarropas que el usuario está pidiendo *si* pueden usarse acá, habria que tambien pasar el usuario por parametro
        // y de esa manera sí se van a poder devolver guardarropas que pertenezcan a sus atuendosAceptados
        // solo por una cantidad de tiempo no es usable
        // delegar en otro objeto

        // hacer producto cartesiano y dsp filtrar

        return FiltradorDePrendas.getInstance().filtrarYGenerarCombinacion(this.obtenerPrendasSuperioresDisponibles(),
                this.obtenerPrendasInferioresDisponibles(), this.obtenerCalzadosDisponibles(), this.obtenerAccesoriosDisponibles(),
                this.obtenerAccesorioCuelloDisponibles(), this.obtenerAccesorioManosDisponibles(), evento, sensibilidad);
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


    public String getNombreGuardarropa() {
        return nombreGuardarropa;
    }

    public void setNombreGuardarropa(String nombreGuardarropa) {
        this.nombreGuardarropa = nombreGuardarropa;
    }
}
