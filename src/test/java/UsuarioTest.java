import domain.*;
import domain.estadoAtuendo.*;
import domain.usuario.tipoDeUsuario.*;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import exceptions.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class UsuarioTest {

    private Guardarropa guardarropaDeMerlin;
    private Guardarropa guardarropaDeMaria;
    private Usuario merlin;
    private Usuario maria;
    private Prenda accesorio;
    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private EstadoAtuendo estado;
    private Atuendo atuendoVerano;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda crocs;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda pollera;
    private Prenda pañuelo;
    private Prenda anteojos;
    private Color color;

    @Before
    public void iniciarTest() {
        this.merlin = new Usuario(Gratuito.getInstance(), "1543333322");
        this.maria = new Usuario(Premium.getInstance(), "1543333322");
        Set<Usuario> merlinLista = new HashSet<>();
        merlinLista.add(merlin);
        Set<Usuario> mariaLista = new HashSet<>();
        mariaLista.add(maria);
        this.guardarropaDeMerlin = new Guardarropa(merlinLista);
        this.guardarropaDeMaria = new Guardarropa(mariaLista);
        this.color = new Color(1, 2, 3);
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaDeMerlin,21,30,false);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaDeMerlin,21,30,false);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropaDeMerlin,21,30,false);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropaDeMerlin,21,30,false);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropaDeMerlin,21,30,false);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropaDeMerlin,21,30,false);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMerlin,21,30,false);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropaDeMerlin,21,30,false);
        Set<Prenda> superiores = new HashSet<>();
        superiores.add(musculosa);
        this.atuendoVerano = new Atuendo(superiores,shortDeJean,crocs,anteojos);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void usuarioGratuitoTieneLimiteDePrendas(){
        Assert.assertTrue(merlin.tieneLimiteDePrendas());
    }
    @Test
    public void usuarioPremiumNoTieneLimiteDePrendas(){
        Assert.assertFalse(maria.tieneLimiteDePrendas());
    }
    @Test
    public void usuarioAceptaAtuendo(){
        merlin.aceptarAtuendo(atuendoVerano);
        Assert.assertTrue(merlin.obtenerAtuendosAceptados().contains(atuendoVerano));
    }
    @Test
    public void usuarioRechazaAtuendo(){
        merlin.rechazarAtuendo(atuendoVerano);
        Assert.assertTrue(merlin.obtenerAtuendosRechazados().contains(atuendoVerano));
    }
    @Test
    public void usuarioDeshaceUltimaDecisionVacia(){
        exception.expect(PilaVaciaException.class);
        exception.expectMessage("No hay decisiones por deshacer");
        merlin.deshacer();

    }
    @Test
    public void usuarioDeshaceUltimaDecision(){
        merlin.aceptarAtuendo(atuendoVerano);
        merlin.calificarAtuendo(atuendoVerano,5);
        merlin.deshacer();
        Assert.assertEquals("domain.estadoAtuendo.Aceptado", this.atuendoVerano.obtenerEstadoAtuendo().getClass().getName());
    }
    @Test
    public void cantidadDeOperaciones(){
        merlin.aceptarAtuendo(atuendoVerano);
        merlin.calificarAtuendo(atuendoVerano,5);
        merlin.calificarAtuendo(atuendoVerano,4);
        merlin.calificarAtuendo(atuendoVerano,3);
        Assert.assertEquals(4, merlin.obtenerDecisiones().size());
    }
    @Test
    public void usuarioDeshaceUltimaDecisionAceptada(){
        merlin.aceptarAtuendo(atuendoVerano);
        merlin.deshacer();
        Assert.assertEquals(0, merlin.obtenerAtuendosAceptados().size());
    }

    @Test
    public void usuarioDeshaceUltimaDecisionRechazada(){
        merlin.rechazarAtuendo(atuendoVerano);
        merlin.deshacer();
        Assert.assertEquals(0, merlin.obtenerAtuendosRechazados().size());
    }
}
