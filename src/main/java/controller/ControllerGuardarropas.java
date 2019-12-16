package controller;

import domain.*;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class ControllerGuardarropas {


    public ModelAndView mostrarGuardarropas(Request req, Response res) {
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorEmail(req.session().attribute("user"));
        return new ModelAndView(usuario.getGuardarropas(), "guardarropas.hbs");
    }

    public ModelAndView mostrarPrendas(Request req, Response res) {
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorEmail(req.session().attribute("user"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager em = emf.createEntityManager();
        Guardarropa guardarropa = em.find(Guardarropa.class,Long.valueOf(req.params("id")));

        return new ModelAndView(guardarropa, "prendas.hbs");
    }

}
