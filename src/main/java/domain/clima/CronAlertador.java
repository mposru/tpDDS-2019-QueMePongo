package domain.clima;

import domain.Alertador;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CronAlertador {

    @Scheduled(fixedDelay = 500000)
    public void chequearCondicionClimatica() {
        List<Alerta> alertas = AccuWeather.getInstance().obtenerAlertas();
        if(!alertas.isEmpty()) {
            Alertador.getInstance().alertarQueVaALLoverOGranizar(alertas);
        }
    }

    @Scheduled(fixedDelay = 500000)
    public void avisarQueEstanListasLasSugerencias() {
        Alertador.getInstance().avisarQueEstanListasLasSugerencias();
    }

}
