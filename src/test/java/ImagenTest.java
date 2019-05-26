import domain.*;
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
        //exception.expect(IOException.class);
        imagen.leerDeFileSystem("C:\\Users\\Marina\\Desktop\\tpDDS\\2019-vi-no-group-08\\src\\imagenes\\ojotas.png");
    }
}
