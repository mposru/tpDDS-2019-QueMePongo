package domain;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import domain.Notificacion.Notificador;
import domain.TipoDeUsuario.Gratuito;
import domain.TipoDeUsuario.Premium;
import domain.TipoDeUsuario.TipoUsuario;
import domain.Transiciones.*;
import domain.clima.Alerta;
import exceptions.*;

public class Usuario {
    private Set<Guardarropa> guardarropas = new HashSet<>();
    private Deque<Decision> decisiones = new LinkedList<>();
    private TipoUsuario tipoUsuario;
    private String numeroDeCelular;
    private ArrayList<Atuendo> atuendosAceptados = new ArrayList<>();
    private ArrayList<Atuendo> atuendosRechazados = new ArrayList<>();
    private Set<Evento> eventos = new HashSet<>();
    private Set<Notificador> notificadores = new HashSet<>();

    public Usuario(TipoUsuario tipoUsuario, String numeroDeCelular) {
        this.tipoUsuario = tipoUsuario;
        this.numeroDeCelular = numeroDeCelular;
    }

    public Deque<Decision> obtenerDecisiones() {
        return this.decisiones;
    }

    public ArrayList<Atuendo> obtenerAtuendosAceptados() {
        return this.atuendosAceptados;
    }

    public ArrayList<Atuendo> obtenerAtuendosRechazados() {
        return this.atuendosRechazados;
    }

    public void cambiarAPremium() {
        this.tipoUsuario = Premium.getInstance();
    }

    public void cambiarAGratuito() {
        this.tipoUsuario = Gratuito.getInstance();
    }

    public int limiteDePrendas() {
        return this.tipoUsuario.limiteDePrendas();
    }

    public boolean tieneLimiteDePrendas() {
        return this.tipoUsuario.tieneLimiteDePrendas();
    }

    public Set<Guardarropa> obtenerGuardarropas() {
        return this.guardarropas;
    }

    public void aceptarAtuendo(Atuendo atuendo) {
        atuendo.aceptar();
        this.atuendosAceptados.add(atuendo); //validar que el atuendo no se pueda aceptar dos veces.
        this.decisiones.push(new Aceptar(atuendo));
    }

    public void rechazarAtuendo(Atuendo atuendo) {
        atuendo.rechazar();
        atuendosRechazados.add(atuendo); //validar que el atuendo no se pueda rechazar dos veces.
        this.decisiones.push(new Rechazar(atuendo));
    }

    public void calificarAtuendo(Atuendo atuendo, int nuevaCalificacion) {
        atuendo.calificar(nuevaCalificacion);
        if (atuendo.estaCalificado()) {
            this.decisiones.push(new Recalificar(atuendo));
        } else {
            this.decisiones.push(new Calificar(atuendo));
        }
    }

    public void deshacer() { //deshacemos el último cambio
        if (decisiones.isEmpty()) {
            throw new PilaVaciaException("No hay decisiones por deshacer");
        }
        this.decisiones.pop().deshacer(this);
    }

    public void agregarEvento(String nombre, String ubicacion, LocalDateTime fecha) {
        Evento nuevoEvento = new Evento(nombre, ubicacion, fecha);
        this.eventos.add(nuevoEvento);
    }

    public void validarEventoDia() {
        Set<Evento> eventosDeHoy = eventos.stream().filter( evento -> evento.esHoy()).collect(Collectors.toSet());
        if (eventosDeHoy.isEmpty()) {
            throw new NoHayEventoCercanoException("No hay ningún evento para hoy");
        }
    }

    public String getNumeroDeCelular() {
        return numeroDeCelular;
    }

    public void agregarNotificador(Notificador notificador) {
        notificadores.add(notificador);
    }

    public void quitarNotificador(Notificador notificador) {
        notificadores.remove(notificador);
    }

    //recibirAlertas(List<Alerta> alertas) {
    //      foreach alerta -> this.recibirAlerta(alerta)


    public void recibirAlerta(Alerta alerta) {
        // Chequear que el atuendo no se ya adecuado
        for(Notificador notificador: notificadores) {
            if(alerta == Alerta.LLUVIA) {
                notificador.notificar(this, "Alerta meteorologica de lluvia");
            }
            if(alerta == Alerta.GRANIZO) {
                notificador.notificar(this, "Alerta meteorologica de granizo");
            }
            if(alerta == Alerta.VIENTO) {
                notificador.notificar(this, "Alerta meteorologica de vientos fuertes");
            }
        }
    }
}
