package domain.prenda;

import domain.*;
import exceptions.ColorSecundarioException;

import static java.util.Objects.requireNonNull;

public class Borrador  {
    private TipoDePrenda tipoDePrenda;
    private Trama trama = Trama.LISA;
    private Color colorPrimario;
    private Color colorSecundario;
    private Material material;
    private Guardarropa guardarropa;
    private double temperaturaMin;
    private double temperaturaMax;
    private boolean esParaLluvia;
    private String nombre;

    public Borrador definirTipo(TipoDePrenda tipoDePrenda) {
        this.tipoDePrenda = requireNonNull(tipoDePrenda, "Debe ingresar un tipo de prenda");
        return this;
    }

    public Borrador definirNombre(String nombre) {
        this.nombre = requireNonNull(nombre, "Debe ingresar el nombre de la prenda");
        return this;
    }

    public Borrador definirMaterial(Material material) {
        requireNonNull(material, "Debe ingresar un material");
        requireNonNull(tipoDePrenda, "Debe definir el tipo de prenda antes de definir el material");
        tipoDePrenda.validarMaterial(material);
        this.material = material;
        return this;
    }

    public Borrador definirColorPrimario(Color colorPrimario) {
        this.colorPrimario = requireNonNull(colorPrimario, "Debe ingresar un color primario");
        return this;
    }

    public Borrador definirColorSecundario(Color colorSecundario) {
        if (colorPrimario.equals(colorSecundario)) {
            throw new ColorSecundarioException("El color secundario debe ser distinto al color primario");
        }
        this.colorSecundario = colorSecundario;
        return this;
    }

    public Borrador definirTrama(Trama trama) {
        this.trama = requireNonNull(trama, "Debe ingresar una trama");
        return this;
    }

    public Borrador definirGuardarropa(Guardarropa guardarropa) {
        this.guardarropa = requireNonNull(guardarropa, "Debe asignarle un guardarropa a la prenda");
        return this;
    }

    public Borrador definirEsParaLLuvia(boolean esParaLluvia) {
        this.esParaLluvia = requireNonNull(esParaLluvia, "Debe asignarle si es impermeable a la prenda");
        return this;
    }

    public Prenda crearPrenda() {
        requireNonNull(tipoDePrenda, "El tipo de prenda es obligatorio");
        requireNonNull(material, "El material es obligatorio");
        requireNonNull(colorPrimario, "El color es obligatorio");
        requireNonNull(guardarropa, "El guardarropa es obligatorio");
        requireNonNull(esParaLluvia, "Debe asignarle si es impermeable a la prenda");
        requireNonNull(nombre, "Debe ingresar el nombre de la prenda");
        Prenda prenda = new Prenda(tipoDePrenda, material, colorPrimario, colorSecundario, trama, guardarropa, esParaLluvia, nombre);
        return prenda;
    }
}
