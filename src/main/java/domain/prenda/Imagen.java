package domain.prenda;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Entity
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue
    @Column(name = "imagen_id",columnDefinition = "int(11) NOT NULL")
    private long id;

    private int ancho = 900;
    private int alto = 900;
    @Transient
    private BufferedImage imagen;

    private File archivo;

    public Imagen leerDeFileSystem(String path) throws IOException {
        this.archivo = new File(path);
        this.imagen = ImageIO.read(archivo);
        BufferedImage resizedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(imagen, 0, 0, ancho,alto, null);
        g.dispose();
        this.imagen = resizedImage;
        return this;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public File getArchivo() {
        return archivo;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }
}
