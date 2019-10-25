package controller;

import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerCalendario {

    private boolean primeraCarga = true;

    public ModelAndView mostrarCalendarioConEventos(Request req, Response res) {
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(0);
        if(usuario == null) {
            usuario = crearUsuario();
        }

        res.cookie("uid", "0");

        CalendarioSpark calendarioSpark = new CalendarioSpark();

        if(primeraCarga) {
            res.cookie("mes", getMes(LocalDate.now().getMonthValue()));
            res.cookie("anio", Integer.toString(LocalDate.now().getYear()));
            res.cookie("numeroDeMes", Integer.toString(LocalDate.now().getMonthValue()));
            primeraCarga = false;
            res.redirect("/calendario");
        }

        String mes = req.cookie("mes");
        int anio = Integer.parseInt(req.cookie("anio"));
        int numeroDeMes =  Integer.parseInt(req.cookie("numeroDeMes"));

        if(req.queryParams().isEmpty()) {
            calendarioSpark.setMes(mes);
            calendarioSpark.setAnio(Integer.toString(anio));
            List<Dia> dias = obtenerDiasConYSinEventos(anio, numeroDeMes, usuario.getCalendario());
            calendarioSpark.setDiasDelMes(dias);
            return new ModelAndView(calendarioSpark, "calendario.hbs");
        } else if(req.queryParams("action").equals("mesAnterior")) {
            if(numeroDeMes == 1) {
                res.cookie("mes", getMes(12));
                res.cookie("numeroDeMes", "12");
                res.cookie("anio", Integer.toString(anio-1));
            } else {
                res.cookie("mes", getMes(numeroDeMes - 1));
                res.cookie("numeroDeMes", Integer.toString(numeroDeMes - 1));
            }
            res.redirect("/calendario");
            return null;
        } else if(req.queryParams("action").equals("mesSiguiente")) {
            if(numeroDeMes == 12) {
                res.cookie("mes", getMes(1));
                res.cookie("numeroDeMes", "1");
                res.cookie("anio", Integer.toString(anio + 1));
            } else {
                res.cookie("mes", getMes(numeroDeMes + 1));
                res.cookie("numeroDeMes", Integer.toString(numeroDeMes + 1));
            }
            res.redirect("/calendario");
            return null;
        } else {
            calendarioSpark.setMes(mes);
            calendarioSpark.setAnio(Integer.toString(anio));
            List<Dia> dias = obtenerDiasConYSinEventos(anio, numeroDeMes, usuario.getCalendario());
            calendarioSpark.setDiasDelMes(dias);
            return new ModelAndView(calendarioSpark, "calendario.hbs");
        }
    }

    public Usuario crearUsuario() {
        Usuario usuario = new Usuario("1534522454", new Calendario(),"123abc","email","nombre");
        usuario.getCalendario()
                .agregarEvento(new Evento("Parcial Fisica", "UTN",
                        LocalDateTime.of(2019, 10, 14, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Cena", "Deniro",
                        LocalDateTime.of(2019, 10, 2, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Cine", "Unicenter",
                        LocalDateTime.of(2019, 10, 8, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Partido", "el Club",
                        LocalDateTime.of(2019, 10, 8, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Cumpleaños", "casa",
                        LocalDateTime.of(2019, 10, 22, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Noche de los museos", "capital",
                        LocalDateTime.of(2019, 10, 29, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("TP Operativos", "UTN",
                        LocalDateTime.of(2019, 10, 24, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("TP Diseño", "UTN",
                        LocalDateTime.of(2019, 10, 16, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Final AM1", "UTN",
                        LocalDateTime.of(2019, 10, 29, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Cine", "Devoto",
                        LocalDateTime.of(2019, 10, 4, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Pizzeada", "lo de Julio",
                        LocalDateTime.of(2019, 10, 17, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Partido", "San Andrés",
                        LocalDateTime.of(2019, 10, 26, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Salida", "Devoto",
                        LocalDateTime.of(2019, 10, 11, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Juntada de estudio", "casa de Mari",
                        LocalDateTime.of(2019, 10, 18, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Asado", "casa de la suegri",
                        LocalDateTime.of(2019, 10, 20, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Opera", "el Colon",
                        LocalDateTime.of(2019, 10, 19, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Jazz", "Bar en Devoto",
                        LocalDateTime.of(2019, 10, 28, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Almuerzo", "Mi Barrio",
                        LocalDateTime.of(2019, 10, 22, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        RepositorioDeUsuarios.getInstance().agregarUsuario(usuario);
        return usuario;
    }

    public String getMes(int numeroDeMes) {
        switch(numeroDeMes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "";
        }
    }

    public List<Dia> obtenerDiasConYSinEventos(int anio, int mes, Calendario calendario) {
        List<Dia> dias = new ArrayList<>();
        int cantidadDeDiasDelMes = YearMonth.of(anio, mes).lengthOfMonth();
        for (int i = 1; i <= cantidadDeDiasDelMes; i++) {
            Dia dia = new Dia();
            dia.setNumeroDeDia(Integer.toString(i));
            List<Evento> eventosParaEseDia = calendario.obtenerEventosPorFecha(LocalDate.of(anio, mes, i));
            dia.setEventos(eventosParaEseDia);
            dia.setNoEsDiaDemas(true);
            dia.setEsDiaDemas(false);
            dias.add(dia);
        }

        int cantidadDeDiasHastaElPrimerDiaDelMes = LocalDate.of(anio, mes, 1).getDayOfWeek().getValue();
        if(cantidadDeDiasHastaElPrimerDiaDelMes != 7) { // no es domingo el primer dia del mes
            for (int i = 0; i < cantidadDeDiasHastaElPrimerDiaDelMes; i++) {
                Dia diaDemas = new Dia();
                diaDemas.setNumeroDeDia("");
                diaDemas.setEventos(new ArrayList<>());
                diaDemas.setNoEsDiaDemas(false);
                diaDemas.setEsDiaDemas(true);
                dias.add(0, diaDemas);
            }
        }
        return dias;
    }

}

class CalendarioSpark {
    private List<Dia> diasDelMes;
    private String mes;
    private String anio;
    private String numeroDeMes;

    public String getAnio() {
        return anio;
    }

    public String getMes() {
        return mes;
    }

    public String getNumeroDeMes() {
        return numeroDeMes;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setNumeroDeMes(String numeroDeMes) {
        this.numeroDeMes = numeroDeMes;
    }

    public List<Dia> getDiasDelMes() {
        return diasDelMes;
    }

    public void setDiasDelMes(List<Dia> diasDelMes) {
        this.diasDelMes = diasDelMes;
    }
}

class Dia {
    private String numeroDeDia;
    private List<Evento> eventos;
    private Boolean noEsDiaDemas;
    private Boolean esDiaDemas;

    public String getNumeroDeDia() {
        return numeroDeDia;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public Boolean getNoEsDiaDemas() {
        return noEsDiaDemas;
    }

    public Boolean getEsDiaDemas() {
        return esDiaDemas;
    }

    public void setNumeroDeDia(String numeroDeDia) {
        this.numeroDeDia = numeroDeDia;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void setNoEsDiaDemas(Boolean noEsDiaDemas) {
        this.noEsDiaDemas = noEsDiaDemas;
    }

    public void setEsDiaDemas(Boolean esDiaDemas) {
        this.esDiaDemas = esDiaDemas;
    }
}