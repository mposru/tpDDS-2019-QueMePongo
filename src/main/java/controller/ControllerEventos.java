package controller;

import domain.*;
import domain.usuario.Evento;
import spark.*;

import java.util.*;

public class ControllerEventos {

    public ModelAndView mostrarSugerencia(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        Evento evento = RepositorioEventos.getInstance().findById(req.params(":id"));

        String id = req.cookie("uid");
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.valueOf(id));

        int indice = Integer.valueOf(req.params("indice"));

        List<Atuendo> sugerencias = usuario.obtenerSugerenciasDeEvento(evento);

        model.put("sugerencia", sugerencias.get(indice));
        model.put("nombreEvento", evento.getNombre());
        model.put("eventoId", req.params(":id"));
        model.put("noHayMasSugerencias", sugerencias.size() == (indice + 1));
        model.put("indice", indice);
        return new ModelAndView(model, "sugerencias.hbs");
    }

    public ModelAndView mostrarEventos(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        String id = req.cookie("uid");
        List<Evento> eventos = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.valueOf(id)).obtenerEventos();

        //todo: temporal
        eventos = new LinkedList<>();
        eventos.add(RepositorioEventos.getInstance().findById("2"));
        //
        model.put("redirectSugerencias", Boolean.valueOf(req.queryParams("redirectSugerencias")));
        model.put("eventos", eventos);
        return new ModelAndView(model, "eventos.hbs");
    }

    public ModelAndView modificarEstadoSugerencia(Request req, Response res) {
        // obtener con el id el atuendo y hacer aceptar o rechazar
        Evento evento = RepositorioEventos.getInstance().findById(req.params(":id"));

        String id = req.cookie("uid");
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.valueOf(id));

        int indice = Integer.valueOf(req.queryParams("indice"));

        List<Atuendo> sugerencias = usuario.obtenerSugerenciasDeEvento(evento);
        Atuendo atuendo = RepositorioAtuendos.getInstance().findById(req.params(":idSugerencia"));
        if ((indice + 1) == sugerencias.size()) {
            usuario.aceptarAtuendo(atuendo);
            // obtener sugerencia por id y aceptarla
            res.redirect("/eventos?redirectSugerencias=true");

        } else {
            String estadoElegido = req.params("estado");
            if(estadoElegido.equals("Aceptar")) {
                usuario.aceptarAtuendo(atuendo);
                res.redirect("/eventos?redirectSugerencias=true");
            } else if (estadoElegido.equals("Rechazar")) {
                usuario.rechazarAtuendo(atuendo);
                res.redirect("/eventos/"+req.params("id")+"/sugerencias/"+ String.valueOf(indice + 1));
            }
        }
        return null;
    }
}
