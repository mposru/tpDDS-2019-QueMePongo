package server;

import controller.ControllerEventos;
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
        ControllerEventos controllerEventos = new ControllerEventos();

        TemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get("/guardarropa/prendas",controllerGuardarropas::prendas, engine);
        Spark.get("/login",controllerSesion::mostrarLogin, engine);
        Spark.post("/login",controllerPerfil::mostrar, engine);
        Spark.get("/eventos",controllerEventos ::mostrarEventos, engine);
        Spark.get("/eventos/:id/sugerencias/:indice",controllerEventos ::mostrarSugerencia, engine);
        Spark.post("/eventos/:id/sugerencias/:idSugerencia/estado",controllerEventos ::modificarEstadoSugerencia, engine);

        DebugScreen.enableDebugScreen();
    }

}
