package domain;

import domain.guardarropa.Gratuito;
import domain.guardarropa.Premium;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;

import java.util.HashSet;
import java.util.Set;

public class RepositorioGuardarropas {

    private static final RepositorioGuardarropas INSTANCE = new RepositorioGuardarropas();

    public static RepositorioGuardarropas instance() {
        return INSTANCE;
    }

    public Guardarropa findByUsuario(Usuario usuario) {
        Set<Usuario> listaDeUsuarios = new HashSet<>();
        listaDeUsuarios.add(usuario);

        Guardarropa guardarropas = new Guardarropa("Blabla",listaDeUsuarios, new Premium());
        Prenda musculosa = new Prenda("Musculosa",TipoDePrenda.MUSCULOSA, Material.ALGODON, new Color(100,200,300), null, Trama.CUADROS, guardarropas,false);
        Prenda buzo = new Prenda("Buzo",TipoDePrenda.BUZO, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropas, false);
        Prenda shortDeJean = new Prenda("ShortDeJean",TipoDePrenda.SHORT, Material.JEAN, new Color(300,0,300), null, Trama.LISA, guardarropas,false);
        Prenda pañuelo = new Prenda("Pañuelo",TipoDePrenda.PANUELO, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropas, false);
        Prenda bandana = new Prenda("Bandana",TipoDePrenda.PANUELO, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropas, false);
        Prenda gorro = new Prenda("Gorro",TipoDePrenda.GORRO, Material.LANA, new Color(1,2,3), null, Trama.LISA, guardarropas, false);
        Prenda anteojos = new Prenda("Anteojos",TipoDePrenda.ANTEOJOS, Material.PLASTICO, new Color(1,2,3), null, Trama.LISA, guardarropas, false);
        Prenda zapatillas = new Prenda("nombre",TipoDePrenda.ZAPATILLAS, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropas, false);
        Prenda crocs = new Prenda("nombre",TipoDePrenda.CROCS, Material.GOMA, new Color(1,2,3), null, Trama.CUADROS, guardarropas, true);
        Prenda ojotas = new Prenda("nombre",TipoDePrenda.CROCS, Material.GOMA, new Color(1,2,3), null, Trama.CUADROS, guardarropas, true);
        
        guardarropas.guardarPrenda(musculosa);
        guardarropas.guardarPrenda(buzo);
        guardarropas.guardarPrenda(shortDeJean);
        guardarropas.guardarPrenda(pañuelo);
        guardarropas.guardarPrenda(bandana);
        guardarropas.guardarPrenda(gorro);
        guardarropas.guardarPrenda(anteojos);
        guardarropas.guardarPrenda(zapatillas);
        guardarropas.guardarPrenda(crocs);
        guardarropas.guardarPrenda(ojotas);

        return guardarropas;
    }

}
