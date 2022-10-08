package entity;

import base.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
public class Account extends BaseEntity {

    @Transient
    private Random random = new Random();

    @Column(name = "account_number", nullable = false, unique = true)
    private Long accountNumber;

    private Double balance;



    @ManyToOne(cascade = CascadeType.ALL) // har account bara y customer
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // har account y card dare
    private CreditCard creditCard;

    @ManyToOne(cascade = CascadeType.ALL) // har account bara y branch
    private Branch branch;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true) // har account chan ta transaction dare
    private Set<Transaction> transactions = new HashSet<>();



    public Account(Customer customer, CreditCard creditCard, Branch branch, Set<Transaction> transaction) {
        accountNumber = random.nextLong(1000000000L, 10000000000L);
        balance = 0.0;
        this.customer = customer;
        this.creditCard = creditCard;
        this.branch = branch;
        this.transactions = transaction;
    }

    public Account() {
        accountNumber = random.nextLong(1000000000L, 10000000000L);
        balance = 0.0;
    }

    // helper method, for one to many
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setAccount(this);
    }




    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Set<Transaction> getTransaction() {
        return transactions;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transactions = transaction;
    }

    @Override
    public String toString() {
        return "\nAccount {" +
                "accountNumber = " + accountNumber +
                ", balance=" + balance +
                ", customer = (" + customer.getFirstname() + ", " + customer.getLastname() + ", " + customer.getNationalCode() +
                "), creditCard = " + creditCard +
                ", branch = " + branch +
                ", transaction = " + transactions +
                '}' + '\n';
    }
}
