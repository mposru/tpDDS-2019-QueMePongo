package controller;

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
    public ModelAndView seleccion(Request req, Response res) {
        res.redirect("/altaDePrenda");
        return null;
    }
}
