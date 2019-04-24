package domain;

import java.util.Set;

public class Usuario {
    private String nombre, apellido, mail;
    private Set<Guardarropa> guardarropas;

    public void agregarGuardarropa(Guardarropa guardarropa) {
        this.guardarropas.add(guardarropa);
    }
}
