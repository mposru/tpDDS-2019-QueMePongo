import domain.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.io.IOException;

public class ImagenTest {

    private Imagen imagen;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void leerArchivoDeFileSystem() throws IOException {
        //exception.expect(IOException.class);
        imagen = imagen.leerDeFileSystem("/src/imagenes/ojotas.jpg/");
    }
}
