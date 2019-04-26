package domain;


import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Set;


public class GuardarropaTest {
    private Set<Prenda> prendasSuperiores;
    private Set<Prenda> prendasInferiores;
    private Set<Prenda> calzados;
    private Set<Prenda> accesorios;
    private Usuario usuario;
    private Guardarropa guardarropa;

    @Before
    public void iniciarTest() {
        this.guardarropa = new Guardarropa();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void definirUsuarioVacio() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("El usuario no puede ser vacio");
        this.guardarropa.definirUsuario(null);
    }

    @Test
    public void sugerirAtuendos() throws Exception {
    }

    @Test
    public void guardarPartesSuperiores() {

    }

    @Test
    public void guardarPartesInferiores() {

    }

    @Test
    public void guardarCalzados() {

    }


    @Test
    public void guardarAccesorios() {

    }
}
