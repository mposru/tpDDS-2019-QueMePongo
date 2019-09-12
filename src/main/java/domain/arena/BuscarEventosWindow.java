package domain.arena;

import domain.usuario.Evento;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import java.awt.*;


@SuppressWarnings("serial")
public class BuscarEventosWindow extends SimpleWindow<BuscadorEventos> {

    public BuscarEventosWindow(WindowOwner owner) {
        super(owner, new BuscadorEventos());
        this.getModelObject().search();
    }

    @Override
    protected void createMainTemplate(Panel mainPanel) {
        this.setTitle("Buscador de Eventos");
        this.setTaskDescription("Ingrese los parámetros de búsqueda");

        super.createMainTemplate(mainPanel);
        this.createResultsGrid(mainPanel);
    }


    //  FORMULARIO DE BUSQUEDA

    @Override
    protected void createFormPanel(Panel mainPanel) { //El panel principal de búsuqeda permite filtrar por número y fecha
        Panel searchFormPanel = new Panel(mainPanel);
        searchFormPanel.setLayout(new ColumnLayout(4));

        new Label(searchFormPanel).setText("Fecha desde").setForeground(Color.BLUE);
        new TextBox(searchFormPanel).setWidth(50).bindValueToProperty("diaDesde");
        new TextBox(searchFormPanel).setWidth(50).bindValueToProperty("mesDesde");
        new TextBox(searchFormPanel).setWidth(50).bindValueToProperty("anioDesde");

        new Label(searchFormPanel).setText("Fecha hasta").setForeground(Color.BLUE);
        new TextBox(searchFormPanel).setWidth(50).bindValueToProperty("diaHasta");
        new TextBox(searchFormPanel).setWidth(50).bindValueToProperty("mesHasta");
        new TextBox(searchFormPanel).setWidth(50).bindValueToProperty("anioHasta");

    }


    @Override
    protected void addActions(Panel actionsPanel) {

        new Button(actionsPanel)
                .setCaption("Buscar")
                .onClick(getModelObject()::search)
                .setAsDefault()
                .disableOnError();
    }

    //      RESULTADOS DE LA BUSQUEDA

    //  Se crea la grilla en el panel de abajo.
    //  El binding es: el contenido de la grilla en base a los resultados de la búsqueda. Cuando el usuario presiona Buscar,
    //  se actualiza el model, y éste a su vez dispara la notificación a la grilla que funciona como Observer

    protected void createResultsGrid(Panel mainPanel) {
        Table<Evento> table = new Table<Evento>(mainPanel, Evento.class);
        table.setNumberVisibleRows(10);
        table.setWidth(350);

        table.bindItemsToProperty("resultados");
        table.bindValueToProperty("eventosSeleccionados");

        this.describeResultsGrid(table);
    }


//    Define las columnas de la grilla. Cada columna se puede bindear:
//    1) contra una propiedad del model, como en el caso del nombre del evento o la fecha.
    protected void describeResultsGrid(Table<Evento> table) {
        new Column<Evento>(table) //
                .setTitle("Nombre Evento")
                .setFixedSize(100)
                .bindContentsToProperty("nombre");

        new Column<Evento>(table) //
                .setTitle("Fecha Evento")
                .setFixedSize(100)
                .alignRight()
                .bindContentsToProperty("fecha");

//        2) contra un transformer que recibe el model y devuelve un tipo (generalmente String)
        Column<Evento> ingresoColumn = new Column<Evento>(table);
        ingresoColumn.setTitle("Tiene sugerencia");
        ingresoColumn.setFixedSize(150);
        ingresoColumn.bindContentsToProperty("tieneSugerencia").setTransformer(new BooleanASiNoTransformer());

    }



}