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
@Table(name = "guardarropas")
public class Guardarropa {


    @Id
    @GeneratedValue
    @Column(name = "guardarropa_id",columnDefinition = "int(11) NOT NULL")
    private long id;
    @Column(name = "limite_prendas")
    private int limitePrendas=0;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guardarropa",fetch = FetchType.EAGER)
    private Set<Prenda> prendasSuperiores = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "guardarropa",fetch = FetchType.EAGER)
    private Set<Prenda> accesorios = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "guardarropa",fetch = FetchType.EAGER)
    private Set<Prenda> prendasInferiores = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "guardarropa", fetch = FetchType.EAGER)
    private Set<Prenda> calzados = new HashSet<>();

    @ManyToMany (cascade = CascadeType.ALL,mappedBy = "guardarropas",fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>();

    @Column(name = "nombre_guardarropa")
    private String nombreGuardarropa;


    public Guardarropa(){}

    public Guardarropa(String nombreGuardarropa,  int limitePrendas) {
        this.nombreGuardarropa = requireNonNull(nombreGuardarropa, "Debe ingresar un nombre para el guardarropa");
        this.limitePrendas = limitePrendas;
    }

    public void agregarUsuario(Usuario usuario) {
        //System.out.println("agrega a: "+usuario.getNombreUsuario());
        this.usuarios.add(usuario);
    }

    public void setLimiteDePrendas(int limitePrendas) {
        if (this.obtenerCantidadDePrendas() > limitePrendas) {
            throw new SuperaLimiteDePrendasException("El nuevo límite de prendas es menor que la cantidad de prendas del guardarropa.\n Cantidad de prendas: "+this.obtenerCantidadDePrendas());
        }
        this.limitePrendas = limitePrendas;
    }

    public boolean tieneLimiteDePrendas() {
        return this.limitePrendas>0;
    }

    public int getlimiteDePrendas() {
        return this.limitePrendas;
    }
    public long getId() {
        return id;
    }


    public int obtenerCantidadDePrendas() {
        return (this.prendasSuperiores.size() + this.prendasInferiores.size() + this.accesorios.size() + this.calzados.size());
    }

    public Set<Prenda> getPrendasSuperiores() { return prendasSuperiores; }

    public Set<Prenda> getPrendasInferiores() {
        return prendasInferiores;
    }

    public Set<Prenda> getCalzados() {
        return calzados;
    }

    public Set<Prenda> getAccesorios() {
        return accesorios;
    }

    public Set<Prenda> obtenerPrendasSuperioresDisponibles() {
        return prendasSuperiores.stream().filter(prenda -> prenda.getDisponibilidad() && prenda.esParteSuperior()).collect(Collectors.toSet());
    }

    public Set<Prenda> obtenerPrendasInferioresDisponibles() {
        return prendasInferiores.stream().filter(prenda -> prenda.getDisponibilidad() && prenda.obtenerCategoria() == Categoria.PARTE_INFERIOR).collect(Collectors.toSet());
    }

    public Set<Prenda> obtenerCalzadosDisponibles() {
        return calzados.stream().filter(prenda -> prenda.getDisponibilidad() && prenda.obtenerCategoria() == Categoria.CALZADO).collect(Collectors.toSet());
    }

    public Set<Prenda> obtenerAccesoriosDisponibles() {
        return accesorios.stream().filter(prenda -> prenda.getDisponibilidad() && prenda.esAccesorioGeneral()).collect(Collectors.toSet());
    }

    public Set<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public void verificarLimiteDePrendas() {
        if (this.tieneLimiteDePrendas() && this.obtenerCantidadDePrendas() >= this.getlimiteDePrendas()) {
            System.out.println("Entra en el limite de prendas");
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
        return Objects.equals(prendasInferiores, guardarropa.getPrendasInferiores()) &&
                Objects.equals(prendasSuperiores, guardarropa.getPrendasSuperiores()) &&
                Objects.equals(calzados, guardarropa.getCalzados()) &&
                Objects.equals(accesorios, guardarropa.getAccesorios()) &&
                Objects.equals(usuarios, guardarropa.obtenerUsuarios());
    }


    public String getNombreGuardarropa() {
        return nombreGuardarropa;
    }
    public void setNombreGuardarropa(String nombreGuardarropa) {
        this.nombreGuardarropa = nombreGuardarropa;
    }

}
