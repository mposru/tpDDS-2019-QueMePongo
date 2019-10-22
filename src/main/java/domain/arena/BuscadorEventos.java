package domain.arena;
/*
import domain.usuario.Evento;
import org.springframework.cglib.core.Local;
import org.uqbar.commons.applicationContext.ApplicationContext;
import org.uqbar.commons.model.annotations.Observable;

import java.time.LocalDateTime;
import java.util.List;

@Observable
public class BuscadorEventos {

    private Integer diaDesde=1;
    private Integer mesDesde=1;
    private Integer anioDesde=1;
    private Integer diaHasta=1;
    private Integer mesHasta=1;
    private Integer anioHasta=1;
    private List<Evento> resultados;

    LocalDateTime fechaDesde;
    LocalDateTime fechaHasta;

    public BuscadorEventos() {
    }

    public void search() {
            fechaDesde = LocalDateTime.of(anioDesde, mesDesde, diaDesde, 0, 0);
            fechaHasta = LocalDateTime.of(anioHasta, mesHasta, diaHasta, 0, 0);
            this.resultados = this.getRepositorioEventos().search(this.fechaDesde, this.fechaHasta);
    }

    public RepositorioEventos getRepositorioEventos() {
        return (RepositorioEventos) ApplicationContext.getInstance().getSingleton(Evento.class);
    }

    public List<Evento> getResultados() {
        return resultados;
    }

    public void setResultados(List<Evento> resultados) {
        this.resultados = resultados;
    }

    public int getDiaDesde() {
        return diaDesde;
    }

    public void setDiaDesde(int diaDesde) {
        this.diaDesde = diaDesde;
    }

    public int getMesDesde() {
        return mesDesde;
    }

    public void setMesDesde(int mesDesde) {
        this.mesDesde = mesDesde;
    }

    public int getAnioDesde() {
        return anioDesde;
    }

    public void setAnioDesde(int anioDesde) {
        this.anioDesde = anioDesde;
    }

    public int getDiaHasta() {
        return diaHasta;
    }

    public void setDiaHasta(int diaHasta) {
        this.diaHasta = diaHasta;
    }

    public int getMesHasta() {
        return mesHasta;
    }

    public void setMesHasta(int mesHasta) {
        this.mesHasta = mesHasta;
    }

    public int getAnioHasta() {
        return anioHasta;
    }

    public void setAnioHasta(int anioHasta) {
        this.anioHasta = anioHasta;
    }
}


 */