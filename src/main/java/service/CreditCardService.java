package service;

import entity.CreditCard;
import exception.NotFoundClassException;
import exception.NotMatchException;
import repository.CreditCardRepository;

public class CreditCardService {

    CreditCardRepository creditCardRepository = new CreditCardRepository();
    AccountService accountService = new AccountService();

    public void save(String password) {
        CreditCard creditCard = new CreditCard(password);
        creditCardRepository.saveOrUpdate(creditCard);
    }

    public void saveOrUpdate(CreditCard creditCard) {
        creditCardRepository.saveOrUpdate(creditCard);
    }

    public void delete(CreditCard creditCard) {
        creditCardRepository.delete(creditCard);
    }

    public void deleteByCardNumber(String cardNumber) {
        creditCardRepository.deleteByCardNumber(cardNumber);
    }

    public CreditCard loadById(Long id) {
        CreditCard creditCard = creditCardRepository.loadById(id);
        if (creditCard == null) {
            throw new NotFoundClassException("there is no credit card with this id");
        }
        return creditCard;
    }

    public CreditCard loadByCardNumber(String cardNumber) {
        CreditCard creditCard = creditCardRepository.loadByCardNumber(cardNumber);
        if (creditCard == null) {
            throw new NotFoundClassException("there is no credit card with this card number");
        }
        return creditCard;
    }

    public void editPassword(String cardNumber, String oldPassword, String newPassword) {
        CreditCard loadedCreditCard = loadByCardNumber(cardNumber);
        if (loadedCreditCard.getPassword().equals(oldPassword)) {
            try {
                Integer.parseInt(newPassword);
                loadedCreditCard.setPassword(newPassword);
                saveOrUpdate(loadedCreditCard);
            } catch (NumberFormatException nfe) {
                throw new NumberFormatException("the password format is not correct");
            }
        } else throw new NotMatchException("your old password not correct...!!");
    }

    /*public void transfer(String originCard, String destinationCard, int cvv2, String expireDate, String password, double amount) {
        CreditCard origin = loadByCardNumber(originCard);
        CreditCard destination = loadByCardNumber(destinationCard);
        Account account1 = accountService.loadByCreditCardId(origin.getId());
        Account account2 = accountService.loadByCreditCardId(destination.getId());
        if (!origin.isBlock() && !destination.isBlock()) {
            if (origin.getCvv2() == cvv2 &&
                    origin.getPassword().equals(password) &&
                    origin.getExpireDate().equals(LocalDate.parse(expireDate))) {
                accountService.withdraw(account1.getAccountNumber(), amount + 6);
                accountService.deposit(account2.getAccountNumber(), amount);
            } else
                throw new NotMatchException("the cvv2, expire date or password does not match");
        } else
            throw new RuntimeException("sorry, your account is block");
    }*/

}
