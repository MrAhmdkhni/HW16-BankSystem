package entity;

import base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL) // har transaction bara y account
    private Account account;



    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
