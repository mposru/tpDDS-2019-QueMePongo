package domain;

import java.util.Set;

public class Usuario {
    private Set<Guardarropa> guardarropas;

    public void agregarGuardarropa(Guardarropa guardarropa) {
        this.guardarropas.add(guardarropa);
    }

    public Set<Guardarropa> obtenerGuardarropas() {
        return this.guardarropas;
    }
}
