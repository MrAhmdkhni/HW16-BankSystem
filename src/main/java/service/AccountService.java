package service;

import entity.Account;
import exception.NotFoundClassException;
import repository.AccountRepository;

import java.util.List;

public class AccountService {

    AccountRepository accountRepository = new AccountRepository();

    public void save(Account account) {
        accountRepository.saveOrUpdate(account);
    }

    public void saveOrUpdate(Account account) {
        accountRepository.saveOrUpdate(account);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }

    public void deleteByAccountNumber(Long accountNumber) {
        accountRepository.deleteByAccountNumber(accountNumber);
    }

    public Account loadById(Long id) {
        Account loadedAccount = accountRepository.loadById(id);
        if (loadedAccount == null) {
            throw new NotFoundClassException("there is no account with this id...!!");
        }
        return loadedAccount;
    }

    public List<Account> loadByCustomerId(Long id) {
        List<Account> accounts = accountRepository.loadByCustomerId(id);
        if (accounts == null) {
            throw new NotFoundClassException("there is no account with this customer id...!!");
        }
        return accounts;
    }

    public Account loadByAccountNumber(Long accountNumber) {
        Account loadedAccount = accountRepository.loadByAccountNumber(accountNumber);
        if (loadedAccount == null) {
            throw new NotFoundClassException("there is no account with this account number...!!");
        }
        return loadedAccount;
    }

    public Account loadByCreditCardId(Long id) {
        Account loadedAccount = accountRepository.loadByCreditCardId(id);
        if (loadedAccount == null) {
            throw new NotFoundClassException("there is no account with this credit card id...!!");
        }
        return loadedAccount;
    }

    /*public void withdraw(long accountNumber, double amount) {   // ???????
        Account account = loadByAccountNumber(accountNumber);
        if (amount > 0) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.saveOrUpdate(account);
            } else {
                throw new RuntimeException("your account balance is insufficient");
            }
        } else {
            throw new RuntimeException("your amount cannot be less than zero!");
        }
    }

    public void deposit(long accountNumber, double amount) {
        Account account = loadByAccountNumber(accountNumber);
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            accountRepository.saveOrUpdate(account);
        } else {
            throw new RuntimeException("your amount cannot be less than zero!");
        }
    }*/
}
