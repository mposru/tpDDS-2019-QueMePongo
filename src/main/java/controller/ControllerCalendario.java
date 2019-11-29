package controller;

import domain.Guardarropa;
import domain.Prenda;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.guardarropa.Gratuito;
import domain.guardarropa.Premium;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerCalendario {

    public ModelAndView mostrarCalendarioConEventos(Request req, Response res) {
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorEmail(req.session().attribute("user"));
        int anio = Integer.valueOf(req.params("anio"));
        int numeroDeMes = Integer.valueOf(req.params("mes"));

        CalendarioSpark calendarioSpark = new CalendarioSpark();
        calendarioSpark.setMes(getMes(numeroDeMes));
        calendarioSpark.setAnio(Integer.toString(anio));
        List<Dia> dias = obtenerDiasConYSinEventos(anio, numeroDeMes, usuario.getCalendario());
        calendarioSpark.setDiasDelMes(dias);

        return new ModelAndView(calendarioSpark, "calendario.hbs");
    }

    public Usuario crearUsuario() {
        Usuario usuario = new Usuario("1534522454", new Calendario(),"123abc","email","nombre","apellido");
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

        Set<Usuario> usuariosLista = new HashSet<>();
        usuariosLista.add(usuario);

        Guardarropa guardarropa1 = new Guardarropa("GuardarropaInvierno",0);
        Guardarropa guardarropa2 = new Guardarropa("GuardarropaVerano",10);


        Prenda zapatos = new Prenda("Zapatos", TipoDePrenda.ZAPATO, Material.CUERO, new Color(1,2,3), null, Trama.LISA, guardarropa1, true);
        Prenda botasDeNieve = new Prenda("BotasDeNieve",TipoDePrenda.BOTAS_NIEVE, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropa1, false);
        Prenda buzo = new Prenda("Buzo",TipoDePrenda.BUZO, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropa1, false);
        Prenda pantalonPolar = new Prenda("Pantalon Polar",TipoDePrenda.PANTALON, Material.ALGODON, new Color(1,2,3), null, Trama.CUADROS, guardarropa1, false);
        Prenda gorro = new Prenda("Gorro lana",TipoDePrenda.GORRO, Material.LANA, new Color(1,2,3), null, Trama.LISA, guardarropa1, false);


        Prenda musculosa = new Prenda("Musculosa",TipoDePrenda.MUSCULOSA, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropa2,  false);
        Prenda shortDeJean = new Prenda("ShortDeJean", TipoDePrenda.SHORT, Material.JEAN, new Color(1,2,3), null, Trama.LISA, guardarropa2, false);
        Prenda crocs = new Prenda("Crocs",TipoDePrenda.CROCS, Material.GOMA, new Color(1,2,3), null, Trama.CUADROS, guardarropa2, true);
        Prenda pollera = new Prenda("Pollera", TipoDePrenda.POLLERA, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropa2, false);
        Prenda pañuelo = new Prenda("Pañuelo",TipoDePrenda.PANUELO, Material.ALGODON, new Color(1,2,3), null, Trama.LISA, guardarropa2, false);

        guardarropa1.guardarPrenda(zapatos);
        guardarropa1.guardarPrenda(botasDeNieve);
        guardarropa1.guardarPrenda(buzo);
        guardarropa1.guardarPrenda(pantalonPolar);
        guardarropa1.guardarPrenda(gorro);

        guardarropa2.guardarPrenda(shortDeJean);
        guardarropa2.guardarPrenda(musculosa);
        guardarropa2.guardarPrenda(crocs);
        guardarropa2.guardarPrenda(pollera);
        guardarropa2.guardarPrenda(pañuelo);

        usuario.agregarGuardarropa(guardarropa2);
        usuario.agregarGuardarropa(guardarropa1);

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
        List<Evento> eventosDelMes = calendario.obtenerEventosDelMes(anio, mes);

        for (int i = 1; i <= cantidadDeDiasDelMes; i++) {
            Dia dia = new Dia(Integer.toString(i));
            List<Evento> eventosParaEseDia = new ArrayList<>();
            for (Evento evento : eventosDelMes) {
                if (evento.getFecha().toLocalDate().isEqual(LocalDate.of(anio, mes, i)))
                    eventosParaEseDia.add(evento);
            }
            dia.setEventos(eventosParaEseDia);
            dias.add(dia);
        }

        Dia diaVacio = new Dia("");
        diaVacio.setEventos(new ArrayList<>());
        diaVacio.setNoEsDiaDemas(false);
        diaVacio.setEsDiaDemas(true);
        int cantidadDeDiasHastaElPrimerDiaDelMes = LocalDate.of(anio, mes, 1).getDayOfWeek().getValue();
        if(cantidadDeDiasHastaElPrimerDiaDelMes != 7) { // no es domingo el primer dia del mes
            for (int i = 0; i < cantidadDeDiasHastaElPrimerDiaDelMes; i++) {
                dias.add(0, diaVacio);
            }
        }
        System.out.println("dias es: "+dias);
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

    public Dia(String numeroDeDia) {
        this.numeroDeDia = numeroDeDia;
        noEsDiaDemas = true;
        esDiaDemas = false;
    }

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
