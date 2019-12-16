package domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class RepositorioGuardarropas {

    private static RepositorioGuardarropas instanceOfRepositorioDeGuardarropas;
    List<Guardarropa> guardarropas;

    public static RepositorioGuardarropas getInstance() {
        if(instanceOfRepositorioDeGuardarropas==null) {
            instanceOfRepositorioDeGuardarropas = new RepositorioGuardarropas();
        }
        return instanceOfRepositorioDeGuardarropas;
    }

    private RepositorioGuardarropas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createQuery("select a from Guardarropa a"); //levantamos la lista de usuarios de la BBDD
        guardarropas = query.getResultList();
        manager.close();
        emf.close();

    }
    public void actualizarGuardarropa(Guardarropa guardarropa) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager manager = emf.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.merge(guardarropa);
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            manager.close();
        }

    }
}
