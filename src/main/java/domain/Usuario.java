package domain;

import java.time.LocalDateTime;
import java.util.*;

import domain.clima.Clima;
import domain.notificacion.Notificador;
import domain.clima.Alerta;
import domain.prenda.TipoDePrenda;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.tipoDeUsuario.Gratuito;
import domain.usuario.tipoDeUsuario.Premium;
import domain.usuario.tipoDeUsuario.TipoUsuario;
import domain.usuario.transiciones.*;
import exceptions.*;

import static domain.clima.Alerta.GRANIZO;
import static domain.clima.Alerta.LLUVIA;
import static java.time.LocalDate.now;

public class Usuario {
    private Set<Guardarropa> guardarropas = new HashSet<>();
    private Deque<Decision> decisiones = new LinkedList<>();
    private TipoUsuario tipoUsuario;
    private String numeroDeCelular;
    private ArrayList<Atuendo> atuendosAceptados = new ArrayList<>();
    private ArrayList<Atuendo> atuendosRechazados = new ArrayList<>();
    private Set<Notificador> notificadores = new HashSet<>();
    private Calendario calendario = new Calendario();
    private int tiempoDeAnticipacion = 0; // variable que indica con cuanto tiempo antes quiere que le llegue sugerencia sobre evento (en horas)
    private AtuendosSugeridosPorEvento atuendosSugeridosProximoEvento = new AtuendosSugeridosPorEvento(new ArrayList<Atuendo>(), new Evento("","", LocalDateTime.now()));

    // alertador le pide al repo que usuarios ejecutar (los filtra para saber a quienes notificar en base al tiempo de anticipacion que tenga el user)
    // a. notificador tiene que obtener de usuario proximo evento y tiempo de anticipacion y en base a eso devuelve si quiere o no ser notificado
    // se le pide el proximo evento al user, se obtiene el clima de mismo
    // b. se genera sugerencia con ese clima

    public Usuario(TipoUsuario tipoUsuario, String numeroDeCelular) {
        this.tipoUsuario = tipoUsuario;
        this.numeroDeCelular = numeroDeCelular;
    }

    public void generarSugerenciasParaProximoEvento() {
        List<Atuendo> atuendosSugeridos = new ArrayList<>();
        Evento proximoEvento = this.calendario.obtenerProximoEvento();
        for (Guardarropa guardarropa : guardarropas) {
            int diaEvento = -1;
            for(int i = 0; i < 5; i++) {
                if(proximoEvento.getFecha().minusDays(i).equals(LocalDateTime.now().toLocalDate())) { diaEvento = i; }
            }
            if(diaEvento != -1) {
                Clima climaEvento = guardarropa.obtenerMeteorologo().obtenerClima(diaEvento);
                atuendosSugeridos.addAll(guardarropa.generarSugerencia(this, climaEvento, this.calendario.obtenerProximoEvento()));
            }
        }
        this.atuendosSugeridosProximoEvento = new AtuendosSugeridosPorEvento(atuendosSugeridos, proximoEvento);
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
        atuendo.calificar(nuevaCalificacion); // se califica por parte de cuerpo
        // factor de abrigo (sensibilidad) que es modificada por calificacion
        // se relaciona directamente con temperatura
        // calcular en base a una recta el factor de abrigo entre prenda y temperatura (excel recta)
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
        this.calendario.agregarEvento(nuevoEvento);
    }

    public void validarEventoDia() {
        List<Evento> eventosDeHoy = calendario.obtenerEventosPorFecha(now());
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

    public void recibirAlertas(List<Alerta> alertas) {
        for(Alerta alerta : alertas) {
            this.recibirAlerta(alerta);
        }
    }

    public void recibirAlerta(Alerta alerta) {
        for(Notificador notificador: notificadores) {
            if(alerta == LLUVIA && this.seDebeResugerir(LLUVIA)) {
                this.generarSugerenciasParaProximoEvento();
                notificador.notificar(this, "Alerta meteorologica de lluvia");
            }
            if(alerta == GRANIZO && this.seDebeResugerir(GRANIZO)) {
                this.generarSugerenciasParaProximoEvento();
                notificador.notificar(this, "Alerta meteorologica de granizo");
            }
        }
    }

    public Calendario getCalendario(){
        return this.calendario;
    }

    public boolean quiereSerNotificado() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaProximoEvento = this.getCalendario().obtenerProximoEvento().getFecha();
        return (ahora.isBefore(fechaProximoEvento)
                && ahora.isAfter(fechaProximoEvento.minusHours(tiempoDeAnticipacion)))
                || ahora.isEqual(fechaProximoEvento.minusHours(tiempoDeAnticipacion));
    }

    public boolean sugerenciasListasParaProximoEvento() {
        return (atuendosSugeridosProximoEvento.getEvento().getFecha() == this.calendario.obtenerProximoEvento().getFecha()
        && atuendosSugeridosProximoEvento.getEvento().getNombre() == this.calendario.obtenerProximoEvento().getNombre()
        && atuendosSugeridosProximoEvento.getEvento().getUbicacion() == this.calendario.obtenerProximoEvento().getUbicacion());
    }

    public Set<Notificador> getNotificadores() {
        return notificadores;
    }

    public void notificar(String mensaje) {
        for (Notificador notificador : notificadores) {
            notificador.notificar(this, mensaje);
        }
    }

    public boolean seDebeResugerir(Alerta alerta) {
        if(alerta == LLUVIA) {
            return !this.atuendosSugeridosProximoEvento.getAtuendosSugeridos().stream().anyMatch(atuendo -> atuendo.obtenerAccesorio().obtenerTipoDePrenda() == TipoDePrenda.PARAGUAS);
        } else {
            return !this.atuendosSugeridosProximoEvento.getAtuendosSugeridos().stream().anyMatch(atuendo -> atuendo.obtenerAccesorio().obtenerTipoDePrenda() == TipoDePrenda.CASCO);
        }
    }

    public AtuendosSugeridosPorEvento obtenerAtuendosSugeridosProximoEvento() {
        return atuendosSugeridosProximoEvento;
    }
}
