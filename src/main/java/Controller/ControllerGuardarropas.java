package Controller;

import domain.Guardarropa;
import domain.RepositorioGuardarropas;
import domain.Usuario;
import domain.usuario.Calendario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerGuardarropas {
    public ModelAndView prendas(Request req, Response res) {
        Guardarropa guardarropas =
                RepositorioGuardarropas.instance()
                        .findByUsuario(new Usuario("1534522454", new Calendario()));

        return new ModelAndView(guardarropas, "guardarropas.hbs");
    }

/*    //en la clase usuario

    private String passHash; //no se puede guardar una pass encriptada
    // no se puede tampoco guardarla encriptada
    // hay que guardar un hash

    public void validarContrasenia(string pass) {
        if(!this.passHash.equals(hash(pass)) {
            //tiro excepcion de constraseña invalida
        }
    }
*/
}
