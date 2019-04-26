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
    private Guardarropa armarioDeVerano;

    @Before
    public void iniciarTest() {

    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void crearGuardarropaSinUsuario() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("El guardarropa debe tener un usuario");
        armarioDeVerano = new Guardarropa(null) ;
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
