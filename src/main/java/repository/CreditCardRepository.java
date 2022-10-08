package repository;

import entity.Account;
import entity.CreditCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class CreditCardRepository {

    EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    public void saveOrUpdate(CreditCard creditCard) {
        try {
            em.getTransaction().begin();
            if (creditCard.isNew()) {
                em.persist(creditCard);
            } else {
                em.merge(creditCard);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void delete(CreditCard creditCard) {
        try {
            em.getTransaction().begin();
            em.remove(creditCard);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void deleteByCardNumber(String cardNumber) {
        try {
            em.getTransaction().begin();
            String hql = """
                    delete from CreditCard c where c.cardNumber = :input
                    """;
            Query query = em.createQuery(hql);
            query.setParameter("input", cardNumber);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public CreditCard loadById(Long id) {
        try {
            em.getTransaction().begin();
            CreditCard creditCard = em.find(CreditCard.class, id);
            em.getTransaction().commit();
            return creditCard;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public CreditCard loadByCardNumber(String cardNumber) {
        try {
            em.getTransaction().begin();
            String hql = """
                    from CreditCard c where c.cardNumber = :input
                    """;
            TypedQuery<CreditCard> query = em.createQuery(hql, CreditCard.class);
            CreditCard creditCard = query.setParameter("input", cardNumber).getSingleResult();
            em.getTransaction().commit();
            return creditCard;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public boolean isContain(CreditCard creditCard) { // TODO: 10/5/2022  
        boolean contain = false;
        try {
            contain = em.contains(creditCard);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return contain;
    }

}
