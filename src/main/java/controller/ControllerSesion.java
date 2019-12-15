package controller;

import domain.RepositorioDeUsuarios;
import domain.SHA1;
import domain.Usuario;
import spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerSesion {

    private Usuario usuarioLogin;
    private String mensaje = "";


    public ModelAndView mostrarLogin(Request req, Response res) {

        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView iniciarSesion(Request req, Response res) {
        try {
            usuarioLogin = RepositorioDeUsuarios.getInstance().buscarUsuarioPorEmail(req.queryParams("user"));
            String contraseniaHasheada = SHA1.getInstance().convertirConHash(req.queryParams("pass"));
            usuarioLogin.validarContraseniaHash(contraseniaHasheada);
        }
        catch (Exception e) {
            Map<String, Object> model = new HashMap<>();
            model.put("error", e.getMessage());
            return new ModelAndView(model, "login.hbs");
        }
     //   System.out.println("Mensajes de error: "+mensaje);

      //  System.out.println(req.queryParams("user"));

        req.session().attribute("user",usuarioLogin.getEmail());
        req.session().attribute("uid", usuarioLogin.getId());
        req.session().attribute("nombre",usuarioLogin.getNombreUsuario());
        req.session().attribute("apellido",usuarioLogin.getApellido());

//        System.out.println(req.session().attribute("user").toString());
//        System.out.println(req.session().attribute("uid").toString());
//        System.out.println(req.session().attribute("nombre").toString());
//        System.out.println(req.session().attribute("apellido").toString());


        res.redirect("/perfil");
        return null;
    }

}
