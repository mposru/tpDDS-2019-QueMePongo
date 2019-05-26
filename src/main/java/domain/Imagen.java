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

    public void leerDeFileSystem(String path) throws IOException {
        this.archivo = new File(path);
        this.imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        this.imagen = ImageIO.read(archivo);
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
