package controller;

import domain.*;
import domain.guardarropa.Gratuito;
import domain.guardarropa.Premium;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import domain.usuario.Calendario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControllerGuardarropas {
    public ModelAndView guardarropas(Request req, Response res) {
        //Usuario usuarioPrueba = this.crear();
        //res.cookie("uid","0");
        String usuarioId = req.cookie("uid");
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.parseInt(usuarioId));
        return new ModelAndView(usuario.getGuardarropas(), "guardarropasII.hbs");
    }


    public Usuario crear() {

        Usuario usuario = new Usuario( "1543333322", new Calendario(),"abc123","email","nombre");

        Set<Usuario> usuariosLista = new HashSet<>();
        usuariosLista.add(usuario);

        Guardarropa guardarropa1 = new Guardarropa("GuardarropaInvierno",new HashSet<>(),new Premium());
        Guardarropa guardarropa2 = new Guardarropa("GuardarropaVerano",new HashSet<>(),new Gratuito(10));


        Prenda zapatos = new Prenda("Zapatos",TipoDePrenda.ZAPATO, Material.CUERO, new Color(1,2,3), null, Trama.LISA, guardarropa1, true);
        Prenda botasDeNieve = new Prenda("BotasDeNieve",TipoDePrenda.BOTAS_NIEVE, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropa1, false);
        Prenda buzo = new Prenda("Buzo",TipoDePrenda.BUZO, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropa1, false);
        Prenda pantalonPolar = new Prenda("Pantalon Polar",TipoDePrenda.PANTALON, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropa1, false);
        Prenda gorro = new Prenda("Gorro lana",TipoDePrenda.GORRO, Material.LANA, new Color(1,2,3), null, Trama.LISA, guardarropa1, false);


        Prenda musculosa = new Prenda("Musculosa",TipoDePrenda.MUSCULOSA, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropa2,  false);
        Prenda shortDeJean = new Prenda("ShortDeJean", TipoDePrenda.SHORT, Material.JEAN, new Color(1,2,3), null, Trama.LISA, guardarropa2, false);
        Prenda crocs = new Prenda("Crocs",TipoDePrenda.CROCS, Material.GOMA, new Color(1,2,3), null, Trama.CUADROS, guardarropa2, true);
        Prenda pollera = new Prenda("Pollera", TipoDePrenda.POLLERA, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropa2, false);
        Prenda pañuelo = new Prenda("Pañuelo",TipoDePrenda.PANUELO, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropa2, false);

        guardarropa1.guardarPrenda(zapatos);
        guardarropa1.guardarPrenda(botasDeNieve);
        guardarropa1.guardarPrenda(buzo);
        guardarropa1.guardarPrenda(pantalonPolar);
        guardarropa1.guardarPrenda(gorro);

        guardarropa2.guardarPrenda(shortDeJean);
        guardarropa2.guardarPrenda(musculosa);
        guardarropa2.guardarPrenda(crocs);
        guardarropa2.guardarPrenda(pollera);
        guardarropa2.guardarPrenda(pañuelo);

        usuario.agregarGuardarropa(guardarropa2);
        usuario.agregarGuardarropa(guardarropa1);

        RepositorioDeUsuarios.getInstance().agregarUsuario(usuario);
        return usuario;
    }

}
