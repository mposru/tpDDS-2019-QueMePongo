package domain;

import com.google.common.collect.Sets;
import domain.clima.AccuWeather;
import domain.clima.Clima;
import domain.clima.Meteorologo;
import domain.prenda.*;
import domain.usuario.Evento;
import exceptions.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class Guardarropa {

    private Set<Prenda> prendasSuperiores = new HashSet<>();
    private Set<Prenda> prendasInferiores = new HashSet<>();
    private Set<Prenda> calzados = new HashSet<>();
    private Set<Prenda> accesorios = new HashSet<>();
    private Usuario usuario;
    private Meteorologo meteorologo = new AccuWeather();

    public Guardarropa(Usuario usuario) {
        // validar que sean premium
        // agregar user y eliminar user
        // recibe como param set de usuarios y los que son free solo pueden leer
        this.usuario = requireNonNull(usuario, "Debe ingresar un usuario");
    }

    public int limiteDePrendas() {
        // cómo se comporta esto si está compartido? no tiene limite
        return this.usuario.limiteDePrendas();
    } //usuario.limiteDePrendas(); // el guardarropas queda seteado con el limite que tenga el usuario dueño del mismo


    public int obtenerCantidadDePrendas() {
        return (this.prendasSuperiores.size() + this.prendasInferiores.size() + this.accesorios.size() + this.calzados.size());
    }

    public Set<Prenda> obtenerPrendasSuperiores() {
        return prendasSuperiores;
    }

    public Set<Prenda> obtenerPrendasInferiores() {
        return prendasInferiores;
    }

    public Set<Prenda> obtenerCalzados() {
        return calzados;
    }

    public Set<Prenda> obtenerAccesorios() {
        return accesorios;
    }

    public Usuario obtenerUsuario() {
        return usuario;
    }

    public void verificarLimiteDePrendas() {
        if (this.usuario.tieneLimiteDePrendas() && this.obtenerCantidadDePrendas() >= this.usuario.limiteDePrendas()) {
            throw new SuperaLimiteDePrendasException("Se supera el límite de " + this.usuario.limiteDePrendas() + " prendas definido para el tipo de usuario del guardarropa");
        }
    }

    public void guardarPrenda(Prenda prenda) {
        this.verificarLimiteDePrendas();
        switch (prenda.obtenerCategoria()) {
            case CALZADO:
                calzados.add(prenda);
                break;
            case PARTE_SUPERIOR:
                prendasSuperiores.add(prenda);
                break;
            case PARTE_INFERIOR:
                prendasInferiores.add(prenda);
                break;
            case ACCESORIO:
                accesorios.add(prenda);
                break;
        }


    }

    private void validarPrendas(Set<Prenda> prendasSuperioresValidas, Set<Prenda> prendasInferioresValidas,
                                Set<Prenda> calzadosValidos, Set<Prenda> accesoriosValidos, Set<Prenda> abrigosAlto,
                                Set<Prenda> abrigosMediano, Set<Prenda> abrigosBasico) {
        //todo: mandar en el mensaje de error el clima. se concatena en la misma linea que se tira error
        String mensajeDeError = "";
        if(prendasSuperioresValidas.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan prendas superiores adecuadas para el clima del evento. ");
        } else {
            if (abrigosAlto.size() <= 0) {
                mensajeDeError = mensajeDeError.concat("Faltan prendas superiores abrigadas de tipo alto para el clima del evento. ");
            }
            if (abrigosMediano.size() <= 0) {
                mensajeDeError = mensajeDeError.concat("Faltan prendas superiores abrigadas de tipo mediano para el clima del evento. ");
            }
            if (abrigosBasico.size() <= 0) {
                mensajeDeError = mensajeDeError.concat("Faltan prendas superiores abrigadas de tipo basico para el clima del evento. ");
            }
        }
        if(prendasInferioresValidas.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan prendas inferiores adecuadas para el clima del evento. ");
        }
        if (calzadosValidos.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan zapatos adecuados para el clima del evento. ");
        }
        if (accesoriosValidos.size() <= 0) {
            mensajeDeError = mensajeDeError.concat("Faltan accesorios adecuados para el clima del evento. ");
        }
        if (mensajeDeError != "") {
            throw new FaltaPrendaException(mensajeDeError);
        }
    }

    public List<Atuendo> generarSugerencia(Usuario usuario, Clima climaEvento, Evento evento) {// clima del dia (y ver si llueve o no) y evento (por si es formal o no???) como param
        // en generar sugerencia, para obtener las prendas validas, se le
        // va a preguntar a los usuarios "dueños" el listado de atuendosAceptados y esas
        // prendas no van a poder ser usadas
        // si las prendas que el usuario está pidiendo *si* pueden usarse acá, habria que tambien pasar el usuario por parametro
        // y de esa manera sí se van a poder devolver prendas que pertenezcan a sus atuendosAceptados
        // solo por una cantidad de tiempo no es usable
        // delegar en otro objeto

        // se filtra despues de crear sugerencia

        // guardarropas trackea cuales estan libres y cuales no
        Set<Prenda> prendasInferioresAdecuadas;
        Set<Prenda> calzadosAdecuados;
        Set<Prenda> accesoriosAdecuados;
        Set<Prenda> abrigosBasico;
        Set<Prenda> abrigosMediano;
        Set<Prenda> abrigosAlto;
        List<Atuendo> atuendosSugeridos = new ArrayList<>();

        usuario.validarEventoDia(); //Ante la falencia de que no hay evento del dia tira excepcion

        //Clima climaEvento = meteorologo.obtenerClima();

        prendasInferioresAdecuadas = this.obtenerPrendaSegunClima(prendasInferiores, climaEvento);
        calzadosAdecuados = this.obtenerPrendaSegunClima(calzados, climaEvento);
        accesoriosAdecuados = this.obtenerPrendaSegunClima(accesorios, climaEvento);
        abrigosBasico = filtrarPrendaPorAbrigo(prendasSuperiores, TipoAbrigo.BASICO, climaEvento);
        abrigosMediano = filtrarPrendaPorAbrigo(prendasSuperiores, TipoAbrigo.MEDIANO, climaEvento);
        abrigosAlto = filtrarPrendaPorAbrigo(prendasSuperiores, TipoAbrigo.ALTO, climaEvento);

        this.validarPrendas(prendasSuperiores, prendasInferioresAdecuadas, calzadosAdecuados,
                accesoriosAdecuados, abrigosAlto, abrigosMediano, abrigosBasico);

        Set<Set<Prenda>> prendasSuperioresArmadas = Sets.cartesianProduct(abrigosBasico, abrigosMediano, abrigosAlto)
                .stream()
                .map(conjuntoSuperior -> new HashSet<Prenda>(conjuntoSuperior))
                .collect(Collectors.toSet());

        return Sets.cartesianProduct(prendasSuperioresArmadas, prendasInferioresAdecuadas, calzadosAdecuados, accesoriosAdecuados)
                .stream()
                .map(atuendo -> new Atuendo((Set<Prenda>) atuendo.get(0), (Prenda) atuendo.get(1), (Prenda) atuendo.get(2), (Prenda) atuendo.get(3)))
                .collect(Collectors.toList());
    }

    private Set<Prenda> filtrarPrendaPorAbrigo(Set<Prenda> prendasSinFiltrar, TipoAbrigo tipoAbrigo, Clima clima) {
        if (clima.getTemperaturaMaxima() > tipoAbrigo.obtenerTemperaturaMaxima()) {
            // clima no matchea con tipo de abrigo
            Prenda prendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0, 0, 0),
                    null, Trama.NINGUNO, this, 0, 0, false);
            Set<Prenda> prendasVacias = new HashSet<>();
            prendasVacias.add(prendaVacia);
            return prendasVacias;
        }
        return prendasSinFiltrar.stream().filter(prenda -> prenda.obtenerTipoDeAbrigo() == tipoAbrigo).collect(Collectors.toSet());
    }


    private Set<Prenda> obtenerPrendaSegunClima(Set<Prenda> prendas, Clima climaEvento) {
        return prendas.stream().filter((prenda) -> prenda.aptaParaTemperatura(climaEvento) && prenda.noMeMojo(climaEvento)).collect(Collectors.toSet());
    }

    public void definirMeteorologo(Meteorologo meteorologo) {
        this.meteorologo = meteorologo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardarropa guardarropa = (Guardarropa) o;
        return Objects.equals(prendasInferiores, guardarropa.obtenerPrendasInferiores()) &&
                Objects.equals(prendasSuperiores, guardarropa.obtenerPrendasSuperiores()) &&
                Objects.equals(calzados, guardarropa.obtenerCalzados()) &&
                Objects.equals(accesorios, guardarropa.obtenerAccesorios()) &&
                Objects.equals(usuario, guardarropa.obtenerUsuario());
    }

    public Meteorologo obtenerMeteorologo() {
        return this.meteorologo;
    }
}
