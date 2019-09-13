package domain;
import domain.clima.Clima;
import domain.prenda.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.IOException;
import java.util.Objects;

@Entity
public class Prenda {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private TipoDePrenda tipoDePrenda;
    @ManyToOne
    private Material material;
    @ManyToOne
    private Color colorPrimario;
    @ManyToOne
    private Color colorSecundario;
    @ManyToOne
    private Trama trama;
    //Esta bien no poner la anotation?
    private Guardarropa guardarropa;
    private Imagen imagen;
    private boolean esParaLluvia;
    private boolean disponibilidad = true; //toda prenda inicia disponible

    public Prenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama,
                  Guardarropa guardarropa, boolean impermeable) {
        this.tipoDePrenda = tipoDePrenda;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
        // esta bien esto?
        this.guardarropa = guardarropa;
        this.esParaLluvia = impermeable;
    }

    public void cargarImagen(String path) throws IOException {
        this.imagen = this.imagen.leerDeFileSystem(path);
    }

    public double obtenerUnidadDeAbrigo() {
        return this.tipoDePrenda.obtenerUnidadDeAbrigo();
    }

    public Trama obtenerTrama() {
        return this.trama;
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

    public boolean obtenerSiEsParaLluvia() {
        return esParaLluvia;
    }


    public boolean noMeMojo(Clima climaActual) {
        return ((climaActual.getPrecipitacionDia() != 0 || climaActual.getPrecipitacionNoche() != 0) && this.esParaLluvia) ||
                (climaActual.getPrecipitacionDia() == 0 && climaActual.getPrecipitacionNoche() == 0);
    }

    public boolean getDisponibilidad() { return this.disponibilidad; }

    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad; }

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
                Objects.equals(esParaLluvia, prenda.obtenerSiEsParaLluvia());
    }

}

