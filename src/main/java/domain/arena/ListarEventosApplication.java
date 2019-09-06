package domain.arena;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class ListarEventosApplication extends Application {

    public static void main(String[] args) {
        Fixture.initialize();
        new ListarEventosApplication().start();
    }

    @Override
    protected Window<?> createMainWindow() {
        return new ListarWindow(this); }
}