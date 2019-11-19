import domain.notificacion.SMS;
import domain.notificacion.Whatsapp;
import domain.usuario.Calendario;
import domain.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;

public class NotificadorTest {

    private Usuario merlin;
    private Whatsapp notificadorWhatsapp;
    private SMS notificadorSMS;
    private Calendario calendario;

    @Before
    public void iniciarTest() {
        merlin = new Usuario( "+5491134522303", calendario, "merlin123","","",null);
    }

    @Test
    public void enviarMensajePorWhatsapp() {
        notificadorWhatsapp = Mockito.mock(Whatsapp.class);
        Mockito.doAnswer(invocation -> {
            Object args[] = invocation.getArguments();
            Assert.assertEquals(merlin, args[0]);
            Assert.assertEquals("Mensaje enviado por Whatsapp", args[1]);
            return null;
        }).when(notificadorWhatsapp).notificar(any(Usuario.class), any(String.class));
        notificadorWhatsapp.notificar(merlin, "Mensaje enviado por Whatsapp");
    }

    @Test
    public void enviarMensajePorSMS() {
        notificadorSMS = Mockito.mock(SMS.class);
        Mockito.doAnswer(invocation -> {
            Object args[] = invocation.getArguments();
            Assert.assertEquals(merlin, args[0]);
            Assert.assertEquals("Mensaje enviado por SMS", args[1]);
            return null;
        }).when(notificadorSMS).notificar(any(Usuario.class), any(String.class));
        notificadorSMS.notificar(merlin, "Mensaje enviado por SMS");
    }
}
