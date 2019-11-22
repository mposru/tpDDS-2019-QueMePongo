package controller;

import domain.RepositorioDeUsuarios;
import domain.SHA1;
import domain.Usuario;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import spark.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ControllerSesion {

    private EntityManager manager;
    private EntityManagerFactory emf;
    private Usuario usuarioLogin;



    public ModelAndView mostrarLogin(Request req, Response res) {

        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView crear(Request req, Response res) {

        this.emf = Persistence.createEntityManagerFactory("quemepongo");
        this.manager = emf.createEntityManager();
        Query query = manager.createQuery("select a from Usuario a"); //levantamos la lista de usuarios de la BBDD
        List<Usuario> usuarios = query.getResultList(); //Guardamos los usuarios en una lista
        usuarios.forEach(usuario->RepositorioDeUsuarios.getInstance().agregarUsuario(usuario)); //Agregamos todos los usuarios al repositorio

        usuarioLogin = RepositorioDeUsuarios.getInstance().buscarUsuarioPorEmail(req.queryParams("user"));


        String contraseniaHasheada = SHA1.getInstance().convertirConHash(req.queryParams("pass"));
        usuarioLogin.validarContraseniaHash(contraseniaHasheada);

        System.out.println(req.queryParams("user"));

        req.session().attribute("user",usuarioLogin.getEmail());
        req.session().attribute("uid", usuarioLogin.getId());
        req.session().attribute("nombre",usuarioLogin.getNombreUsuario());
        req.session().attribute("apellido",usuarioLogin.getApellido());

        System.out.println(req.session().attribute("user").toString());
    //    System.out.println(req.session().attribute("uid"));
        System.out.println(req.session().attribute("nombre").toString());
        System.out.println(req.session().attribute("apellido").toString());



        res.redirect("/perfil");
        return null;
    }

}
