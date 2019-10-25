package domain;

//import java.util.HashSet;
//import java.util.Set;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import domain.clima.Clima;
import domain.guardarropa.Gratuito;
import domain.notificacion.Notificador;
import domain.clima.Alerta;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
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
@Table(name = "usuario")
public class Usuario {


    @Id
    @GeneratedValue
    @Column(name = "usuario_id",columnDefinition = "int(11) NOT NULL")
    private long id;

    @ManyToMany
    @JoinTable(name = "usuario_guardarropa",joinColumns = @JoinColumn(name="usuario_id"),inverseJoinColumns = @JoinColumn(name = "guardarropa_id"))
    private Set<Guardarropa> guardarropas = new HashSet<>();

  /*  @OneToMany
    @JoinColumn(name = "decision_id")*/
    @Transient
    private LinkedList<Decision> decisiones = new LinkedList<>();
    @Column(name = "numero_celular")
    private String numeroDeCelular;
    private String nombre;
    private String email;
    private String contraseniaHash;

   /* @OneToMany
    @JoinTable(name="atuendo")
    @JoinColumn(name = "atuendo_id",columnDefinition = "int(11) NOT NULL")*/
   @Transient
    private List<Atuendo> atuendosAceptados = new ArrayList<>();

  /*  @OneToMany
    @JoinTable(name="atuendo")
    @JoinColumn(name = "atuendo_id",columnDefinition = "int(11) NOT NULL")*/
  @Transient
    private List<Atuendo> atuendosRechazados = new ArrayList<>();

    @Transient
    private Set<Notificador> notificadores = new HashSet<>();

   @OneToOne (cascade = CascadeType.ALL) //si borro el usuario me borra su calendario
   @JoinColumn(name = "calendario_id")
   private Calendario calendario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

   @Column(name = "tiempo_anticipacion")
    private int tiempoDeAnticipacion = 0; // variable que indica con cuanto tiempo antes quiere que le llegue sugerencia sobre evento (en horas)

    @Transient
    private AtuendosSugeridosPorEvento atuendosSugeridosProximoEvento = new AtuendosSugeridosPorEvento(new ArrayList<Atuendo>(), new Evento("", "", LocalDateTime.now(), Periodo.NINGUNO, 0));
    // agregado de sensibilidades en las partes del cuerpo. Hacemos una escala que va de 1 a 10 (1 para muy friolento hasta 10 para muy caluroso)

    @Transient
    private Sensibilidad sensibilidad = new Sensibilidad();

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    private String contrasenia;



    //
    // variable que indique con cuanto tiempo antes quiere que le llegue sugerencia sobre evento

    // alertador le pide al repo que usuarios ejecutar (los filtra para saber a quienes notificar en base al tiempo de anticipacion que tenga el user)
    // a. notificador tiene que obtener de usuario proximo evento y tiempo de anticipacion y en base a eso devuelve si quiere o no ser notificado
    // se le pide el proximo evento al user, se obtiene el clima de mismo
    // b. se genera sugerencia con ese clima

    public Usuario() {} // solo para JPA
    public Usuario(String numeroDeCelular,Calendario miCalendario,String contrasenia,String email, String nombre) {
        this.numeroDeCelular = numeroDeCelular;
        this.calendario = miCalendario;
        this.contrasenia = contrasenia;
        this.email=email;
        this.contraseniaHash = SHA1.getInstance().convertirConHash(contrasenia);
        this.nombre = nombre;
        RepositorioDeUsuarios.getInstance().agregarUsuarioTotal(this);
    }

    public void validarContraseniaHash(String contrasenia){
        if(!this.contraseniaHash.equals(contrasenia)){
            throw  new ContraseniaInvalidaException("Contraseña invalida");
        }
    }

    public String getNombre() { return this.nombre; }

    public String getEmail() { return this.email; }

    public void calificarSensibilidadGeneral(CalificacionSensibilidad calificacionSensibilidad) {
        this.sensibilidad.calificarSensibilidad(calificacionSensibilidad, "general");
    }

    public void calificarSensibilidadEnManos(CalificacionSensibilidad calificacionSensibilidad) {
        this.sensibilidad.calificarSensibilidad(calificacionSensibilidad, "manos");
    }

    public void calificarSensibilidadEnCuello(CalificacionSensibilidad calificacionSensibilidad) {
        this.sensibilidad.calificarSensibilidad(calificacionSensibilidad, "cuello");
    }

    public double getFactorSensibilidadGeneral() {
        return this.sensibilidad.getFactorSensibilidad("general");
    }

    public double getFactorSensibilidadEnManos() {
        return this.sensibilidad.getFactorSensibilidad("manos");
    }

    public double getFactorSensibilidadEnCuello() {
        return this.sensibilidad.getFactorSensibilidad("cuello");
    }
    public void setNumeroDeCelular(String numeroDeCelular) {
        this.numeroDeCelular = numeroDeCelular;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
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


    public Set<Guardarropa> getGuardarropas() {
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
            return this.atuendosSugeridosProximoEvento.getAtuendosSugeridos().stream().noneMatch(atuendo -> atuendo.obtenerAccesorio().obtenerTipoDePrenda() == TipoDePrenda.CASCO);
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

    public List<Atuendo> obtenerSugerenciasDeEvento(Evento evento) {
        List<Atuendo> sugerencias = new ArrayList<>();
        this.guardarropas.forEach(guardarropa -> guardarropa.generarSugerencia(evento, sensibilidad).forEach(sugerencia -> sugerencias.add(sugerencia)));
        return sugerencias;
    }

    public void agregarGuardarropa(Guardarropa guardarropa) {
        this.guardarropas.add(guardarropa);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setTiempoDeAnticipacion(int tiempoDeAnticipacion) {
        this.tiempoDeAnticipacion = tiempoDeAnticipacion;
    }

    public long getId() {
        return this.id;
    }

    public List<Evento> obtenerEventos() {
        return calendario.obtenerEventos().stream().sorted(Comparator.comparing(Evento::getFecha)).collect(Collectors.toList());
    }

    public Guardarropa buscarGuardarropaPorNombre(String nombre){
        List<Guardarropa> guardarropaEncontrados = guardarropas.stream()
                .filter(guardarropa -> guardarropa.getNombreGuardarropa().equals(nombre))
                .collect(Collectors.toList());
        if(guardarropaEncontrados.isEmpty()) {
            return null;
        }
        else {
            return guardarropaEncontrados.get(0);
        }
    }


    public Guardarropa verificarSiIdDeGuardarropa(String id){
        return this.guardarropas.stream()
                .filter(guardarropaAux->Integer.parseInt(id)==guardarropaAux.getId())
                .findAny()
                .orElseThrow(()->new NoExisteGuardarropaException("El id Guardarropa no exite en el usuario"));
    }
}
