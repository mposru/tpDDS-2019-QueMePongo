package controller;

import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControllerEvento {

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
