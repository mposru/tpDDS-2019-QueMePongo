package domain;

import domain.clima.Alerta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RepositorioDeUsuarios {
    private static RepositorioDeUsuarios instanceOfRepositorioDeUsuarios;
    List<Usuario> usuarios = new ArrayList<>();
    List<Usuario> usuariosTotal = new ArrayList<>();

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

    public void agregarUsuarioTotal(Usuario usuario) { usuariosTotal.add(usuario); }

    public Usuario buscarUsuarioPorId(int id) {
        List<Usuario> usuariosEncontrados = usuarios.stream().filter(usuario -> usuario.getId() == id).collect(Collectors.toList());
        if(usuariosEncontrados.isEmpty()) {
            throw new RuntimeException("No se encontró ningun usuario con ese Id");
        }
        else {
            return usuariosEncontrados.get(0);
        }
    }
}