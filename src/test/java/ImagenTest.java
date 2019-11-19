import domain.prenda.Imagen;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.io.IOException;

public class ImagenTest {

    private Imagen imagen = new Imagen();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void seGuardaConAltoIndicado() throws IOException {
        imagen.leerDeFileSystem("src/imagenes/ojotas.png");
      //  System.out.println(imagen.getArchivo());
        Assert.assertEquals(imagen.getAlto(), imagen.getImagen().getHeight());
    }

    @Test
    public void seGuardaConAnchoIndicado() throws IOException {
        imagen.leerDeFileSystem("src/imagenes/ojotas.png");
        Assert.assertEquals(imagen.getAncho(), imagen.getImagen().getWidth());
    }
}
