import domain.*;
import domain.EstadoAtuendo.*;
import domain.TipoDeUsuario.*;
import exceptions.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.Test;

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
        this.merlin = new Usuario(Gratuito.getInstance());
        this.maria = new Usuario(Premium.getInstance());
        this.guardarropaDeMerlin = new Guardarropa(merlin);
        this.guardarropaDeMaria = new Guardarropa(maria);
        this.color = new Color(1, 2, 3);
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaDeMerlin);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaDeMerlin);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropaDeMerlin);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropaDeMerlin);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropaDeMerlin);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropaDeMerlin);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMerlin);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropaDeMerlin);
        this.atuendoVerano = new Atuendo(musculosa,shortDeJean,crocs,anteojos);
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
    public void usuarioDeshaceUltimaDesicionVacia(){
        exception.expect(PilaVaciaException.class);
        exception.expectMessage("No hay decisiones por deshacer");
        merlin.deshacer();

    }
    @Test
    public void usuarioDeshaceUltimaDesicion(){
        merlin.aceptarAtuendo(atuendoVerano);
        merlin.calificarAtuendo(atuendoVerano,5);
        merlin.deshacer();
        Assert.assertEquals("domain.EstadoAtuendo.Aceptado", this.atuendoVerano.obtenerEstadoAtuendo().getClass().getName());


    }
    @Test
    public void cantidadDeOperaciones(){
        merlin.aceptarAtuendo(atuendoVerano);
        merlin.calificarAtuendo(atuendoVerano,5);
        merlin.calificarAtuendo(atuendoVerano,4);
        merlin.calificarAtuendo(atuendoVerano,3);
        Assert.assertEquals(4, merlin.obtenerDecisiones().size());
    }



}
