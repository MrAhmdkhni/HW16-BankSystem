package entity;

import base.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Branch extends BaseEntity {

    private String name;


    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true) // har branch chan ta account dare
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true) // har branch chan ta employee dare
    private Set<Curator> curators = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // har branch y boss dare
    private Boss boss;

    @ManyToMany(cascade = CascadeType.ALL) // har branch chan ta customer dare
    private Set<Customer> customers = new HashSet<>();
    /*@OneToMany(mappedBy = "branch") // har branch chan ta customer dare
    private Set<Customer> customers;*/


    public Branch(String name) {
        this.name = name;
    }

    public Branch(Set<Account> accounts, Set<Curator> curators, Boss boss, Set<Customer> customers) {
        this.accounts = accounts;
        this.curators = curators;
        this.boss = boss;
        this.customers = customers;
    }

    public Branch() {
    }

    // helper method, for one to many
    public void addAccount(Account account) {
        this.accounts.add(account);
        account.setBranch(this);
    }

    // helper method, for one to many
    public void addCurator(Curator curator) {
        this.curators.add(curator);
        curator.setBranch(this);
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Curator> getCurators() {
        return curators;
    }

    public void setCurators(Set<Curator> curators) {
        this.curators = curators;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
