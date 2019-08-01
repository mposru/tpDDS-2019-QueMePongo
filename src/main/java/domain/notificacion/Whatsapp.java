package domain.notificacion;

import domain.Usuario;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Whatsapp implements Notificador {

    public static final String ACCOUNT_SID = "AC21afe3cf64a21031485643f57ed86f7d";
    public static final String AUTH_TOKEN = "771fdcaa8f1bc2675e8db26f16da62c1";

    public void notificar(Usuario usuario, String mensaje) {
        String numeroAQuienNotificar = "whatsapp:";
        numeroAQuienNotificar = numeroAQuienNotificar.concat(usuario.getNumeroDeCelular());
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(numeroAQuienNotificar), new PhoneNumber("whatsapp:+14155238886"), mensaje).create();
    }

}
