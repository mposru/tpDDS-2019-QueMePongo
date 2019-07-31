package domain;
import domain.clima.Clima;
import domain.prenda.*;

import java.io.IOException;
import java.util.Objects;


public class Prenda {

    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Trama trama;
    private Guardarropa guardarropa;
    private Imagen imagen;
    private double temperaturaMin;
    private double temperaturaMax;
    private boolean esParaLluvia;
    private boolean disponibilidad = true; //toda prenda inicia disponible

    public Prenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama,
                  Guardarropa guardarropa, double temperaturaMin, double temperaturaMax, boolean impermeable) {
        this.tipoDePrenda = tipoDePrenda;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
        // esta bien esto?
        this.guardarropa = guardarropa;
        this.temperaturaMax = temperaturaMax;
        this.temperaturaMin = temperaturaMin;
        this.esParaLluvia = impermeable;
    }

    public void cargarImagen(String path) throws IOException {
        this.imagen = this.imagen.leerDeFileSystem(path);
    }

    public Trama obtenerTrama() {
        return this.trama;
    }

    public TipoAbrigo obtenerTipoDeAbrigo() {
        if (this.esTipoDeAbrigo(TipoAbrigo.BASICO)) {
            return TipoAbrigo.BASICO;
        }
        if (this.esTipoDeAbrigo(TipoAbrigo.MEDIANO)) {
            return TipoAbrigo.MEDIANO;
        }
        if (this.esTipoDeAbrigo(TipoAbrigo.ALTO)) {
            return TipoAbrigo.ALTO;
        }
        return TipoAbrigo.NINGUNO;
    }

    private boolean esTipoDeAbrigo(TipoAbrigo tipoAbrigo) {
        return temperaturaMax <= tipoAbrigo.obtenerTemperaturaMaxima() && temperaturaMin >= tipoAbrigo.obtenerTemperaturaMinima();
    }

    public Color obtenerColorPrimario() {
        return this.colorPrimario;
    }

    public Color obtenerColorSecundario() {
        return this.colorSecundario;
    }

    public Material obtenerMaterial() {
        return this.material;
    }

    public TipoDePrenda obtenerTipoDePrenda() {
        return this.tipoDePrenda;
    }

    public Categoria obtenerCategoria() { return this.tipoDePrenda.obtenerCategoria(); }

    public Guardarropa obtenerGuardarropa() {return this.guardarropa; }

    public double obtenerTemperaturaMax() {
        return temperaturaMax;
    }

    public double obtenerTemperaturaMin() {
        return temperaturaMin;
    }

    public boolean obtenerSiEsParaLluvia() {
        return esParaLluvia;
    }

    public boolean aptaParaTemperatura(Clima climaActual) {
        return this.temperaturaMax <= climaActual.getTemperaturaMaxima() && this.temperaturaMax >= climaActual.getTemperaturaMinima() &&
                this.temperaturaMin <= climaActual.getTemperaturaMaxima() && this.temperaturaMin >= climaActual.getTemperaturaMinima();
    }

    public boolean noMeMojo(Clima climaActual) {
        return ((climaActual.getPrecipitacionDia() != 0 || climaActual.getPrecipitacionNoche() != 0) && this.esParaLluvia) ||
                (climaActual.getPrecipitacionDia() == 0 && climaActual.getPrecipitacionNoche() == 0);
    }

    public boolean getDisponibilidad() { return this.disponibilidad;}
    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenda prenda = (Prenda) o;
        return Objects.equals(tipoDePrenda, prenda.obtenerTipoDePrenda()) &&
                Objects.equals(material, prenda.obtenerMaterial()) &&
                Objects.equals(colorPrimario, prenda.obtenerColorPrimario()) &&
                Objects.equals(colorSecundario, prenda.obtenerColorSecundario()) &&
                Objects.equals(trama, prenda.obtenerTrama()) &&
                Objects.equals(guardarropa, prenda.obtenerGuardarropa()) &&
                Objects.equals(temperaturaMax, prenda.obtenerTemperaturaMax()) &&
                Objects.equals(temperaturaMin, prenda.obtenerTemperaturaMin()) &&
                Objects.equals(esParaLluvia, prenda.obtenerSiEsParaLluvia());
    }

}

// Hacer el requerimiento de limpieza de la prenda (patron state)
