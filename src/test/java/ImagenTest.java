import domain.*;
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
    public void leerArchivoDeFileSystem() throws IOException {
        imagen.leerDeFileSystem("src/imagenes/ojotas.png");
        //Assert.assertEquals();
    }
}
