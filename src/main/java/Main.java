import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import repository.*;
import service.AccountService;
import service.CreditCardService;
import service.CustomerService;
import service.TransactionService;
import util.JPAUtil;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();


        AccountRepository accountRepository = new AccountRepository();
        Account account = new Account();
        //accountRepository.saveOrUpdate(account);
        //accountRepository.deleteByAccountNumber(3534632923L);
        //System.out.println(accountRepository.loadByCustomerId(1L));
        //System.out.println(accountRepository.loadByAccountNumber(4634202281L));
        //System.out.println(accountRepository.loadById(1L));
        //System.out.println(accountRepository.loadByCreditCardId(1L));
        //Account account1 = accountRepository.loadByAccountNumber(4465665656L);
        //accountRepository.delete(account1);
        //accountRepository.saveOrUpdate(account1);

        AccountService accountService = new AccountService();
        //accountService.deposit(3870942608L, 1000);
        //accountService.withdraw(3870942608L, 500);
        //System.out.println(accountService.loadByCustomerId(5L));


        /*BranchRepository branchRepository = new BranchRepository();
        CuratorRepository curatorRepository = new CuratorRepository();

        Curator curator = new Curator("reza", "bahari", "0150400555", "09199155555", "@Reza", "2222");

        Branch branch = new Branch("melli");
        branch.addAccount(account);
        branch.addCurator(curator);

        Customer customer = new Customer("saeed", "adham", "6987422556",
                "09009190109", "@saeed", "110");
        customer.addAccount(account);
        customer.addBranch(branch);

        CreditCard creditCard = new CreditCard("3198");
        creditCard.addAccount(account);

        branchRepository.saveOrUpdate(branch);
        accountRepository.saveOrUpdate(account);
        curatorRepository.saveOrUpdate(curator);*/



        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer1 = new Customer("sajjad", "adham", "6987412556",
                "09909190109", "@sajjad", "110");
//        customer.addAccount(account);
//        customerRepository.saveOrUpdate(customer);
//        accountRepository.saveOrUpdate(account);
        //customerRepository.deleteByNationalCode("0150406869");
        //System.out.println(customerRepository.loadByNationalCodeAndUser("0150406666", "@reza"));
        //System.out.println(customerRepository.loadById(5L));

        CustomerService customerService = new CustomerService();
        //customerService.save("sara", "rahimi","6987412351", "09909199100","@sara", "111");
        //customerService.deleteByNationalCode("6987412350");
        /*try {
            Customer customer1 = customerService.loadById(7L);
            customerService.delete(customer1);
        } catch (NotFoundClassException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            System.out.println(customerService.loadByNationalCodeAndUser("0150408888", "@nasiri"));
        } catch (NotFoundClassException e) {
            System.out.println(e.getMessage());
        }*/

        CreditCardRepository creditCardRepository = new CreditCardRepository();
        CreditCard creditCard1 = new CreditCard("5555");
        /*account.setCreditCard(creditCard1);
        creditCard1.setAccount(account);
        creditCardRepository.saveOrUpdate(creditCard1);*/

        //creditCardRepository.saveOrUpdate(creditCard);
        //creditCard.addAccount(account);
        //creditCardRepository.saveOrUpdate(creditCard);
        //System.out.println(creditCardRepository.loadByCardNumber("1234568180451209"));
        //System.out.println(creditCardRepository.loadById(4L));
//        CreditCard creditCard = creditCardRepository.loadByCardNumber("1234567536580229");
//        System.out.println(creditCardRepository.isContain(creditCard));


        CreditCardService creditCardService = new CreditCardService();
        //creditCardService.save("");
        //creditCardService.transfer("1234568180451209", "1234567536580229", 466, "2027-10-04", "5555", 500);
        //System.out.println(creditCardService.editPassword("1234567536580229", "5050"));

        TransactionService transactionService = new TransactionService();
        Transaction transaction = new Transaction();
        //transactionService.transfer("1234568180451209", "1234567536580229", 466, "2027-10-04", "5555", 500);
        //transactionService.deposit(4634202281L, 506);
        //transactionService.withdraw(4994967485L, 1000);

        /*account.addTransaction(transaction);
        customer1.addAccount(account);
        customerService.saveOrUpdate(customer1);
        accountService.saveOrUpdate(account);
        transactionService.saveOrUpdate(transaction);*/

        //transactionService.depositWithCardNum("1234565952130555", 739, "2027-10-07", "6672", 5000);
        //System.out.println(transactionService.checkPassword("1234565952130555", "66728"));


        ///////////////////////

        /*Boss boss1 = new Boss("Amir", "Amadkhani", "0150400111", "09199111111", "@Amir", "1111", );
        Boss boss2 = new Boss("Hamid", "Amadkhani", "0150400444", "0919914444", "@Hamid", "1111", );

        Curator curator1 = new Curator("Amin", "rostami", "0150400222", "09199122222", "@amin", "2222", branch1, boss1);
        Curator curator2 = new Curator("Ali", "saberi", "0150400333", "09199133333", "@Ali", "3333", branch1, boss1);
        Set<Curator> curators1 = new HashSet<>();
        curators1.add(curator1); curators1.add(curator2);

        Curator curator3 = new Curator("reza", "bahari", "0150400555", "09199155555", "@Reza", "2222", branch2, boss2);
        Curator curator4 = new Curator("Ali", "naseri", "0150400666", "09199166666", "@ali", "3333", branch2, boss2);
        Set<Curator> curators2 = new HashSet<>();
        curators2.add(curator3); curators2.add(curator4);

        Customer customer1 = new Customer("arash", "hoseini", "0019851111", "09121751111", "@Arash", "6666", , branch1);
        Customer customer2 = new Customer("mohammad", "naderi", "0019852222", "09121752222", "@mohammad", "7777", , branch1);
        Set<Customer> customers1 = new HashSet<>();
        customers1.add(customer1); customers1.add(customer2);

        Customer customer3 = new Customer("arash", "mohammadi", "0019853333", "09121753333", "@arash", "6666", , branch2);
        Customer customer4 = new Customer("mohammad", "noori", "0019854444", "09121754444", "@Mohammd", "7777", , branch2);
        Set<Customer> customers2 = new HashSet<>();
        customers2.add(customer3); customers2.add(customer4);

        Branch branch1 = new Branch( , curators1, boss1, customers1);
        Branch branch2 = new Branch( , curators2, boss2, customers2);

        CreditCard creditCard3 = new CreditCard("5059", );
        creditCardRepository.saveOrUpdate(creditCard3);*/


    }

    //System.out.println(match("Ali"));
    //System.out.println(matchDate("1400-06-09"));

    public static boolean match(String string){
        return "Ali".equals(string);
    }

    public static boolean matchDate(String localDate){
        LocalDate localDate1 = LocalDate.of(1400, 6, 9);
        System.out.println(localDate1);
        return localDate1.equals(LocalDate.parse(localDate));
    }



}
