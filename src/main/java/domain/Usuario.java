package domain;

import java.util.Set;

public class Usuario {
    private String nombre, apellido, mail;
    private Set<Guardarropa> guardarropas;

    //es necesario el metodo de abajo si el usuario es parte del constructor del guardarropa?
    public void agregarGuardarropa(Guardarropa guardarropa) {
        this.guardarropas.add(guardarropa);
    }
    public Usuario(String nombre,String apellido,String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
    }
    public String nombre() {
        return this.nombre;
    }
    public String apellido() {
        return this.apellido;
    }
    public String mail(){
        return this.mail;
    }
}
