package domain.arena;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import org.uqbar.commons.model.annotations.Observable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Observable
public class Listar {

    private LocalDate FechaDesde;
    private LocalDate FechaHasta;
    List<Usuario> usuarios = RepositorioDeUsuarios.getInstance().getUsuarios();


    // ********************************************************
    // ** Acciones
    // ********************************************************

    public void obtenerLista() {
        List<Usuario> usuariosConEventoEntreFechas = usuarios.stream()
                .filter(usuario -> !usuario.getCalendario().obtenerEventosEntreFechas(FechaDesde,FechaHasta).isEmpty())
                .collect(Collectors.toList());
    }


    // ********************************************************
    // ** Atributos
    // ********************************************************

    public LocalDate getFechaDesde() {
        return FechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        FechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return FechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        FechaHasta = fechaHasta;
    }
}