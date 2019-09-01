import domain.notificacion.SMS;
import domain.notificacion.Whatsapp;
import domain.usuario.Calendario;
import domain.usuario.tipoDeUsuario.Gratuito;
import domain.Usuario;
import org.junit.Before;
import org.junit.Test;

public class NotificadorTest {

    private Usuario merlin;
    private Whatsapp notificadorWhatsapp;
    private SMS notificadorSMS;
    private Calendario calendario;

    @Before
    public void iniciarTest() {
        this.merlin = new Usuario(Gratuito.getInstance(), "+5491134522303", calendario);
        this.notificadorWhatsapp = new Whatsapp();
        this.notificadorSMS = new SMS();
    }

    @Test
    public void enviarWhatsapp() {
        notificadorWhatsapp.notificar(merlin, "Sugerencias listas!");
    }

    @Test
    public void enviarSMS() {
        notificadorSMS.notificar(merlin, "Sugerencias listas!");
    }
}
