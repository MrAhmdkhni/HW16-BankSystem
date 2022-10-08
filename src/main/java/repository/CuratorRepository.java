package repository;

import entity.Curator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class CuratorRepository {

    EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    public void saveOrUpdate(Curator curator) {
        try {
            em.getTransaction().begin();
            if (curator.isNew()) {
                em.persist(curator);
            } else {
                em.merge(curator);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void delete(Curator curator) {
        try {
            em.getTransaction().begin();
            em.remove(curator);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void deleteByNationalCode(String nationalCode) {
        try {
            em.getTransaction().begin();
            String hql = """
                    delete from Curator c where c.nationalCode = :input
                    """;
            Query query = em.createQuery(hql);
            query.setParameter("input", nationalCode);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Curator loadById(Long id) {
        try {
            em.getTransaction().begin();
            Curator loadedCurator = em.find(Curator.class, id);
            em.getTransaction().commit();
            return loadedCurator;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public Curator loadByNationalCodeAndUser(String nationalCode, String username) {
        try {
            em.getTransaction().begin();
            String hql = """
                    from Curator c where c.nationalCode = :input1 and c.username = :input2
                    """;
            TypedQuery<Curator> query = em.createQuery(hql, Curator.class);
            Curator loadedCurator = query.setParameter("input1", nationalCode).setParameter("input2", username).getSingleResult();
            em.getTransaction().commit();
            return loadedCurator;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

}
