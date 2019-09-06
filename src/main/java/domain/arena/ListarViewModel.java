package domain.arena;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import org.uqbar.commons.model.annotations.Observable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Observable
public class ListarViewModel {

    private LocalDate FechaDesde;
    private LocalDate FechaHasta;
    List<Usuario> usuarios;// = RepositorioDeUsuarios.getInstance().getUsuarios();

    public ListarViewModel() {
        this.usuarios = RepositorioDeUsuarios.getInstance().getUsuarios();
        System.out.println("Cantidad de usuarios: " + usuarios.size());
    }

    public void obtenerLista() {
        List<Usuario> usuariosConEventoEntreFechas = usuarios.stream()
                .filter(usuario -> !usuario.getCalendario().obtenerEventosEntreFechas(FechaDesde,FechaHasta).isEmpty())
                .collect(Collectors.toList());
    }


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