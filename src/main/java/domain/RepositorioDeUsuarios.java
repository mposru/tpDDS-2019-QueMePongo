package domain;

import domain.clima.Alerta;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeUsuarios {
    private static RepositorioDeUsuarios instanceOfRepositorioDeUsuarios;
    List<Usuario> usuarios = new ArrayList<>();

    public static RepositorioDeUsuarios getInstance() {
        if(instanceOfRepositorioDeUsuarios==null) {
            instanceOfRepositorioDeUsuarios = new RepositorioDeUsuarios();
        }
        return instanceOfRepositorioDeUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void alertarATodos(List<Alerta> alertas) {
        //foreach usuario -> usuario.recibirAlertas(alertas)
        List<Usuario> usuariosConEvento = usuarios.stream().filter(usuario -> usuario.)
    }
}