package controller;

import domain.Guardarropa;
import domain.RepositorioDeUsuarios;
import domain.RepositorioGuardarropas;
import domain.Usuario;
import domain.usuario.Calendario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerPrendasGuardarropa {
    public ModelAndView prendas(Request req, Response res) {

        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorEmail(req.session().attribute("user"));
        String nombreGuardarropa = req.queryParams("guardarropas");

        return new ModelAndView(usuario.buscarGuardarropaPorNombre(nombreGuardarropa), "prendasGuardarropa.hbs");
    }
}
