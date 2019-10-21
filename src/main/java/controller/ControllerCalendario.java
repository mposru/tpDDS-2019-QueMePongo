package controller;

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
        Usuario usuario = new Usuario("1534522454", new Calendario());
        usuario.getCalendario()
                .agregarEvento(new Evento("Cine", "Unicenter",
                                LocalDateTime.of(2019, 5, 29, 17, 50, 30),
                                Periodo.NINGUNO, 0));

        usuario.getCalendario()
                .agregarEvento(new Evento("Cumpleaños", "Deniro Av. San Martin",
                        LocalDateTime.of(2019, 5, 29, 17, 50, 30),
                        Periodo.NINGUNO, 0));

        CalendarioSpark calendarioSpark = new CalendarioSpark();
        //Map<String, String> model = new HashMap<>();

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
            //model.put("mes", mes);
            //model.put("anio", Integer.toString(anio));
            //return new ModelAndView(model, "calendario.hbs");
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
            //model.put("mes", mes);
            //model.put("anio", Integer.toString(anio));
            //return new ModelAndView(model, "calendario.hbs");
            calendarioSpark.setMes(mes);
            calendarioSpark.setAnio(Integer.toString(anio));
            List<Dia> dias = obtenerDiasConYSinEventos(anio, numeroDeMes, usuario.getCalendario());
            calendarioSpark.setDiasDelMes(dias);
            return new ModelAndView(calendarioSpark, "calendario.hbs");
        }
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
        //que dia del mes es lunes y agregar a la lista de dias n cantidad de dias vacios
        for (int i = 1; i <= cantidadDeDiasDelMes; i++) {
            Dia dia = new Dia();
            dia.setNumeroDeDia(Integer.toString(i));
            List<Evento> eventosParaEseDia = calendario.obtenerEventosPorFecha(LocalDate.of(anio, mes, i));
            dia.setEventos(eventosParaEseDia);
            dias.add(dia);
        }

        int cantidadDeDiasHastaElPrimerDiaDelMes = LocalDate.of(anio, mes, 1).getDayOfWeek().getValue();
        if(cantidadDeDiasHastaElPrimerDiaDelMes != 7) { // no es domingo el primer dia del mes
            for (int i = 0; i < cantidadDeDiasHastaElPrimerDiaDelMes; i++) {
                Dia diaDemas = new Dia();
                diaDemas.setNumeroDeDia("");
                diaDemas.setEventos(new ArrayList<>());
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

    public String getNumeroDeDia() {
        return numeroDeDia;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setNumeroDeDia(String numeroDeDia) {
        this.numeroDeDia = numeroDeDia;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
