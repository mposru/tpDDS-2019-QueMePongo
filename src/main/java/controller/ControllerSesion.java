package controller;

import domain.Usuario;
import domain.usuario.Calendario;
import spark.*;

public class ControllerSesion {

    public ModelAndView mostrarLogin(Request req, Response res) {
        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView crear(Request req, Response res) {
        //Usuario usuario = RepoUsuario.buscarPorNombre(req.params("user"))
        //si no existe excepciom
        Usuario usuario = new Usuario("1534522454", new Calendario());
        //usuario.validarContraseña(req.queryParams("pass"));

        //NUNCA GUARDAR UN ID DE USUARIO EN UNA COOKIE EN TEXTO PLANOOOOOO
        //res.cookie("uid", usuario.getId()) // escribo aca
        // otra forma (java ya soluciona) req.session()...
        //redirigir al perfil
        res.redirect("/perfil");
        return null;
    }

}
