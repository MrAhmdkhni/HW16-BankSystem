package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Boss extends Employee {

    @OneToOne(mappedBy = "boss", cascade = CascadeType.ALL, orphanRemoval = true) // har boss bara y branch
    private Branch branch;

    @OneToMany(mappedBy = "boss", cascade = CascadeType.ALL, orphanRemoval = true) // har boss chan ta curator dare
    private Set<Curator> curator = new HashSet<>();



    public Boss(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password) {
        super(firstname, lastname, nationalCode, mobileNumber, username, password);
        employeeType = EmployeeType.SPECIAL;
    }

    public Boss(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password, Branch branch, Set<Curator> curator) {
        super(firstname, lastname, nationalCode, mobileNumber, username, password);
        this.branch = branch;
        this.curator = curator;
        employeeType = EmployeeType.SPECIAL;
    }

    public Boss() {
    }


    // helper method X
    public void addBranch(Branch branch) {
        branch.setBoss(this);
    }

    // helper method
    public void addCurator(Curator curator) {
        this.curator.add(curator);
        curator.setBoss(this);
    }




    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Set<Curator> getCurator() {
        return curator;
    }

    public void setCurator(Set<Curator> curator) {
        this.curator = curator;
    }
}
