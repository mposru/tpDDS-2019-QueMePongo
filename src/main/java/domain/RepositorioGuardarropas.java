package domain;

import domain.guardarropa.Gratuito;
import domain.guardarropa.Premium;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import domain.usuario.Calendario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositorioGuardarropas {

    private static final RepositorioGuardarropas INSTANCE = new RepositorioGuardarropas();

    public static RepositorioGuardarropas instance() {
        return INSTANCE;
    }

    List<Guardarropa> guardarropas = new ArrayList<>();

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

    /*
    public Guardarropa buscarGuardarropaPorId(int id) {
        Usuario usuario = new Usuario("",new Calendario()); //Auxiliar
        Set<Usuario> listaDeUsuarios = new HashSet<>();
        listaDeUsuarios.add(usuario);

        Guardarropa guardarropa1 = new Guardarropa("Guardarropa1",new HashSet<>(),new Premium());
        Guardarropa guardarropa2 = new Guardarropa("Guardarropa2",new HashSet<>(),new Gratuito(5));
        guardarropas.add(guardarropa1);
        guardarropas.add(guardarropa2);

        Prenda musculosa = new Prenda("Musculosa", TipoDePrenda.MUSCULOSA, Material.ALGODON, new Color(100,200,300), null, Trama.CUADROS, guardarropa1,false);
        Prenda buzo = new Prenda("Buzo",TipoDePrenda.BUZO, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropa1, false);
        Prenda shortDeJean = new Prenda("ShortDeJean",TipoDePrenda.SHORT, Material.JEAN, new Color(300,0,300), null, Trama.LISA, guardarropa1,false);
        Prenda pañuelo = new Prenda("Pañuelo",TipoDePrenda.PANUELO, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropa1, false);
        Prenda bandana = new Prenda("Bandana",TipoDePrenda.PANUELO, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropa1, false);
        Prenda gorro = new Prenda("Gorro",TipoDePrenda.GORRO, Material.LANA, new Color(1,2,3), null, Trama.LISA, guardarropa1, false);
        Prenda anteojos = new Prenda("Anteojos",TipoDePrenda.ANTEOJOS, Material.PLASTICO, new Color(1,2,3), null, Trama.LISA, guardarropa1, false);

        Prenda zapatillas = new Prenda("Zapatillas",TipoDePrenda.ZAPATILLAS, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropa2, false);
        Prenda crocs = new Prenda("Crocs",TipoDePrenda.CROCS, Material.GOMA, new Color(1,2,3), null, Trama.CUADROS, guardarropa2, true);
        Prenda ojotas = new Prenda("Ojotas",TipoDePrenda.CROCS, Material.GOMA, new Color(1,2,3), null, Trama.CUADROS, guardarropa2, true);

        guardarropa1.guardarPrenda(musculosa);
        guardarropa1.guardarPrenda(buzo);
        guardarropa1.guardarPrenda(shortDeJean);
        guardarropa1.guardarPrenda(pañuelo);
        guardarropa1.guardarPrenda(bandana);
        guardarropa1.guardarPrenda(gorro);
        guardarropa1.guardarPrenda(anteojos);
        guardarropa2.guardarPrenda(zapatillas);
        guardarropa2.guardarPrenda(crocs);
        guardarropa2.guardarPrenda(ojotas);

        List<Guardarropa> guardarropasEncontrados = guardarropas.stream()
                .filter(guardarropa -> guardarropa.getId() == id)
                .collect(Collectors.toList());
        if(guardarropasEncontrados.isEmpty()) {
            return null;
        }
        else {
            return guardarropasEncontrados.get(0);
        }
    }*/
}
