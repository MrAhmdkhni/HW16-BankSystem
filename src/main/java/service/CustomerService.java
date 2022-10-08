package service;

import entity.Customer;
import exception.NotFoundClassException;
import repository.CustomerRepository;

public class CustomerService {

    CustomerRepository customerRepository = new CustomerRepository();

    public void save(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password) {
        Customer customer = new Customer(firstname, lastname, nationalCode, mobileNumber, username, password);
        customerRepository.saveOrUpdate(customer);
    }

    public void saveOrUpdate(Customer customer) {
        customerRepository.saveOrUpdate(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteByNationalCode(String nationalCode) {
        customerRepository.deleteByNationalCode(nationalCode);
    }

    public  Customer loadById(Long id) {
        Customer customerLoaded = customerRepository.loadById(id);
        if (customerLoaded == null) {
            throw new NotFoundClassException("there is no customer with this id...!!");
        }
        return customerLoaded;
    }

    public Customer loadByNationalCodeAndUser(String nationalCode, String username) {
        Customer customerLoaded = customerRepository.loadByNationalCodeAndUser(nationalCode, username);
        if (customerLoaded == null) {
            throw new NotFoundClassException("there is no customer with this national code and username...!!");
        }
        return customerLoaded;
    }

    public Customer loadByNationalCode(String nationalCode) {
        Customer customerLoaded = customerRepository.loadByNationalCode(nationalCode);
        if (customerLoaded == null) {
            throw new NotFoundClassException("there is no customer with this national code...!!");
        }
        return customerLoaded;
    }

}
