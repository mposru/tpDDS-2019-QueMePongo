package domain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Imagen {

    private int ancho = 900;
    private int alto = 900;
    private BufferedImage imagen;
    private File archivo;

    public void leerDeFileSystem(String path) throws IOException {
        this.archivo = new File(path);
        this.imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        FileInputStream fis = new FileInputStream(archivo);
        this.imagen = ImageIO.read(fis);
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
