package domain;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private Set<Guardarropa> guardarropas = new HashSet<>();

    public void agregarGuardarropa(Guardarropa guardarropa) {
        this.guardarropas.add(guardarropa);
    }

    public Set<Guardarropa> obtenerGuardarropas() {
        return this.guardarropas;
    }
}
