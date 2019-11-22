package controller;

import java.util.HashMap;
import java.util.Map;


import spark.*;

public class ControllerPerfil {
    public ModelAndView mostrar(Request req, Response res){

        Map<String, String> model = new HashMap<>();
          model.put("nombre", req.session().attribute("nombre"));
          model.put("apellido", req.session().attribute("apellido"));

        return new ModelAndView(model, "perfil.hbs"); //Info del usuario logeado
    }

}
