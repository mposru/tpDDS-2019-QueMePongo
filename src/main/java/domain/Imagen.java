package domain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Imagen {

    private int ancho = 900;
    private int alto = 900;
    private BufferedImage imagen = null;
    private File archivo = null;

    public Imagen(int ancho, int alto, BufferedImage imagen, File archivo) {
        this.ancho = ancho;
        this.alto = alto;
        this.imagen = imagen;
        this.archivo = archivo;
    }

    public Imagen leerDeFileSystem(String path) throws IOException {
        archivo = new File(path);
        imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        imagen = ImageIO.read(archivo);
        return new Imagen(this.ancho, this.alto, imagen, archivo);
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
