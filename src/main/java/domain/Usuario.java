package domain;

//import java.util.HashSet;
//import java.util.Set;
import java.util.*;
import exceptions.*;

public class Usuario {
    private Set<Guardarropa> guardarropas = new HashSet<>();
    private Deque<Decision> decisiones = new LinkedList<>();
    private TipoUsuario tipoUsuario;
    private ArrayList<Atuendo> atuendosAceptados = new ArrayList<>();
    private ArrayList<Atuendo> atuendosRechazados = new ArrayList<>();

    public Usuario () {
        this.tipoUsuario = new Gratuito(); // por defecto instanciamos siempre usuarios gratuitos y despu
    }
    public void cambiarAPremium() {
        this.tipoUsuario = new Premium();
    }
    public void cambiarAGratuito() {
        this.tipoUsuario = new Gratuito();
    }
    public int limiteDePrendas(){
        return 20;
    }

    public void agregarGuardarropa(Guardarropa guardarropa) throws Exception {
        guardarropa.definirUsuario(this);
        this.guardarropas.add(guardarropa);
    }

    public Set<Guardarropa> obtenerGuardarropas() {
        return this.guardarropas;
    }

    public void aceptarAtuendo(Atuendo atuendo){
        atuendo.aceptar();
        this.atuendosAceptados.add(atuendo);
        this.decisiones.push(new Aceptar(atuendo));
    }
    public void rechazarAtuendo(Atuendo atuendo) {
        atuendo.rechazar();
        atuendosRechazados.add(atuendo);
        this.decisiones.push(new Rechazar(atuendo));
    }
/*
    public void calificarAtuendo(Atuendo atuendo, int nuevaCalificacion) {
        public String estadoActualAtuendo = atuendo.obtenerEstadoAtuendo();
        atuendo.calificar(nuevaCalificacion);
        if (estadoActualAtuendo.equals("Calificado")){
            this.decisiones.push(new Recalificar(atuendo));
        }
        else
        {
            this.decisiones.push(new Calificar(atuendo));
        }
    }
*/

    public boolean tieneLimiteDePrendas() {
        return true;
    }
    public void deshacer(){ //deshacemos el último cambio
        if(decisiones.isEmpty()) {
            throw new PilaVaciaException("No hay decisiones por deshacer");
        }
        this.decisiones.pop().deshacer();
    }

}
