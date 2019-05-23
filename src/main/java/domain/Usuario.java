package domain;

//import java.util.HashSet;
//import java.util.Set;
import java.util.*;

public class Usuario {
    private Set<Guardarropa> guardarropas = new HashSet<>();
    private Deque<Decision> decisiones = new LinkedList<>();


    public void agregarGuardarropa(Guardarropa guardarropa) throws Exception {
        guardarropa.definirUsuario(this);
        this.guardarropas.add(guardarropa);
    }

    public Set<Guardarropa> obtenerGuardarropas() {
        return this.guardarropas;
    }


    public void aceptarAtuendo(Atuendo atuendo){ }
    public void rechazarAtuendo(Atuendo atuendo) {}
    public void calificarAtuendo(Atuendo atuendo) {}


}
