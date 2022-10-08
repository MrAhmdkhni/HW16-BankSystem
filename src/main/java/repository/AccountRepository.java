package repository;

import entity.Account;
import entity.CreditCard;
import exception.NotFoundClassException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class AccountRepository {

    EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    public void saveOrUpdate(Account account) {
        try {
            em.getTransaction().begin();
            if (account.isNew()) {
                em.persist(account);
            } else {
                em.merge(account);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void delete(Account account) {
        try {
            em.getTransaction().begin();
            em.remove(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void deleteByAccountNumber(Long accountNumber) {
        try {
            em.getTransaction().begin();
            String hql = """
                    delete from Account a where a.accountNumber = :input
                    """;
            Query query = em.createQuery(hql);
            query.setParameter("input", accountNumber);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Account loadById(Long id) { // TODO: 10/6/2022 handle all load methods with optional class
        try {
            em.getTransaction().begin();
            Account account = em.find(Account.class, id);
            em.getTransaction().commit();
            return account;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public List<Account> loadByCustomerId(Long id) {
        try {
            em.getTransaction().begin();
            String hql = """
                    from Account a where a.customer.id = :input
                    """;
            TypedQuery<Account> query = em.createQuery(hql, Account.class);
            List<Account> loadedAccounts = query.setParameter("input", id).getResultList();
            em.getTransaction().commit();
            return loadedAccounts;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public Account loadByAccountNumber(Long accountNumber) {
        try {
            em.getTransaction().begin();
            String hql = """
                    from Account a where a.accountNumber = :input
                    """;
            TypedQuery<Account> query = em.createQuery(hql, Account.class);
            Account account = query.setParameter("input", accountNumber).getSingleResult();
            em.getTransaction().commit();
            return account;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }

    public Account loadByCreditCardId(Long id) {
        try {
            em.getTransaction().begin();
            String hql = """
                    from Account a where a.creditCard.id = :input
                    """;
            TypedQuery<Account> query = em.createQuery(hql, Account.class);
            Account loadedAccount = query.setParameter("input", id).getSingleResult();
            em.getTransaction().commit();
            return loadedAccount;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }


    /*public void withdraw(long accountNumber, double amount) {
        Account account = loadByAccountNumber(accountNumber);
        if (account != null) {
            if (amount > 0) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    saveOrUpdate(account);
                } else {
                    throw new RuntimeException("your amount must be less than the balance");
                }
            } else {
                throw new RuntimeException("your amount cannot be less than zero");
            }
        } else {
            throw new NotFoundClassException("your account number does not exist!");
        }
    }


    public void deposit(long accountNumber, double amount) {
        Account account = loadByAccountNumber(accountNumber);
        if (account.getId() != null) {
            if (amount > 0) {
                account.setBalance(account.getBalance() + amount);
                saveOrUpdate(account);
            } else {
                throw new RuntimeException("your amount cannot be less than zero");
            }
        } else {
            throw new NotFoundClassException("your account number does not exist!");
        }
    }*/

}
