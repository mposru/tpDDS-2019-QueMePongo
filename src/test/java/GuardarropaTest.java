package domain;


import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Set;


public class GuardarropaTest {
    private Set<Prenda> prendasSuperiores;
    private Set<Prenda> prendasInferiores;
    private Set<Prenda> calzados;
    private Set<Prenda> accesorios;
    private Usuario usuario;
    private Guardarropa armarioDeVerano;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void crearGuardarropaSinUsuario() throws Exception {
        exception.expect(NullPointerException.class);
        exception.expectMessage("El guardarropa debe tener un usuario");
        armarioDeVerano = new Guardarropa(null) ;
    }

    @Test
    public void sugerirGuardarropaValido() throws Exception {

    }
}
