package server;

import controller.*;
import spark.Spark;
import spark.TemplateEngine;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 9000; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        //RepositorioGuardarropas.instance().findByUsuario(new Usuario());
        //Spark.port(9000);
        Spark.port(getHerokuAssignedPort());
       // Spark.staticFileLocation("src/main/resources");
        Spark.staticFileLocation("/public");
        Spark.init();

        ControllerGuardarropas controllerGuardarropas = new ControllerGuardarropas();

        ControllerSesion controllerSesion = new ControllerSesion();
        ControllerCalendario controllerCalendario = new ControllerCalendario();
        ControllerEventos controllerEventos = new ControllerEventos();
        ControllerAltaDePrenda controllerAltaDePrenda = new ControllerAltaDePrenda();
        ControllerCalificarAceptadas controllerCalificarAceptadas = new ControllerCalificarAceptadas();
        ControllerPerfil controllerPerfil = new ControllerPerfil();
        ControllerSugerencias controllerSugerencias = new ControllerSugerencias();

        TemplateEngine engine = new HandlebarsTemplateEngine();
        Spark.get("/guardarropas",controllerGuardarropas::mostrarGuardarropas, engine);
        Spark.get("/guardarropas/:id/prendas",controllerGuardarropas::mostrarPrendas, engine);
        Spark.post("/calificarAceptadas",controllerCalificarAceptadas::calificarAceptadas,engine);
        Spark.get("/calificarAceptadas",controllerCalificarAceptadas::mostrarAceptadas,engine);
        Spark.get("/guardarropas/:id/prendas/new",controllerAltaDePrenda::mostrarAltaDePrenda,engine);
        Spark.post("/guardarropas/:id/prendas/new",controllerAltaDePrenda::seleccionAltaDePrenda,engine);
        Spark.get("/perfil",controllerPerfil::mostrar,engine);
        Spark.get("/login",controllerSesion ::mostrarLogin, engine);
        Spark.post("/login",controllerSesion::iniciarSesion, engine);
        /*Spark.post("/calendario/prev", controllerCalendario::irAlMesAnterior, engine);
        Spark.post("/calendario/next", controllerCalendario::irAlMesSiguiente, engine);*/
        Spark.get("/calendario/:anio/:mes", controllerCalendario::mostrarCalendarioConEventos, engine);
        //Spark.post("/calendario", controllerCalendario::mostrarCalendarioConEventos, engine);
        Spark.get("/eventos/new", controllerEventos::mostrar, engine);
        Spark.post("/eventos/new", controllerEventos::crearEvento, engine);
        Spark.get("/eventos",controllerEventos ::mostrarEventos, engine);
        Spark.get("/eventos/:id/sugerencias/:indice",controllerEventos ::mostrarSugerencia, engine);
        Spark.get("/sugerencias",controllerSugerencias::mostrar, engine);
        Spark.get("/sugerencias/:id/calificacion",controllerSugerencias::mostrarCalificacion, engine);
        Spark.post("/sugerencias/:id/calificacion",controllerSugerencias::calificar, engine);
        Spark.post("/eventos/:id/sugerencias/:idSugerencia/estado",controllerEventos ::modificarEstadoSugerencia, engine);

        DebugScreen.enableDebugScreen();
    }

}
