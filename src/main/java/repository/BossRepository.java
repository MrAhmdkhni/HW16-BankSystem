package repository;

import entity.Boss;
import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class BossRepository {

    EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    public void saveOrUpdate(Boss boss) {
        try {
            em.getTransaction().begin();
            if (boss.isNew()) {
                em.persist(boss);
            } else {
                em.merge(boss);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void delete(Boss boss) {
        try {
            em.getTransaction().begin();
            em.remove(boss);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void deleteByNationalCode(String nationalCode) {
        try {
            em.getTransaction().begin();
            String hql = """
                    delete from Boss b where b.nationalCode = :input
                    """;
            Query query = em.createQuery(hql);
            query.setParameter("input", nationalCode);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Boss loadById(Long id) {
        try {
            em.getTransaction().begin();
            Boss loadedBoss = em.find(Boss.class, id);
            em.getTransaction().commit();
            return loadedBoss;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public Boss loadByNationalCodeAndUser(String nationalCode, String username) {
        try {
            em.getTransaction().begin();
            String hql = """
                    from Boss b where b.nationalCode = :input1 and b.username = :input2
                    """;
            TypedQuery<Boss> query = em.createQuery(hql, Boss.class);
            Boss loadedBoss = query.setParameter("input1", nationalCode).setParameter("input2", username).getSingleResult();
            em.getTransaction().commit();
            return loadedBoss;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

}
