package domain;


import exceptions.NoExisteUsuarioException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    public RepositorioDeUsuarios() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        usuarios = manager.createQuery(
                "SELECT p FROM usuarios p", Usuario.class).getResultList();
        manager.getTransaction().commit();
        manager.close();
        emf.close();
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
            return null;
        }
        else {
            return usuariosEncontrados.get(0);
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        List<Usuario> usuariosEncontrados = usuarios.stream().filter(usuario -> usuario.getEmail().equals(email)).collect(Collectors.toList());
        if(usuariosEncontrados.isEmpty()) {
            throw new NoExisteUsuarioException("El usuario no existe en el repositorio");
        }
        else {
            return usuariosEncontrados.get(0);
        }
    }

    public  Usuario buscarPorIdentificador(String identificador) {
        Usuario usuario = usuariosTotal.stream()
                .filter(usuarioAux -> identificador.equals(usuarioAux.getNombreUsuario())||identificador.equals(usuarioAux.getEmail()))
                .findAny()
                .orElseThrow(()->new NoExisteUsuarioException("No existe usuario con el nombre indicado"));
        return usuario;
    }
}