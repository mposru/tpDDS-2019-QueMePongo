/*
package domain.arena;

import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.usuario.Evento;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.applicationContext.ApplicationContext;

public class EventosApplication extends Application {

    public static void main(String[] args) {
        new EventosApplication().start();
    }

    @Override
    protected Window<?> createMainWindow() {
        ApplicationContext.getInstance().configureSingleton(Evento.class, new RepositorioEventos());
        return new BuscarEventosWindow(this ); }
}*/
