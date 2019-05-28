package domain;
import exceptions.*;


import exceptions.PrendaInvalidaException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class Atuendo {

    private Prenda accesorio;
    private Set<Prenda> prendasSuperiores = new HashSet<>();
    private Prenda prendaInferior;
    private Prenda calzado;
    private EstadoAtuendo estado;
    private int calificacion = 0;
    private int calificacionAnterior = 0;

    public Atuendo(Set<Prenda> prendasSuperiores, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        this.validarPrendas(prendasSuperiores, prendaInferior, calzado, accesorio);
        this.accesorio = accesorio;
        this.prendasSuperiores = prendasSuperiores;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.estado = new Nuevo(); //todo atuendo nace en estado nuevo.
    }

    private void validarPrendas(Set<Prenda> prendasSuperiores, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        String mensajeDeError = "";
        int prendasSuperioresInvalidas;
        prendasSuperioresInvalidas = prendasSuperiores.stream().
                filter(prendaSuperior -> prendaSuperior.obtenerCategoria() != Categoria.PARTE_SUPERIOR)
                .collect(toList())
                .size();
        if (prendasSuperioresInvalidas > 0) {
            mensajeDeError = mensajeDeError.concat("Una de las prendas superiores no es válida. ");
        }
        if (prendaInferior.obtenerCategoria() != Categoria.PARTE_INFERIOR) {
            mensajeDeError = mensajeDeError.concat("La prenda inferior no es válida. ");
        }
        if (calzado.obtenerCategoria() != Categoria.CALZADO) {
            mensajeDeError = mensajeDeError.concat("El calzado no es válido. ");
        }
        if (accesorio.obtenerCategoria() != Categoria.ACCESORIO) {
            mensajeDeError = mensajeDeError.concat("El accesorio no es válido. ");
        }
        if (mensajeDeError != "") {
            throw new PrendaInvalidaException(mensajeDeError);
        }
    }

    public Set<Prenda> obtenerPrendasSuperiores() {
        return prendasSuperiores;
    }

    public Prenda obtenerPrendaInferior() {
        return prendaInferior;
    }

    public Prenda obtenerAccesorio() {
        return accesorio;
    }

    public Prenda obtenerCalzado() {
        return calzado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atuendo atuendo = (Atuendo) o;
        return Objects.equals(accesorio, atuendo.obtenerAccesorio()) &&
                Objects.equals(prendasSuperiores, atuendo.obtenerPrendasSuperiores()) &&
                Objects.equals(prendaInferior, atuendo.obtenerPrendaInferior()) &&
                Objects.equals(calzado, atuendo.obtenerCalzado());
    }


    public EstadoAtuendo obtenerEstadoAtuendo() { return this.estado; }

    public int obtenerCalificacionActual() { return this.calificacion;}
    public int obtenerCalificacionAnterior() { return this.calificacionAnterior;}

    public void aceptar() {
        if(!(this.estado instanceof Nuevo)) {
            throw new NoSePuedeAceptarException("Sólo se puede aceptar un atuendo con estado = Nuevo");
        }
        this.estado = new Aceptado(); //quedaría mejor usar singleton. Hay que cambiarlo
    }

    public void calificar(int nuevaCalificacion) {
     /*   if (nuevaCalificacion < 1 && nuevaCalificacion > 5) {
            throw new RangoDeCalificacionException("La calificación del atuendo debe estar entre 1 y 5");
        }
        if (this.obtenerEstadoAtuendo().equals("Calificado")) {
            this.calificacionAnterior = this.calificacion;
        }

      */
        if (!(this.estado instanceof Aceptado)) {
            throw new NoSePuedeCalificarException("Sólo se puede calificar un atuendo aceptado");
        }
        this.calificacion = nuevaCalificacion;
        this.estado = new Calificado();
    }

    public void rechazar() {
        if(!this.obtenerEstadoAtuendo().equals("Nuevo")) {
            throw new NoSePuedeRechazarException("Solo se puede rechazar un atuendo con estado = Nuevo");
        }
        this.estado = new Rechazado();
    }


    public void cambiarEstado(EstadoAtuendo estado) {
        this.estado = estado;
    }



}