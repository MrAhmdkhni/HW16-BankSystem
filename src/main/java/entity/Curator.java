package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Curator extends Employee {

    @ManyToOne(cascade = CascadeType.ALL) // har curator bara y branch
    private Branch branch;

    @ManyToOne(cascade = CascadeType.ALL) // har curator bara y boss
    private Boss boss;


    public Curator(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password) {
        super(firstname, lastname, nationalCode, mobileNumber, username, password);
        employeeType = EmployeeType.NORMAL;
    }

    public Curator(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password, Branch branch, Boss boss) {
        super(firstname, lastname, nationalCode, mobileNumber, username, password);
        this.branch = branch;
        this.boss = boss;
        employeeType = EmployeeType.NORMAL;
    }

    public Curator() {
    }



    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}
