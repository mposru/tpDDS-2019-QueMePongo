package domain;

import domain.guardarropa.Gratuito;
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

        Guardarropa guardarropas = new Guardarropa("Blabla",listaDeUsuarios, new Gratuito(5));
        Prenda musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, new Color(100,200,300), null, Trama.CUADROS, guardarropas,false);
        Prenda shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, new Color(300,0,300), null, Trama.LISA, guardarropas,false);

        guardarropas.obtenerPrendasSuperiores().add(musculosa);

        guardarropas.guardarPrenda(musculosa);
        guardarropas.guardarPrenda(shortDeJean);


        return guardarropas;
    }

}
