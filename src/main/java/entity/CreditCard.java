package entity;

import base.BaseEntity;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

@Entity
@ToString
public class CreditCard extends BaseEntity {

    public Random random(){
        return new Random();
    }
    /*@Transient
    private Random random = new Random();*/

    @Column(name = "card_number", length = 16, nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private Integer cvv2;

    @Column(name = "expire_date", nullable = false)
    private LocalDate expireDate;

    @Column(nullable = false)
    private String password;

    private boolean block;
    private int counter;



    @OneToOne(mappedBy = "creditCard", cascade = CascadeType.ALL, orphanRemoval = true) // har card bara y account
    @ToString.Exclude
    private Account account = new Account(); // TODO: 10/5/2022 recent added




    public CreditCard(String password, Account account) {
        this.password = password;
        this.account = account;
        cardNumber = "123456" + random().nextLong(1000000000L, 10000000000L);
        cvv2 = random().nextInt(100, 1000);
        expireDate = LocalDate.now().plusYears(5L);
        block = false;
        counter = 0;
        /*account.setCreditCard(this);*/
    }

    public CreditCard(String password) {
        this.password = password;
        cardNumber = "123456" + random().nextLong(1000000000L, 10000000000L);
        cvv2 = random().nextInt(100, 1000);
        expireDate = LocalDate.now().plusYears(5L);
        block = false;
        counter = 0;
    }

    public CreditCard() {

    }

    // helper method X
    public void addAccount(Account account) {
        account.setCreditCard(this);
    }




    public String getCardNumber() {
        return cardNumber;
    }

    public Integer getCvv2() {
        return cvv2;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }


    /*@Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvv2=" + cvv2 +
                ", expireDate=" + expireDate +
                ", password='" + password + '\'' +
                ", block=" + block +
                ", counter=" + counter +
                ", account=" + account +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return block == that.block && counter == that.counter && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cvv2, that.cvv2) && Objects.equals(expireDate, that.expireDate) && Objects.equals(password, that.password) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cvv2, expireDate, password, block, counter, account);
    }
}
