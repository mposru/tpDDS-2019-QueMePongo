package controller;

import domain.Atuendo;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class ControllerCalificarAceptadas {
    public ModelAndView mostrarAceptadas(Request req, Response res) {
        Usuario usuario= RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.parseInt(req.cookie("uid").toString()));
        return new ModelAndView(usuario,"calificarAceptadas.hbs");
    }
    public ModelAndView calificarAceptadas(Request req, Response res) {
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.parseInt(req.cookie("uid")));
        String calificaion;
        for(Atuendo a:usuario.obtenerAtuendosAceptados())
        {
            calificaion=req.queryParams(String.valueOf(a.getId()));
            a.calificar(Integer.parseInt(calificaion));
        }
        res.redirect("/perfil");
        return null;
    }

}
