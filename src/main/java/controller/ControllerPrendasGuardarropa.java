package controller;

import domain.Guardarropa;
import domain.RepositorioGuardarropas;
import domain.Usuario;
import domain.usuario.Calendario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerPrendasGuardarropa {
    public ModelAndView prendas(Request req, Response res) {
        Guardarropa guardarropas =
                RepositorioGuardarropas.instance()
                        .findByUsuario(new Usuario("",new Calendario()));

        return new ModelAndView(guardarropas, "prendasGuardarropa.hbs");
    }
}
