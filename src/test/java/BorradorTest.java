package domain;


import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

public class BorradorTest {
    private Prenda prenda;
    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Color colorSecundarioIgualPrimario;
    private Color otroColorSecundario;
    private Borrador borradorZapatillas

    this.tipoDePrenda = tipoDePrenda.ZAPATO;
    this.colorPrimario = new Color(20,20,30);
    this.colorSecundarioIgualPrimario = new Color(20,20,30);
    this.otroColorSecundario = new Color(25,40,88);

    public void iniciar() {
        borradorZapatillas = new Borrador();
        borradorZapatillas.definirColorPrimario(colorPrimario);

    }
    @Test
    public void setearColorSecundarioIgualAlPrimario(){
        try {
            this.borradorZapatillas.definirColorSecundario(colorSecundarioIgualPrimario);
        }
        catch (ColorSecundarioException exception){
            System.out.print(exception.getMessage());
        }
    }

    @Test
    public void deberiaHaberTipoDePrendaAlCrearPrenda() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("el tipo de prenda es obligatorio");

        material = Material.ALGODON;
        color = new Color(100,100,100);
        prenda = new Prenda(tipoDePrenda, material, color);
    }

}