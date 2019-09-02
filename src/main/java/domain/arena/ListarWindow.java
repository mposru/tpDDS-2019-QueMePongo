package domain.arena;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import java.awt.*;

public class ListarWindow extends SimpleWindow<Listar> {

    public ListarWindow(WindowOwner parent) {
        super(parent, new Listar());
        this.getModelObject().obtenerLista();
    }

    @Override
    public void createContents(Panel mainPanel) {
        this.setTitle("Buscar eventos por fecha :: AAAA-MM-DD ");
        this.createFormPanel(mainPanel);
    }

    protected void createFormPanel(Panel mainPanel) {
        mainPanel.setLayout(new VerticalLayout());

        new Label(mainPanel).setText("Ingrese fecha desde:");

        new NumericField(mainPanel).setBackground(Color.ORANGE).bindValueToProperty("fechaDesde");

        new Label(mainPanel).setText("Ingrese fecha hasta:");

        new NumericField(mainPanel).setBackground(Color.ORANGE).bindValueToProperty("fechaHasta");

        new Label(mainPanel)
                .setBackground(Color.ORANGE)
                .bindValueToProperty("usuariosConEventoEntreFechas");

        new Label(mainPanel).setText("EventoEntreFechas");
    }

    @Override
    protected void addActions(Panel mainPanel) {
        new Button(mainPanel)
                .setCaption("Buscar eventos")
                .onClick(() -> this.getModelObject().obtenerLista());
    }
}
