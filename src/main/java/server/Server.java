package server;

import controller.*;
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
        ControllerCalendario controllerCalendario = new ControllerCalendario();
        ControllerEvento controllerEvento = new ControllerEvento();

        TemplateEngine engine = new HandlebarsTemplateEngine();

        Spark.get("/guardarropa/prendas",controllerGuardarropas::prendas, engine);
        Spark.get("/login",controllerSesion::mostrarLogin, engine);
        Spark.post("/login",controllerSesion::crear, engine);

        /*Spark.post("/calendario/prev", controllerCalendario::irAlMesAnterior, engine);
        Spark.post("/calendario/next", controllerCalendario::irAlMesSiguiente, engine);*/
        Spark.get("/calendario", controllerCalendario::mostrarCalendarioConEventos, engine);
        Spark.post("/calendario", controllerCalendario::mostrarCalendarioConEventos, engine);
        Spark.get("/evento", controllerEvento::mostrar, engine);
        Spark.post("/evento", controllerEvento::crearEvento, engine);

        DebugScreen.enableDebugScreen();
    }

}
