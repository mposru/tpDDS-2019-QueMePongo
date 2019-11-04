package domain.prenda;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Objects;

@Embeddable
public class Color {

    private int rojo, verde, azul;

    public Color(int rojo, int verde, int azul) {
        this.rojo = rojo;
        this.verde = verde;
        this.azul = azul;
    }
    public Color() {}

    public int obtenerRojo() {
        return rojo;
    }

    public int obtenerAzul() {
        return azul;
    }

    public int obtenerVerde() {
        return verde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(rojo, color.obtenerRojo()) &&
                Objects.equals(azul, color.obtenerAzul()) &&
                Objects.equals(verde, color.obtenerVerde());
    }
}
