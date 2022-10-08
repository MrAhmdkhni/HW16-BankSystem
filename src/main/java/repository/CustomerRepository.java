package repository;

import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;


public class CustomerRepository {

    EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    public void saveOrUpdate(Customer customer) {
        try {
            em.getTransaction().begin();
            if (customer.isNew()) {
                em.persist(customer);
            } else {
                em.merge(customer);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void delete(Customer customer) {
        try {
            em.getTransaction().begin();
            em.remove(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void deleteByNationalCode(String nationalCode) {
        try {
            em.getTransaction().begin();
            String hql = """
                    delete from Customer c where c.nationalCode = :input
                    """;
            Query query = em.createQuery(hql);
            query.setParameter("input", nationalCode);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Customer loadById(Long id) {
        try {
            em.getTransaction().begin();
            Customer loadedCustomer = em.find(Customer.class, id);
            em.getTransaction().commit();
            return loadedCustomer;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public Customer loadByNationalCodeAndUser(String nationalCode, String username) {
        try {
            em.getTransaction().begin();
            String hql = """
                    from Customer c where c.nationalCode = :input1 and c.username = :input2
                    """;
            TypedQuery<Customer> query = em.createQuery(hql, Customer.class);
            Customer loadedCustomer = query.setParameter("input1", nationalCode).setParameter("input2", username).getSingleResult();
            em.getTransaction().commit();
            return loadedCustomer;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public Customer loadByNationalCode(String nationalCode) {
        try {
            em.getTransaction().begin();
            String hql = """
                    from Customer c where c.nationalCode = :input
                    """;
            TypedQuery<Customer> query = em.createQuery(hql, Customer.class);
            Customer loadedCustomer = query.setParameter("input", nationalCode).getSingleResult();
            em.getTransaction().commit();
            return loadedCustomer;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

}
