package domain;
import domain.estadoAtuendo.EstadoAtuendo;


import domain.estadoAtuendo.Nuevo;
import domain.prenda.Categoria;
import domain.prenda.TipoDePrenda;
import exceptions.PrendaInvalidaException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static domain.prenda.Categoria.*;
import static java.util.stream.Collectors.toList;

public class Atuendo {

    private Set<Prenda> accesorios = new HashSet<>();
    private Set<Prenda> prendasSuperiores = new HashSet<>();
    private Set<Prenda> prendasInferiores = new HashSet<>();
    private Set<Prenda> calzados = new HashSet<>();
    private EstadoAtuendo estado;

    public Atuendo(Set<Prenda> prendasSuperiores, Set<Prenda> prendasInferiores, Set<Prenda> calzados, Set<Prenda> accesorios) {
        this.validarPrendas(prendasSuperiores, prendasInferiores, calzados, accesorios);
        this.accesorios = accesorios;
        this.prendasSuperiores = prendasSuperiores;
        this.prendasInferiores = prendasInferiores;
        this.calzados = calzados;
        this.estado = new Nuevo(this); //todo atuendo nace en estado nuevo.
    }

    private void validarPrendas(Set<Prenda> prendasSuperiores, Set<Prenda> prendasInferiores, Set<Prenda> calzados, Set<Prenda> accesorios) {
        String mensajeDeError = "";
        mensajeDeError = mensajeDeError.concat(validarPrendas(PARTE_SUPERIOR, prendasSuperiores));

        mensajeDeError = mensajeDeError.concat(validarPrendas(PARTE_INFERIOR, prendasInferiores));

        mensajeDeError = mensajeDeError.concat(validarPrendas(CALZADO, calzados));
        if(accesorios.stream().anyMatch(accesorio->accesorio.obtenerCategoria()==ACCESORIO)) {
            mensajeDeError = mensajeDeError.concat(validarPrendas(ACCESORIO, accesorios));
        }
        if(accesorios.stream().anyMatch(accesorio->accesorio.obtenerCategoria()==ACCESORIO_CUELLO)) {
            mensajeDeError = mensajeDeError.concat(validarPrendas(ACCESORIO_CUELLO, accesorios));
        }
        if(accesorios.stream().anyMatch(accesorio->accesorio.obtenerCategoria()==ACCESORIO_CABEZA)) {
            mensajeDeError = mensajeDeError.concat(validarPrendas(ACCESORIO_CABEZA, accesorios));
        }
        if(accesorios.stream().anyMatch(accesorio->accesorio.obtenerCategoria()==ACCESORIO_MANOS)) {
            mensajeDeError = mensajeDeError.concat(validarPrendas(ACCESORIO_MANOS, accesorios));
        }
        if(accesorios.stream().anyMatch(accesorio->accesorio.obtenerCategoria()==ACCESORIO_PIES)) {
            mensajeDeError = mensajeDeError.concat(validarPrendas(ACCESORIO_PIES, accesorios));
        }

        if (mensajeDeError != "") {
            throw new PrendaInvalidaException(mensajeDeError);
        }
    }

    private String validarPrendas(Categoria categoria, Set<Prenda> prendas) {
        String mensajeDeError = "";
        int prendasInvalidas;
        prendasInvalidas = prendas.stream().
                filter(prenda -> prenda.obtenerCategoria() != categoria)
                .collect(toList())
                .size();
        if (prendasInvalidas > 0) {
            String tipoPrenda = "";
            switch (categoria) {
                case PARTE_INFERIOR:
                    tipoPrenda = "prendas inferiores";
                    break;
                case PARTE_SUPERIOR:
                    tipoPrenda = "prendas superiores";
                    break;
                case CALZADO:
                    tipoPrenda = "prendas de tipo calzado";
                    break;
                case ACCESORIO:
                    tipoPrenda = "prendas accesorias en general";
                    break;
                case ACCESORIO_PIES:
                    tipoPrenda = "prendas accesorias para pies";
                    break;
                case ACCESORIO_MANOS:
                    tipoPrenda = "prendas accesorias para manos";
                    break;
                case ACCESORIO_CABEZA:
                    tipoPrenda = "prendas accesorias para cabeza";
                    break;
                case ACCESORIO_CUELLO:
                    tipoPrenda = "prendas accesorias para cuello";
                    break;
            }
            mensajeDeError = mensajeDeError.concat("Una de las " + tipoPrenda + " no es válida. ");
        }
        return mensajeDeError;
    }

    public Set<Prenda> obtenerPrendasSuperiores() {
        return prendasSuperiores;
    }

    public Set<Prenda> obtenerPrendasInferiores() {
        return prendasInferiores;
    }

    public Set<Prenda> obtenerAccesorios() {
        return accesorios;
    }

    public Set<Prenda> obtenerCalzados() {
        return calzados;
    }

    public EstadoAtuendo obtenerEstadoAtuendo() { return this.estado; }

    public int obtenerCalificacionAnterior() {return this.estado.obtenerCalificacionAnterior();}

    public int obtenerCalificacionActual() {return this.estado.obtenerCalificacionActual();}

    public void aceptar() {
        this.estado.aceptar();
        this.cambiarDisponibilidadPrendas(false);
    }

    private void cambiarDisponibilidadPrendas(boolean nuevaDisponibilidad) {
        this.accesorios.forEach(prenda -> prenda.setDisponibilidad(nuevaDisponibilidad));
        this.calzados.forEach(prenda -> prenda.setDisponibilidad(nuevaDisponibilidad));
        this.prendasInferiores.forEach(prenda -> prenda.setDisponibilidad(nuevaDisponibilidad));
        this.prendasSuperiores.forEach(prenda -> prenda.setDisponibilidad(nuevaDisponibilidad));
    }

    public void calificar(int nuevaCalificacion) {
        this.estado.calificar(nuevaCalificacion);
    }

    public void rechazar() {
        this.estado.rechazar();
        this.cambiarDisponibilidadPrendas(true);
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
        return Objects.equals(accesorios, atuendo.obtenerAccesorios()) &&
                Objects.equals(prendasSuperiores, atuendo.obtenerPrendasSuperiores()) &&
                Objects.equals(prendasInferiores, atuendo.obtenerPrendasInferiores()) &&
                Objects.equals(calzados, atuendo.obtenerCalzados()) &&
                Objects.equals(estado, atuendo.obtenerEstadoAtuendo());
    }

    public boolean esAptoParaLluvia() {
        return this.obtenerAccesorios().stream().anyMatch(accesorio -> accesorio.obtenerTipoDePrenda() == TipoDePrenda.PARAGUAS)
                || (this.prendasSuperiores.stream().anyMatch(prenda -> prenda.obtenerSiEsParaLluvia())
                && this.prendasInferiores.stream().anyMatch(prenda -> prenda.obtenerSiEsParaLluvia())
                && this.calzados.stream().anyMatch(prenda -> prenda.obtenerSiEsParaLluvia()));
    }
}