package domain;
import domain.clima.Clima;
import domain.prenda.*;

import javax.persistence.*;
import java.io.IOException;
import java.util.Objects;

@Entity
@Table(name = "prenda")
public class Prenda {
    @Id
    @GeneratedValue
    @Column(name = "prenda_id",columnDefinition = "int(11) NOT NULL")
    private long id;

    private String nombrePrenda;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_prenda_id",columnDefinition = "int(11) NOT NULL")
    private TipoDePrenda tipoDePrenda;

    @Enumerated (EnumType.STRING)
    private Material material;

    @Embedded
    private Color colorPrimario;

   // @Embedded
    @Transient // lo dejo asi para probar solamente
    private Color colorSecundario;

    @Enumerated (EnumType.STRING)
    private Trama trama;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guardarropa_id")
    private Guardarropa guardarropa;

    /*@OneToOne
    @JoinColumn(name = "imagen_id")*/
    @Transient
    private Imagen imagen;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "atuendo_id")
    private Atuendo atuendo;

    @Column(name="impermeable")
    private boolean esParaLluvia;
    private boolean disponibilidad = true; //toda prenda inicia disponible

    private String nombreMaterial;

    //todo: agregar el nombre de la prenda al constructor
    public Prenda(String nombreDePrenda,TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama,
                  Guardarropa guardarropa, boolean impermeable) {
        this.nombrePrenda = nombreDePrenda;
        this.tipoDePrenda = tipoDePrenda;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
        this.guardarropa = guardarropa;
        this.esParaLluvia = impermeable;
    }



    public void cargarImagen(String path) throws IOException {
        this.imagen = this.imagen.leerDeFileSystem(path);
    }

    public boolean esParteSuperior() {
        return this.obtenerCategoria() == Categoria.PARTE_SUPERIOR_ABAJO
                || this.obtenerCategoria() == Categoria.PARTE_SUPERIOR_MEDIO
                || this.obtenerCategoria() == Categoria.PARTE_SUPERIOR_ARRIBA;
    }

    public boolean esAccesorioGeneral() {
        return this.obtenerCategoria() == Categoria.ACCESORIO
                || this.obtenerCategoria() == Categoria.ACCESORIO_CABEZA
                || this.obtenerCategoria() == Categoria.ACCESORIO_PIES;
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
    public String getTipoDePrenda(){
        return this.tipoDePrenda.toString();
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

    public Imagen obtenerImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombrePrenda;
    }

    public void setNombre(String nombre) {
        this.nombrePrenda = nombre;
    }

    public Atuendo getAtuendo() {
        return atuendo;
    }

    public void setAtuendo(Atuendo atuendo) {
        this.atuendo = atuendo;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public Trama getTrama() {
        return this.trama;
    }

    public Color getColorPrimario() {
        return this.colorPrimario;
    }

    public Color getColorSecundario() {
        return this.colorSecundario;
    }

    public Material getMaterial() {
        return this.material;
    }

    public Categoria getCategoria() { return this.tipoDePrenda.obtenerCategoria(); }

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
                Objects.equals(esParaLluvia, prenda.obtenerSiEsParaLluvia()) &&
                Objects.equals(disponibilidad, prenda.getDisponibilidad()) /*&&
                agregar esto cuando imagen tenga equals
                Objects.equals(imagen, prenda.obtenerImagen())*/;
    }

    public String getNombreMaterial() {
        return material.toString();
    }

    public String getNombrePrenda() {
        return nombrePrenda;
    }
}

