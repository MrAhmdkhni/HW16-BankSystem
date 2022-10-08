package repository;

import entity.Branch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class BranchRepository {

    EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    public void saveOrUpdate(Branch branch) {
        try {
            em.getTransaction().begin();
            if (branch.isNew()) {
                em.persist(branch);
            } else {
                em.merge(branch);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void delete(Branch branch) {
        try {
            em.getTransaction().begin();
            em.remove(branch);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Branch loadById(Long id) {
        try {
            em.getTransaction().begin();
            Branch branch = em.find(Branch.class, id);
            em.getTransaction().commit();
            return branch;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public List<Branch> loadByName(String name) {
        try {
            em.getTransaction().begin();
            String hql = """
                    delete from Branch b where b.name = :input
                    """;
            TypedQuery<Branch> query = em.createQuery(hql, Branch.class);
            List<Branch> branches = query.setParameter("input", name).getResultList();
            em.getTransaction().commit();
            return branches;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }
}
