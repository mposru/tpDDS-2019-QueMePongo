package domain;

import domain.usuario.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositorioAtuendos {
    private static RepositorioAtuendos instanceOfRepositorioAtuendos;

    public static RepositorioAtuendos getInstance() {
        if(instanceOfRepositorioAtuendos==null) {
            instanceOfRepositorioAtuendos = new RepositorioAtuendos();
        }
        return instanceOfRepositorioAtuendos;
    }

    public void actualizarAtuendo(Atuendo atuendo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dxffzlciern157vi");
        EntityManager manager = emf.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.merge(atuendo);
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
