package domain;

import domain.usuario.Evento;
import domain.usuario.Periodo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class RepositorioEventos {
    private static RepositorioEventos instanceOfRepositorioEventos;

    public static RepositorioEventos getInstance() {
        if(instanceOfRepositorioEventos==null) {
            instanceOfRepositorioEventos = new RepositorioEventos();
        }
        return instanceOfRepositorioEventos;
    }

    public Evento findById(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager em = emf.createEntityManager();
        return em.find(Evento.class,Long.valueOf(id));
    }

    public void actualizarEvento(Evento evento) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("quemepongo");
        EntityManager manager = emf.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.merge(evento);
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
