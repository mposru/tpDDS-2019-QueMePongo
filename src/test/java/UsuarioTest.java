import domain.Guardarropa;
import domain.Usuario;
import org.junit.Before;

public class UsuarioTest {

    private Guardarropa guardarropa;
    private Usuario merlin;

    @Before
    public void iniciarTest() {
        this.merlin = new Usuario();
        this.guardarropa = new Guardarropa(merlin);
    }



}
