package entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends Person{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true) // har customer chan ta account dare
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(mappedBy = "customers", cascade = CascadeType.ALL) // har customer bara chan ta branch
    private Set<Branch> branches = new HashSet<>();
    /*@ManyToOne // har customer bara y branch
    private Branch branch;*/



    public Customer(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password) {
        super(firstname, lastname, nationalCode, mobileNumber, username, password);
    }

    public Customer(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password, Set<Account> accounts, Set<Branch> branches) {
        super(firstname, lastname, nationalCode, mobileNumber, username, password);
        this.accounts = accounts;
        this.branches = branches;
    }

    public Customer() {
    }

    // helper method, for one to many
    public void addAccount(Account account) {
        this.accounts.add(account);
        account.setCustomer(this);
    }

    // helper method, for many to many
    public void addBranch(Branch branch) {
        this.branches.add(branch);
        branch.getCustomers().add(this);
    }



    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }
}
