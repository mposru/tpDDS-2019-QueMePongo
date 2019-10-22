package controller;

import domain.RepositorioDeUsuarios;
import domain.Usuario;

import java.util.HashMap;
import java.util.Map;
import spark.*;

public class ControllerPerfil {
    public ModelAndView mostrar(Request req, Response res){
        Map<String, String> model = new HashMap<>();
        String id = req.cookie("uid");
        model.put("id", id);
        return new ModelAndView(model, "perfil.hbs"); //Info del usuario logeado
    }
}
