package server;

import controller.ControllerGuardarropas;
import controller.ControllerPerfil;
import controller.ControllerSesion;
import spark.Spark;
import spark.TemplateEngine;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
    public static void main(String[] args) {
        //RepositorioGuardarropas.instance().findByUsuario(new Usuario());
        Spark.port(9000);
        Spark.staticFileLocation("/public");
        Spark.init();
        ControllerGuardarropas controllerGuardarropas = new ControllerGuardarropas();
        ControllerSesion controllerSesion = new ControllerSesion();
        ControllerPerfil controllerPerfil = new ControllerPerfil();

        TemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get("/guardarropa/prendas",controllerGuardarropas::prendas, engine);
        Spark.get("/login",controllerSesion::mostrarLogin, engine);
        Spark.post("/login",controllerPerfil::mostrar, engine);


        DebugScreen.enableDebugScreen();
    }

}
