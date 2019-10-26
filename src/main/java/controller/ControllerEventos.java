package controller;

import domain.*;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import spark.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControllerEventos {

    public ModelAndView mostrarSugerencia(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager em = emf.createEntityManager();
        Evento evento = em.find(Evento.class,Long.valueOf(req.params("id")));

        Usuario usuario = em.find(Usuario.class,Long.valueOf(req.cookie("uid")));

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class,Long.valueOf(req.cookie("uid")));
        model.put("redirectSugerencias", Boolean.valueOf(req.queryParams("redirectSugerencias")));
        model.put("eventos", usuario.obtenerEventos());

        return new ModelAndView(model, "eventos.hbs");
    }

    public ModelAndView modificarEstadoSugerencia(Request req, Response res) {
        // obtener con el id el atuendo y hacer aceptar o rechazar
        Evento evento = RepositorioEventos.getInstance().findById(req.params(":id"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager em = emf.createEntityManager();

        Usuario usuario = em.find(Usuario.class,Long.valueOf(req.cookie("uid")));

        int indice = Integer.valueOf(req.queryParams("indice"));

        List<Atuendo> sugerencias = usuario.obtenerSugerenciasDeEvento(evento);

        Atuendo atuendo = em.find(Atuendo.class,Long.valueOf(req.params(":idSugerencia")));

        if ((indice + 1) == sugerencias.size()) {
            usuario.aceptarAtuendo(atuendo);
            this.actualizarUsuarioYAtuendo(usuario, atuendo);

            res.redirect("/eventos?redirectSugerencias=true");

        } else {
            String estadoElegido = req.params("estado");
            if(estadoElegido.equals("Aceptar")) {
                usuario.aceptarAtuendo(atuendo);
                this.actualizarUsuarioYAtuendo(usuario, atuendo);

                res.redirect("/eventos?redirectSugerencias=true");
            } else if (estadoElegido.equals("Rechazar")) {
                usuario.rechazarAtuendo(atuendo);
                this.actualizarUsuarioYAtuendo(usuario, atuendo);

                res.redirect("/eventos/"+req.params("id")+"/sugerencias/"+ String.valueOf(indice + 1));
            }
        }
        return null;
    }

    private void actualizarUsuarioYAtuendo(Usuario usuario, Atuendo atuendo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager manager = emf.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.merge(usuario);
            manager.merge(atuendo);
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            manager.close();
        }
    }

    public ModelAndView mostrar(Request req, Response res) {
        return new ModelAndView(null, "altaEvento.hbs");
    }

    public ModelAndView crearEvento(Request req, Response res) {
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.parseInt(req.cookie("uid")));
        String nombre = req.queryParams("descripcion");
        String ubicacion = req.queryParams("ubicacion");
        String fechaString = req.queryParams("fecha");

        if(nombre.equals("") || ubicacion.equals("") || fechaString.equals("")) {
            res.redirect("/evento");
            return null;
        } else {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
            LocalDateTime fecha = LocalDateTime.parse(fechaString, formato);

            usuario.getCalendario().agregarEvento(new Evento(nombre, ubicacion, fecha, Periodo.NINGUNO, 0));
            res.redirect("/calendario");
            return null;
        }
    }
}
