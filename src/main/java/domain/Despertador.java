package domain;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Despertador implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.darConsultaAUsuarios();
    }

    public void darConsultaAUsuarios(){
         RepositorioDeUsuarios.getInstance().usuariosTotal.forEach(usuario -> usuario.verificarSiHayEventoProximo());
    }
}
