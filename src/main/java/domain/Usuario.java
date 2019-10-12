package domain;

//import java.util.HashSet;
//import java.util.Set;

import java.time.LocalDateTime;
import java.util.*;

import domain.clima.Clima;
import domain.notificacion.Notificador;
import domain.clima.Alerta;
import domain.prenda.TipoDePrenda;
import domain.usuario.*;
import domain.usuario.transiciones.*;
import exceptions.*;
import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.annotations.Transactional;

import javax.persistence.*;

import static domain.clima.Alerta.GRANIZO;
import static domain.clima.Alerta.LLUVIA;
import static java.time.LocalDate.now;

@Transactional
@Observable
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    private Set<Guardarropa> guardarropas = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "decision_id")
    private LinkedList<Decision> decisiones = new LinkedList<>();

    private String numeroDeCelular;

    @OneToMany
    @JoinColumn(name = "aceptado_id")
    private List<Atuendo> atuendosAceptados = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "rechazado_id")
    private List<Atuendo> atuendosRechazados = new ArrayList<>();

    private Set<Notificador> notificadores = new HashSet<>();

    @OneToOne
    private Calendario calendario;

    private int tiempoDeAnticipacion = 0; // variable que indica con cuanto tiempo antes quiere que le llegue sugerencia sobre evento (en horas)

    @OneToOne
    private AtuendosSugeridosPorEvento atuendosSugeridosProximoEvento = new AtuendosSugeridosPorEvento(new ArrayList<Atuendo>(), new Evento("", "", LocalDateTime.now(), Periodo.NINGUNO, 0));
    // agregado de sensibilidades en las partes del cuerpo. Hacemos una escala que va de 1 a 10 (1 para muy friolento hasta 10 para muy caluroso)
    @Embedded
    private Sensibilidad sensibilidad = new Sensibilidad();

    //
    // variable que indique con cuanto tiempo antes quiere que le llegue sugerencia sobre evento

    // alertador le pide al repo que usuarios ejecutar (los filtra para saber a quienes notificar en base al tiempo de anticipacion que tenga el user)
    // a. notificador tiene que obtener de usuario proximo evento y tiempo de anticipacion y en base a eso devuelve si quiere o no ser notificado
    // se le pide el proximo evento al user, se obtiene el clima de mismo
    // b. se genera sugerencia con ese clima

    public Usuario(String numeroDeCelular,Calendario miCalendario) {
        this.numeroDeCelular = numeroDeCelular;
        this.calendario = miCalendario;
        RepositorioDeUsuarios.getInstance().agregarUsuarioTotal(this);
    }

    public void calificarSensibilidadGeneral(CalificacionSensibilidad calificacionSensibilidad) {
        this.sensibilidad.calificarSensibilidadGeneral(calificacionSensibilidad);
    }

    public void calificarSensibilidadEnManos(CalificacionSensibilidad calificacionSensibilidad) {
        this.sensibilidad.calificarSensibilidadEnManos(calificacionSensibilidad);
    }

    public void calificarSensibilidadEnCuello(CalificacionSensibilidad calificacionSensibilidad) {
        this.sensibilidad.calificarSensibilidadEnCuello(calificacionSensibilidad);
    }

    public double getFactorSensibilidadGeneral() {
        return this.sensibilidad.getFactorSensibilidadGeneral();
    }

    public double getFactorSensibilidadEnManos() {
        return this.sensibilidad.getFactorSensibilidadEnManos();
    }

    public double getFactorSensibilidadEnCuello() {
        return this.sensibilidad.getFactorSensibilidadEnCuello();
    }

    public void generarSugerenciasParaProximoEvento() {
        List<Atuendo> atuendosSugeridos = new ArrayList<>();
        Evento proximoEvento = this.calendario.obtenerProximoEvento();
        for (Guardarropa guardarropa : guardarropas) {

            int diaEvento = -1;
            for (int i = 0; i < 5; i++) {
                if (proximoEvento.getFecha().minusDays(i).toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                    diaEvento = i;
                }
            }
            if (diaEvento != -1) {
                atuendosSugeridos.addAll(guardarropa.generarSugerencia(proximoEvento, sensibilidad));
            }
        }
        this.atuendosSugeridosProximoEvento = new AtuendosSugeridosPorEvento(atuendosSugeridos, proximoEvento);
    }

    public List<Decision> obtenerDecisiones() {
        return this.decisiones;
    }

    public List<Atuendo> obtenerAtuendosAceptados() {
        return this.atuendosAceptados;
    }

    public List<Atuendo> obtenerAtuendosRechazados() {
        return this.atuendosRechazados;
    }


    public Set<Guardarropa> obtenerGuardarropas() {
        return this.guardarropas;
    }

    //
    public void aceptarAtuendo(Atuendo atuendo) {
        atuendo.aceptar();
        this.atuendosAceptados.add(atuendo); //validar que el atuendo no se pueda aceptar dos veces.
        this.decisiones.addFirst(new Aceptar(atuendo));
    }

    public void rechazarAtuendo(Atuendo atuendo) {
        atuendo.rechazar();
        atuendosRechazados.add(atuendo); //validar que el atuendo no se pueda rechazar dos veces.
        this.decisiones.addFirst(new Rechazar(atuendo));
    }

    public void calificarAtuendo(Atuendo atuendo, int nuevaCalificacion) {
        atuendo.calificar(nuevaCalificacion); // se califica por parte de cuerpo
        // factor de abrigo (sensibilidad) que es modificada por calificacion
        // se relaciona directamente con temperatura
        // calcular en base a una recta el factor de abrigo entre prenda y temperatura (excel recta)
        if (atuendo.estaCalificado()) {
            this.decisiones.addFirst(new Recalificar(atuendo));
        } else {
            this.decisiones.addFirst(new Calificar(atuendo));
        }
    }

    public void deshacer() { //deshacemos el último cambio
        if (decisiones.isEmpty()) {
            throw new PilaVaciaException("No hay decisiones por deshacer");
        }
        this.decisiones.removeFirst().deshacer(this);
    }

    public void agregarEvento(String nombre, String ubicacion, LocalDateTime fecha, Periodo periodo, Integer antelacion) {
        Evento nuevoEvento = new Evento(nombre, ubicacion, fecha, periodo, antelacion);
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
        alertas.forEach(this::recibirAlerta);
    }

    public void recibirAlerta(Alerta alerta) {
        for (Notificador notificador : notificadores) {
            if (alerta == LLUVIA && this.seDebeResugerir(LLUVIA)) {
                this.generarSugerenciasParaProximoEvento();
                notificador.notificar(this, "Alerta meteorologica de lluvia");
            }
            if (alerta == GRANIZO && this.seDebeResugerir(GRANIZO)) {
                this.generarSugerenciasParaProximoEvento();
                notificador.notificar(this, "Alerta meteorologica de granizo");
            }
        }
    }

    public Calendario getCalendario() {
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
        notificadores.forEach(notificador -> notificador.notificar(this, mensaje));
    }

    public boolean seDebeResugerir(Alerta alerta) {
        if (alerta == LLUVIA) {
            return this.atuendosSugeridosProximoEvento.getAtuendosSugeridos().stream().noneMatch(atuendo -> atuendo.esAptoParaLluvia());
        } else {
            return this.atuendosSugeridosProximoEvento.getAtuendosSugeridos().stream().noneMatch(atuendo -> atuendo.obtenerAccesorios().stream().anyMatch(acc -> acc.obtenerTipoDePrenda() == TipoDePrenda.CASCO));
        }
    }

    public AtuendosSugeridosPorEvento obtenerAtuendosSugeridosProximoEvento() {
        return atuendosSugeridosProximoEvento;
    }

    public void verificarSiHayEventoProximo() {
        // todo: que es esto?!?!?!?!??!?!
        Clima clima = new Clima(1 / 11 / 10, 10, 20, 20, 20);
        List<Evento> eventosProximos = calendario.eventosProximos();
        eventosProximos.forEach(evento -> sugerenciaParaEvento(evento));
    }

    public void sugerenciaParaEvento(Evento evento) {
        this.guardarropas.forEach(guardarropa -> guardarropa.generarSugerencia(evento, sensibilidad));
    }

    public void agregarGuardarropa(Guardarropa guardarropa) {
        this.guardarropas.add(guardarropa);
    }

    public void setTiempoDeAnticipacion(int tiempoDeAnticipacion) {
        this.tiempoDeAnticipacion = tiempoDeAnticipacion;
    }
}
