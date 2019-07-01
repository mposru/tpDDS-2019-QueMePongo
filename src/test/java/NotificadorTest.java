import domain.Notificacion.SMS;
import domain.Notificacion.Whatsapp;
import domain.TipoDeUsuario.Gratuito;
import domain.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class NotificadorTest {

    private Usuario merlin;
    private Whatsapp notificadorWhatsapp;
    private SMS notificadorSMS;

    @Before
    public void iniciarTest() {
        this.merlin = new Usuario(Gratuito.getInstance(), "+5491134522303");
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
