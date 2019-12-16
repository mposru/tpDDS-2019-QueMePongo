package controller;

import domain.Atuendo;
import domain.RepositorioAtuendos;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerSugerencias {
    public ModelAndView mostrar(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorEmail(req.session().attribute("user"));
        // todo: cuando este persistido dejar lo de abajo, por ahora dejar
        //model.put("sugerencias", usuario.obtenerSugerenciasCalificadas());

        // temporal
        List<Atuendo> temporal = new ArrayList<>();
        temporal.add(RepositorioAtuendos.getInstance().obtenerPorId(Long.valueOf(715)));
        // temporal

        model.put("sugerencias", temporal);

        return new ModelAndView(model, "sugerenciasCalificadas.hbs");
    }
    public ModelAndView mostrarCalificacion(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        // todo: esto anda solo en el local de daiu, cuando se suba a produccion ya deberia estar implementada la persistencia y esto no haria falta
        // todo: reemplazar numero hardcodeado por: req.params("id")
        Atuendo atuendo = RepositorioAtuendos.getInstance().obtenerPorId(Long.valueOf(715));
        model.put("sugerencia", atuendo);
        model.put("sugerenciaId", atuendo.getId());
        return new ModelAndView(model, "calificarSugerencia.hbs");
    }
    public ModelAndView calificar(Request req, Response res) {
        // redirect a /sugerencias
        // todo: esto anda solo en el local de daiu, cuando se suba a produccion ya deberia estar implementada la persistencia y esto no haria falta
        // todo: reemplazar numero hardcodeado por: req.params("id")
        Atuendo atuendo = RepositorioAtuendos.getInstance().obtenerPorId(Long.valueOf(715));
        atuendo.aceptar();
        atuendo.calificar(Integer.valueOf(req.queryParams("puntuacion")));
        RepositorioAtuendos.getInstance().actualizarAtuendo(atuendo);
        res.redirect("/sugerencias");
        return null;
    }
}
