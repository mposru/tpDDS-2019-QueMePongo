package domain;
import domain.estadoAtuendo.EstadoAtuendo;


import domain.estadoAtuendo.Nuevo;
import domain.prenda.Categoria;
import domain.prenda.TipoDePrenda;
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


    public Atuendo(Set<Prenda> prendasSuperiores, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        this.validarPrendas(prendasSuperiores, prendaInferior, calzado, accesorio);
        this.accesorio = accesorio;
        this.prendasSuperiores = prendasSuperiores;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.estado = new Nuevo(this); //todo atuendo nace en estado nuevo.
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

    public EstadoAtuendo obtenerEstadoAtuendo() { return this.estado; }

    public int obtenerCalificacionAnterior() {return this.estado.obtenerCalificacionAnterior();}

    public int obtenerCalificacionActual() {return this.estado.obtenerCalificacionActual();}

    public void aceptar() {
        this.estado.aceptar();
        this.accesorio.setDisponibilidad(false);
        this.calzado.setDisponibilidad(false);
        this.prendaInferior.setDisponibilidad(false);
        this.prendasSuperiores.forEach(prenda -> prenda.setDisponibilidad(false));

    }

    public void calificar(int nuevaCalificacion) {
        this.estado.calificar(nuevaCalificacion);
    }

    public void rechazar() {
        this.estado.rechazar();
    }

    public void cambiarEstado(EstadoAtuendo estado) {
        this.estado = estado;
    }
    public boolean estaCalificado() {
        return this.obtenerCalificacionActual()>0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atuendo atuendo = (Atuendo) o;
        return Objects.equals(accesorio, atuendo.obtenerAccesorio()) &&
                Objects.equals(prendasSuperiores, atuendo.obtenerPrendasSuperiores()) &&
                Objects.equals(prendaInferior, atuendo.obtenerPrendaInferior()) &&
                Objects.equals(calzado, atuendo.obtenerCalzado()) &&
                Objects.equals(estado, atuendo.obtenerEstadoAtuendo());
    }
}