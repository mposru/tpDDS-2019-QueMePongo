import domain.Guardarropa;
import domain.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

    private Guardarropa guardarropa;
    private Usuario merlin;

    @Before
    public void iniciarTest() {
        this.merlin = new Usuario();
        this.guardarropa = new Guardarropa(merlin);
    }



}
