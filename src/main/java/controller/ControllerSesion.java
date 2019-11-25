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
    private boolean flagError= false;
    private String mensaje = "";


    public ModelAndView mostrarLogin(Request req, Response res) {

        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView iniciarSesion(Request req, Response res) {

        this.emf = Persistence.createEntityManagerFactory("quemepongo");
        this.manager = emf.createEntityManager();
        Query query = manager.createQuery("select a from Usuario a"); //levantamos la lista de usuarios de la BBDD
        List<Usuario> usuarios = query.getResultList(); //Guardamos los usuarios en una lista
        System.out.println("cantidad de usuarios en la base: "+usuarios.size());
        usuarios.forEach(usuario->RepositorioDeUsuarios.getInstance().agregarUsuario(usuario)); //Agregamos todos los usuarios al repositorio

        usuarios.forEach(usuario->System.out.println("email usuario: "+ usuario.getEmail()));
        System.out.println("Repositorio de usuarios "+RepositorioDeUsuarios.getInstance().getUsuarios());
        try {
            usuarioLogin = RepositorioDeUsuarios.getInstance().buscarUsuarioPorEmail(req.queryParams("user"));
        }
        catch (Exception e)
        {
            mensaje += " "+e.getMessage();
            flagError = true;
        }

        String contraseniaHasheada = SHA1.getInstance().convertirConHash(req.queryParams("pass"));
        try {
            usuarioLogin.validarContraseniaHash(contraseniaHasheada);
        }
        catch (Exception e) {
            flagError = true;
            mensaje += " "+  e.getMessage();
        }
     //   System.out.println("Mensajes de error: "+mensaje);

      //  System.out.println(req.queryParams("user"));

        req.session().attribute("user",usuarioLogin.getEmail());
        req.session().attribute("uid", usuarioLogin.getId());
        req.session().attribute("nombre",usuarioLogin.getNombreUsuario());
        req.session().attribute("apellido",usuarioLogin.getApellido());

//        System.out.println(req.session().attribute("user").toString());
//        System.out.println(req.session().attribute("uid").toString());
//        System.out.println(req.session().attribute("nombre").toString());
//        System.out.println(req.session().attribute("apellido").toString());


        if (!flagError) {
            res.redirect("/perfil");
        }
        return null;
    }

}
