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
        ControllerPrendasGuardarropa controllerPrendasGuardarropa = new ControllerPrendasGuardarropa();
        ControllerSesion controllerSesion = new ControllerSesion();
        ControllerCalendario controllerCalendario = new ControllerCalendario();
        ControllerEventos controllerEventos = new ControllerEventos();
        ControllerAltaDePrenda controllerAltaDePrenda = new ControllerAltaDePrenda();
        ControllerCalificarAceptadas controllerCalificarAceptadas = new ControllerCalificarAceptadas();
        ControllerPerfil controllerPerfil = new ControllerPerfil();

        TemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get("/guardarropas",controllerGuardarropas::guardarropas, engine);
        Spark.get("/prendas",controllerPrendasGuardarropa::prendas, engine);
        Spark.post("/calificarAceptadas",controllerCalificarAceptadas::calificarAceptadas,engine);
        Spark.get("/calificarAceptadas",controllerCalificarAceptadas::mostrarAceptadas,engine);
        Spark.get("/altaDePrenda",controllerAltaDePrenda::mostrarAltaDePrenda,engine);
        Spark.post("/altaDePrenda",controllerAltaDePrenda::seleccionAltaDePrenda,engine);
        Spark.post("/perfil",controllerPerfil::seleccion,engine);
        Spark.get("/login",controllerSesion::mostrarLogin, engine);
        Spark.post("/login",controllerSesion::crear, engine);
        /*Spark.post("/calendario/prev", controllerCalendario::irAlMesAnterior, engine);
        Spark.post("/calendario/next", controllerCalendario::irAlMesSiguiente, engine);*/
        Spark.get("/calendario", controllerCalendario::mostrarCalendarioConEventos, engine);
        Spark.post("/calendario", controllerCalendario::mostrarCalendarioConEventos, engine);
        Spark.get("/evento", controllerEventos::mostrar, engine);
        Spark.post("/evento", controllerEventos::crearEvento, engine);
        Spark.get("/eventos",controllerEventos ::mostrarEventos, engine);
        Spark.get("/eventos/:id/sugerencias/:indice",controllerEventos ::mostrarSugerencia, engine);
        Spark.post("/eventos/:id/sugerencias/:idSugerencia/estado",controllerEventos ::modificarEstadoSugerencia, engine);

        DebugScreen.enableDebugScreen();
    }

}
