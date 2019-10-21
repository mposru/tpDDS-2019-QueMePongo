package controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerEvento {

    public ModelAndView mostrar(Request req, Response res) {
        return new ModelAndView(null, "altaEvento.hbs");
    }

    public ModelAndView crearEvento(Request req, Response res) {
        //Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.parseInt(req.params(":id")));
        String nombre = req.queryParams("nombre");
        String ubicacion = req.queryParams("ubicacion");
        String fecha = req.queryParams("fecha");
        res.redirect("/login");
        return null;
    }

}
