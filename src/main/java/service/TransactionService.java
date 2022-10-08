package service;

import entity.Account;
import entity.CreditCard;
import entity.Transaction;
import exception.*;
import repository.TransactionRepository;

import java.time.LocalDate;

public class TransactionService {

    AccountService accountService = new AccountService();
    CreditCardService creditCardService = new CreditCardService();
    TransactionRepository transactionRepository = new TransactionRepository();

    public void saveOrUpdate(Transaction transaction) {
        transactionRepository.saveOrUpdate(transaction);
    }

    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public Transaction loadById(Long id) {
        Transaction transactionLoaded = transactionRepository.loadById(id);
        if (transactionLoaded == null) {
            throw new NotFoundClassException("there is no transaction with this id...!!");
        }
        return transactionLoaded;
    }

    public void withdraw(long accountNumber, double amount) {   // ???????
        Account account = accountService.loadByAccountNumber(accountNumber);
        if (amount > 0) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountService.saveOrUpdate(account);
            } else {
                throw new BalanceNotEnough("your account balance is insufficient...!!");
            }
        } else {
            throw new RangeException("your amount cannot be less than zero...!!");
        }
    }

    public void deposit(long accountNumber, double amount) {
        Account account = accountService.loadByAccountNumber(accountNumber);
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            accountService.saveOrUpdate(account);
        } else {
            throw new RuntimeException("your amount cannot be less than zero...!!");
        }
    }

    public void transfer(String originCardNum, String destinationCardNum, int cvv2, String expireDate, String password, double amount) {
        CreditCard originCard = creditCardService.loadByCardNumber(originCardNum);
        CreditCard destinationCard = creditCardService.loadByCardNumber(destinationCardNum);

        Account account1 = accountService.loadByCreditCardId(originCard.getId());
        Account account2 = accountService.loadByCreditCardId(destinationCard.getId());
        if (!originCard.isBlock() && !destinationCard.isBlock()) {
            if (originCard.getCvv2() == cvv2
                    && originCard.getPassword().equals(password)
                    && originCard.getExpireDate().equals(LocalDate.parse(expireDate))) {
                withdraw(account1.getAccountNumber(), amount + 6);
                deposit(account2.getAccountNumber(), amount);
            } else
                throw new NotMatchException("the cvv2, expire date or password does not match...!!");
        } else
            throw new BlockAccountException("sorry, your account is block...!!");
    }

    public void withdrawWithCardNum(String cardNumber, int cvv2, String expireDate, String password, double amount) {
        CreditCard creditCard = creditCardService.loadByCardNumber(cardNumber);

        Account loadedAccount = accountService.loadByCreditCardId(creditCard.getId());

        if (!creditCard.isBlock()) {
            if (creditCard.getCvv2() == cvv2
                    && creditCard.getPassword().equals(password)
                    && creditCard.getExpireDate().equals(LocalDate.parse(expireDate))) {
                withdraw(loadedAccount.getAccountNumber(), amount);
            } else
                throw new NotMatchException("the cvv2, expire date or password does not match...!!");
        } else
            throw new BlockAccountException("sorry, your account is block...!!");
    }

    public void depositWithCardNum(String cardNumber, int cvv2, String expireDate, String password, double amount) {
        CreditCard creditCard = creditCardService.loadByCardNumber(cardNumber);

        Account loadedAccount = accountService.loadByCreditCardId(creditCard.getId());

        if (!creditCard.isBlock()) {
            if (creditCard.getCvv2() == cvv2
                    && creditCard.getPassword().equals(password)
                    && creditCard.getExpireDate().equals(LocalDate.parse(expireDate))) {
                deposit(loadedAccount.getAccountNumber(), amount);
            } else
                throw new NotMatchException("the cvv2, expire date or password does not match...!!");
        } else
            throw new RuntimeException("sorry, your account is block...!!");
    }

    public boolean checkPassword(String cardNumber, String password) {
        CreditCard creditCard = creditCardService.loadByCardNumber(cardNumber);
        int counter = 0;
        while (!creditCard.isBlock()) {
            if (creditCard.getPassword().equals(password))
                return true;
            else {
                if (counter == 3) {
                    creditCard.setCounter(counter);
                    creditCard.setBlock(true);
                    creditCardService.saveOrUpdate(creditCard);
                }
                counter++;
            }
        }
        return false;
    }



}
