package domain;

//import java.util.HashSet;
//import java.util.Set;
import java.util.*;

import domain.TipoDeUsuario.Gratuito;
import domain.TipoDeUsuario.Premium;
import domain.TipoDeUsuario.TipoUsuario;
import domain.Transiciones.Aceptar;
import domain.Transiciones.Calificar;
import domain.Transiciones.Decision;
import domain.Transiciones.Rechazar;
import exceptions.*;

public class Usuario {
    private Set<Guardarropa> guardarropas = new HashSet<>();
    private Deque<Decision> decisiones = new LinkedList<>();
    private TipoUsuario tipoUsuario;
    private ArrayList<Atuendo> atuendosAceptados = new ArrayList<>();
    private ArrayList<Atuendo> atuendosRechazados = new ArrayList<>();

    public Usuario (TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public void cambiarAPremium() {
        this.tipoUsuario = new Premium();
    }
    public void cambiarAGratuito() {
        this.tipoUsuario = new Gratuito();
    }
    public int limiteDePrendas(){
        return this.tipoUsuario.limiteDePrendas();
    }
    public boolean tieneLimiteDePrendas() { return this.tipoUsuario.tieneLimiteDePrendas();}

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

    public void calificarAtuendo(Atuendo atuendo, int nuevaCalificacion) {
        atuendo.calificar(nuevaCalificacion);
        this.decisiones.push(new Calificar(atuendo)); //falta si es recalificado.
    }


    public void deshacer(){ //deshacemos el último cambio
        if(decisiones.isEmpty()) {
            throw new PilaVaciaException("No hay decisiones por deshacer");
        }
        this.decisiones.pop().deshacer();
    }

}
