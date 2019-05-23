package domain;

//import java.util.HashSet;
//import java.util.Set;
import java.util.*;

public class Usuario {
    private Set<Guardarropa> guardarropas = new HashSet<>();
    private Deque<Decision> decisiones = new LinkedList<>();
    private TipoUsuario tipoUsuario;
    private Integer cantidadGuardarropas; // límite a la cantidad de guardarropa según el tipo de usuario

    public void agregarGuardarropa(Guardarropa guardarropa) throws Exception {
        guardarropa.definirUsuario(this);
        this.guardarropas.add(guardarropa);
    }

    public Set<Guardarropa> obtenerGuardarropas() {
        return this.guardarropas;
    }

    // falta codificar
    public void aceptarAtuendo(Atuendo atuendo){
        atuendo.aceptar();
    }
    public void rechazarAtuendo(Atuendo atuendo) {
        atuendo.rechazar();
    }
    public void calificarAtuendo(Atuendo atuendo, Integer calificacion) {
        atuendo.calificar(calificacion);
    }

    public void deshacer(){
        // falta implementar
    }


}
