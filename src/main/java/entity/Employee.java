package entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Employee extends Person {

    @Enumerated(EnumType.STRING)
    protected EmployeeType employeeType;

    public Employee(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password) {
        super(firstname, lastname, nationalCode, mobileNumber, username, password);
    }

    public Employee() {
    }
}
