package domain;

import domain.prenda.TipoDePrenda;
import domain.usuario.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTipoDePrendas {
    private static RepositorioTipoDePrendas instanceOfRepositorioTipoDePrendas;
    private List<TipoDePrenda> tipoDePrendas = new ArrayList<>();
    public static RepositorioTipoDePrendas getInstance() {
        if(instanceOfRepositorioTipoDePrendas==null) {
            instanceOfRepositorioTipoDePrendas = new RepositorioTipoDePrendas();
        }
        return instanceOfRepositorioTipoDePrendas;
    }
    public List<TipoDePrenda> getTipoDePrendas () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dxffzlciern157vi");
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createQuery("select tp from TipoDePrenda tp"); //levantamos la lista de tipo de prendas de la base de datos
        tipoDePrendas = query.getResultList();
        manager.close();
        emf.close();
        return tipoDePrendas;
    }


}
