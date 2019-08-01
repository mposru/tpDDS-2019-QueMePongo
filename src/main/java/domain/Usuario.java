package domain;


//import java.util.HashSet;
//import java.util.Set;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import domain.clima.Clima;
import domain.prenda.Categoria;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.Sensibilidad;
import domain.usuario.TipoSensibilidad;
import domain.usuario.tipoDeUsuario.Gratuito;
import domain.usuario.tipoDeUsuario.Premium;
import domain.usuario.tipoDeUsuario.TipoUsuario;
import domain.usuario.transiciones.*;
import exceptions.*;

import static java.time.LocalDate.now;

public class Usuario {
    private Set<Guardarropa> guardarropas = new HashSet<>();
    private Deque<Decision> decisiones = new LinkedList<>();
    private TipoUsuario tipoUsuario;
    private ArrayList<Atuendo> atuendosAceptados = new ArrayList<>();
    private ArrayList<Atuendo> atuendosRechazados = new ArrayList<>();
    private Calendario calendario = new Calendario();
    // agregado de sensibilidades en las partes del cuerpo. Hacemos una escala que va de 1 a 10 (1 para muy friolento hasta 10 para muy caluroso)
    private Sensibilidad sensibilidadGeneral;
    private Sensibilidad sensibilidadManos;
    private Sensibilidad sensibilidadCuello;
    private Sensibilidad sensibilidadParteSuperior;
    private Sensibilidad sensibilidadParteInferior;

    //
    // variable que indique con cuanto tiempo antes quiere que le llegue sugerencia sobre evento

    // alertador le pide al repo que usuarios ejecutar (los filtra para saber a quienes notificar en base al tiempo de anticipacion que tenga el user)
    // notificador tiene que obtener de usuario proximo evento y tiempo de anticipacion y en base a eso devuelve si quiere o no ser notificado
    // se le pide el proximo evento al user, se obtiene el clima de mismo
    // se genera sugerencia con ese clima

    public Usuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public void calificarPrenda(Prenda prenda) {

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

    public Calendario getCalendario(){
        return this.calendario;
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





}
