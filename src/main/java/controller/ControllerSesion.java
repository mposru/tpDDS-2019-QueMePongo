package controller;

import domain.RepositorioDeUsuarios;
import domain.SHA1;
import domain.Usuario;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import spark.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ControllerSesion {

    public ModelAndView mostrarLogin(Request req, Response res) {
        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView crear(Request req, Response res) {
        //Usuario usuario = RepoUsuario.buscarPorNombre(req.params("user"))
        //si no existe excepciom
        //Usuario usuario = new Usuario("1534522454", new Calendario());
        //usuario.validarContraseña(req.queryParams("pass"));

        Usuario usuario = new Usuario("",new Calendario() ,"foo", "foo", "foo@foo","apellido");
        //RepositorioDeUsuarios.getInstance().agregarUsuario(usuarie);
        //Usuario usuario = RepositorioDeUsuarios.getInstance().buscarPorIdentificador(req.queryParams("user"));
        String contraseñaHash= SHA1.getInstance().convertirConHash(req.queryParams("pass"));
        usuario.validarContraseniaHash(contraseñaHash);
        //NUNCA GUARDAR UN ID DE USUARIO EN UNA COOKIE EN TEXTO PLANOOOOOO
        //res.cookie("uid", usuario.getId()) // escribo aca
        // otra forma (java ya soluciona) req.session()...
        //redirigir al perfil
        res.redirect("/perfil");
        return null;
    }

}
