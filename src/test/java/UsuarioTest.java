import domain.Guardarropa;
import domain.TipoDeUsuario.*;
import domain.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

    private Guardarropa guardarropaDeMerlin;
    private Guardarropa guardarropaDeMaria;
    private Usuario merlin;
    private Usuario maria;


    @Before
    public void iniciarTest() {
        this.merlin = new Usuario(Gratuito.getInstance());
        this.maria = new Usuario(Premium.getInstance());
        this.guardarropaDeMerlin = new Guardarropa(merlin);
        this.guardarropaDeMaria = new Guardarropa(maria);
    }

    @Test
    public void usuarioGratuitoTieneLimiteDePrendas(){
        Assert.assertTrue(merlin.tieneLimiteDePrendas());
    }
    @Test
    public void usuarioPremiumNoTieneLimiteDePrendas(){
        Assert.assertFalse(maria.tieneLimiteDePrendas());
    }


}
