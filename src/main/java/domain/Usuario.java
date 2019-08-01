package domain;


//import java.util.HashSet;
//import java.util.Set;

import java.time.LocalDateTime;
import java.util.*;

import domain.clima.Clima;
import domain.notificacion.Notificador;
import domain.clima.Alerta;
import domain.prenda.TipoDePrenda;
import domain.prenda.Categoria;
import domain.usuario.*;
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
    private AtuendosSugeridosPorEvento atuendosSugeridosProximoEvento = new AtuendosSugeridosPorEvento(new ArrayList<Atuendo>(), new Evento("","", LocalDateTime.now(),Periodo.NINGUNO,0));
    // agregado de sensibilidades en las partes del cuerpo. Hacemos una escala que va de 1 a 10 (1 para muy friolento hasta 10 para muy caluroso)
    private Sensibilidad sensibilidadGeneral;
    private Sensibilidad sensibilidadManos;
    private Sensibilidad sensibilidadCuello;
    private Sensibilidad sensibilidadParteSuperior;
    private Sensibilidad sensibilidadParteInferior;

    //
    // variable que indique con cuanto tiempo antes quiere que le llegue sugerencia sobre evento

    // alertador le pide al repo que usuarios ejecutar (los filtra para saber a quienes notificar en base al tiempo de anticipacion que tenga el user)
    // a. notificador tiene que obtener de usuario proximo evento y tiempo de anticipacion y en base a eso devuelve si quiere o no ser notificado
    // se le pide el proximo evento al user, se obtiene el clima de mismo
    // b. se genera sugerencia con ese clima

    public Usuario(TipoUsuario tipoUsuario, String numeroDeCelular) {
        this.tipoUsuario = tipoUsuario;
        this.numeroDeCelular = numeroDeCelular;
        RepositorioDeUsuarios.getInstance().agregarUsuarioTotal(this);
    }

    public void generarSugerenciasParaProximoEvento() {
        List<Atuendo> atuendosSugeridos = new ArrayList<>();
        Evento proximoEvento = this.calendario.obtenerProximoEvento();
        for (Guardarropa guardarropa : guardarropas) {
            int diaEvento = -1;
            for(int i = 0; i < 5; i++) {
                if(proximoEvento.getFecha().minusDays(i).toLocalDate().equals(LocalDateTime.now().toLocalDate())) { diaEvento = i; }
            }
            if(diaEvento != -1) {
                Clima climaEvento = guardarropa.obtenerMeteorologo().obtenerClima(diaEvento);
                atuendosSugeridos.addAll(guardarropa.generarSugerencia(climaEvento, proximoEvento));
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

    public int obtenerLimiteDePrendas() {
        return this.tipoUsuario.limiteDePrendas();
    }

    public boolean tieneLimiteDePrendas() {
        return this.tipoUsuario.tieneLimiteDePrendas();
    }

    public Set<Guardarropa> obtenerGuardarropas() {
        return this.guardarropas;
    }
    //
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
        notificadores.forEach(notificador -> notificador.notificar(this, mensaje));
    }

    public boolean seDebeResugerir(Alerta alerta) {
        if(alerta == LLUVIA) {
            return this.atuendosSugeridosProximoEvento.getAtuendosSugeridos().stream().noneMatch(atuendo -> atuendo.esAptoParaLluvia());
        } else {
            return this.atuendosSugeridosProximoEvento.getAtuendosSugeridos().stream().noneMatch(atuendo -> atuendo.obtenerAccesorio().obtenerTipoDePrenda() == TipoDePrenda.CASCO);
        }
    }

    public AtuendosSugeridosPorEvento obtenerAtuendosSugeridosProximoEvento() {
        return atuendosSugeridosProximoEvento;
    }

    public void calificarFrioEnManos(Atuendo atuendo, double temperatura) {
        this.sensibilidadManos = new Sensibilidad(temperatura, TipoSensibilidad.FRIO,atuendo);
    }
    public void calificarCalorEnManos(Atuendo atuendo, double temperatura) {
        this.sensibilidadManos = new Sensibilidad(temperatura, TipoSensibilidad.CALOR,atuendo);
    }
    public void calificarFrioEnCuello(Atuendo atuendo, double temperatura) {
        this.sensibilidadCuello = new Sensibilidad(temperatura, TipoSensibilidad.FRIO,atuendo);
    }
    public void calificarCalorEnCuello(Atuendo atuendo, double temperatura) {
        this.sensibilidadCuello = new Sensibilidad(temperatura, TipoSensibilidad.CALOR,atuendo);
    }
    public void calificarFrioEnParteSuperior(Atuendo atuendo, double temperatura) {
        this.sensibilidadParteSuperior= new Sensibilidad(temperatura, TipoSensibilidad.FRIO,atuendo);
    }
    public void calificarCalorEnParteSuperior(Atuendo atuendo,double temperatura) {
        this.sensibilidadParteSuperior = new Sensibilidad(temperatura, TipoSensibilidad.CALOR,atuendo);
    }
    public void calificarFrioEnParteInferior(Atuendo atuendo,double temperatura) {
        this.sensibilidadParteInferior= new Sensibilidad(temperatura, TipoSensibilidad.FRIO,atuendo);
    }
    public void calificarCalorEnParteInferior(Atuendo atuendo,double temperatura) {
        this.sensibilidadParteInferior = new Sensibilidad(temperatura, TipoSensibilidad.CALOR,atuendo);
    }


    public void calificarNormalEnManos(Atuendo atuendo, double temperatura) {
        this.sensibilidadManos = new Sensibilidad(temperatura, TipoSensibilidad.NORMAL,atuendo);
    }

    public void calificarNormalEnCuello(Atuendo atuendo, double temperatura) {
        this.sensibilidadCuello = new Sensibilidad(temperatura, TipoSensibilidad.NORMAL,atuendo);
    }

    public void calificarNormalEnParteSuperior(Atuendo atuendo, double temperatura) {
        this.sensibilidadParteSuperior= new Sensibilidad(temperatura, TipoSensibilidad.NORMAL,atuendo);
    }

    public void calificarNormalEnParteInferior(Atuendo atuendo,double temperatura) {
        this.sensibilidadParteInferior = new Sensibilidad(temperatura, TipoSensibilidad.NORMAL, atuendo);
    }

    public void verificarSiHayEventoProximo(){
        Clima clima=new Clima(1/11/10,10,20,20,20);
        List<Evento> eventosProximos=calendario.eventosProximos();
        eventosProximos.forEach(evento -> sugerenciaParaEvento(evento,clima));
    }

    public void sugerenciaParaEvento(Evento evento,Clima clima){
        this.guardarropas.forEach(guardarropa -> guardarropa.generarSugerencia(clima,evento));
    }

    public void agregarGuardarropa(Guardarropa guardarropa) {
        this.guardarropas.add(guardarropa);
    }

    public void setTiempoDeAnticipacion(int tiempoDeAnticipacion) {
        this.tiempoDeAnticipacion = tiempoDeAnticipacion;
    }
}
