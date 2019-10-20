package controller;

import domain.*;
import domain.guardarropa.Gratuito;
import domain.guardarropa.Premium;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ControllerGuardarropas {
    public ModelAndView guardarropas(Request req, Response res) {
//        Usuario usuarioPrueba = this.crear();
        List<Guardarropa> guardarropas = new ArrayList<>();
        Guardarropa guardarropa1 = new Guardarropa("Guardarropa1",new HashSet<>(),new Premium());
        Guardarropa guardarropa2 = new Guardarropa("Guardarropa2",new HashSet<>(),new Gratuito(2));
        guardarropas.add(guardarropa1);
        guardarropas.add(guardarropa2);

        return new ModelAndView(guardarropas, "guardarropasII.hbs");
    }


//    public Usuario crear() {
//        Calendario calendarioMerlin = new Calendario();
//        Usuario merlin = new Usuario( "1543333322", calendarioMerlin);
//
//        Set<Usuario> merlinLista = new HashSet<>();
//        merlinLista.add(merlin);
//
//        Guardarropa guardarropaDeMerlin = new Guardarropa("Guardarropa1",merlinLista,new Premium());
//
//        Prenda pantalon = new Prenda(TipoDePrenda.PANTALON, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropaDeMerlin, true);
//        Prenda zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, new Color(1,2,3), null, Trama.LISA, guardarropaDeMerlin, true);
//        Prenda shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, new Color(1,2,3), null, Trama.LISA, guardarropaDeMerlin, false);
//        Prenda pollera = new Prenda(TipoDePrenda.POLLERA, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropaDeMerlin, false);
//        Prenda pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropaDeMerlin, false);
//
//        guardarropaDeMerlin.guardarPrenda(pantalon);
//        guardarropaDeMerlin.guardarPrenda(zapatos);
//        guardarropaDeMerlin.guardarPrenda(shortDeJean);
//        guardarropaDeMerlin.guardarPrenda(pollera);
//        guardarropaDeMerlin.guardarPrenda(pañuelo);
//
//        merlin.agregarGuardarropa(guardarropaDeMerlin);
//
//        return merlin;
//    }

}
