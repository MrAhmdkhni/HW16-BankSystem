package repository;

import entity.Account;
import entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import util.JPAUtil;

public class TransactionRepository {

    EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    public void saveOrUpdate(Transaction transaction) {
        try {
            em.getTransaction().begin();
            if (transaction.isNew()) {
                em.persist(transaction);
            } else {
                em.merge(transaction);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void delete(Transaction transaction) {
        try {
            em.getTransaction().begin();
            em.remove(transaction);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Transaction loadById(Long id) {
        try {
            em.getTransaction().begin();
            Transaction transaction = em.find(Transaction.class, id);
            em.getTransaction().commit();
            return transaction;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }
}
