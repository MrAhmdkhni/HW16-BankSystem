package menu;

import entity.*;
import exception.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    /*public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";*/

    static CustomerService customerService = new CustomerService();
    static CuratorService curatorService = new CuratorService();
    static BossService bossService = new BossService();
    static AccountService accountService = new AccountService();
    static TransactionService transactionService = new TransactionService();
    static CreditCardService creditCardService = new CreditCardService();
    static Scanner input = new Scanner(System.in);

    public static void menu0() {
        System.out.println("        ** welcome **");
        System.out.println(ANSI_BLACK + "   \"choose your role\"" + ANSI_RESET);
        System.out.println(" 1) boss:");
        System.out.println(" 2) curator:");
        System.out.println(" 3) customer:");
        System.out.println(" 4) exit: ");
        int role = input.nextInt();
        switch (role) {
            case 1:
                firstMenu(1);
                break;
            case 2:
                firstMenu(2);
                break;
            case 3:
                firstMenu(3);
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("---------------\n" + "enter valid number!" + "\n---------------");
                menu0();
        }
    }

    public static void firstMenu(int no) {
        System.out.println(" 1) signup:");
        System.out.println(" 2) login:");
        System.out.println(" 3) exit:");
        int operation = input.nextInt();
        switch (operation) {
            case 1:
                System.out.println(ANSI_BLACK + "    \"sign up menu\"" + ANSI_RESET);
                signUp(no);
                break;
            case 2:
                System.out.println(ANSI_BLACK + "    \"login menu\"" + ANSI_RESET);
                login(no);
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("---------------\n" + "the number is invalid!" + "\n---------------");
        }
    }

    public static void signUp(int no) {
        System.out.println("enter first name:");
        String firstname = input.next();
        System.out.println("enter last name:");
        String lastname = input.next();
        System.out.println("enter national code:");
        String nationalCode = input.next();
        System.out.println("enter mobile number:");
        String mobileNumber = input.next();
        System.out.println("enter username:");
        String username = input.next();
        System.out.println("enter password:");
        String password = input.next();
        if (no == 1) {
            bossService.save(firstname, lastname, nationalCode, mobileNumber, username, password);
        } else if (no == 2) {
            curatorService.save(firstname, lastname, nationalCode, mobileNumber, username, password);
        } else {
            customerService.save(firstname, lastname, nationalCode, mobileNumber, username, password);
        }
        System.out.println(ANSI_GREEN + "sign up was successful" + ANSI_RESET);
    }

    public static void login(int no) {
        System.out.println("enter national code");
        String nationalCode = input.next();
        System.out.println("enter username");
        String username = input.next();
        try {
            if (no == 1) {
                bossService.loadByNationalCodeAndUser(nationalCode, username);
            } else if (no == 2) {
                curatorService.loadByNationalCodeAndUser(nationalCode, username);
            } else {
                Customer customer = customerService.loadByNationalCodeAndUser(nationalCode, username);
                System.out.println(ANSI_GREEN + "entered successfully\n" + ANSI_RESET);
                customerMenu(customer);
            }
        } catch (NotFoundClassException e) {
            System.err.println(e.getMessage());
        }
    }

    /*public static Boss bossLogin() {
        System.out.println("enter national code");
        String nationalCode = input.next();
        System.out.println("enter username");
        String username = input.next();

        try {
            Boss boss = bossService.loadByNationalCodeAndUser(nationalCode, username);

            System.out.println("entered successfully\n");
            return boss;
        } catch (NotFoundClassException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static Curator curatorLogin() {
        System.out.println("enter national code");
        String nationalCode = input.next();
        System.out.println("enter username");
        String username = input.next();

        try {
            Curator curator = curatorService.loadByNationalCodeAndUser(nationalCode, username);

            System.out.println("entered successfully\n");
            return curator;
        } catch (NotFoundClassException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static Customer customerLogin() {
        System.out.println("enter national code");
        String nationalCode = input.next();
        System.out.println("enter username");
        String username = input.next();

        try {
            Customer customer = customerService.loadByNationalCodeAndUser(nationalCode, username);

            System.out.println("entered successfully\n");
            return customer;
        } catch (NotFoundClassException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }*/

    public static void customerMenu(Customer customer) {
        System.out.println(" 1) create account:");
        System.out.println(" 2) view existing account:");
        System.out.println(" 3) cash withdrawal:");
        System.out.println(" 4) deposit:");
        System.out.println(" 5) money transfer:");
        System.out.println(" 6) edit password");
        System.out.println(" 7) edit password");
        System.out.println(" 8) exit:");
        int operation = input.nextInt();
        switch (operation) {
            case 1:
                createAccount(customer);
                break;
            case 2:
                viewExistingAccount(customer);
                break;
            case 3:
                System.out.println(ANSI_BLACK + "    \"withdrawal menu\"" + ANSI_RESET);
                withdrawalOrDeposit(3);
                break;
            case 4:
                System.out.println(ANSI_BLACK + "    \"deposit menu\"" + ANSI_RESET);
                withdrawalOrDeposit(4);
                break;
            case 5:
                transfer(customer);
                break;
            case 6:
                editPassword();
                break;
            case 7:
                System.exit(0);
            default:
                System.out.println("---------------\n" + "the number is invalid!" + "\n---------------");
        }
    }

    public static void createAccount(Customer customer) {
        Account account = new Account();

        System.out.println("enter your password for credit card:");
        String password = input.next();
        CreditCard creditCard = new CreditCard(password);

        try {
            Customer loadedCustomer = customerService.loadByNationalCode(customer.getNationalCode());
            loadedCustomer.addAccount(account);

            account.setCreditCard(creditCard);
            creditCard.setAccount(account);

            customerService.saveOrUpdate(loadedCustomer);
            creditCardService.saveOrUpdate(creditCard);
            System.out.println(ANSI_GREEN + "your account has been successfully created" + ANSI_RESET);
        } catch (NotFoundClassException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void viewExistingAccount(Customer customer) {
        try {
            Customer loadedCustomer = customerService.loadByNationalCode(customer.getNationalCode());
            System.out.println(accountService.loadByCustomerId(loadedCustomer.getId()));
        } catch (NotFoundClassException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void withdrawalOrDeposit(int no) {
        try {
            System.out.println("enter your card number:");
            String cardNumber = input.next();
            CreditCard creditCard = creditCardService.loadByCardNumber(cardNumber);

            System.out.println("enter cvv2:");
            int cvv2 = input.nextInt();
            System.out.println("enter expire date:");
            String expireDate = input.next();

            String password = null;
            for (int i = 1; i <= 3; i++) {
                System.out.println("enter password:");
                password = input.next();
                if (creditCard.getPassword().equals(password)) {
                    break;
                } else if (i == 3) {
                    System.out.println("sorry, your account has been blocked");
                    creditCard.setCounter(i);
                    creditCard.setBlock(true);
                    creditCardService.saveOrUpdate(creditCard);
                    break;
                }
                System.out.println("password is wrong!");
            }

            System.out.println("enter your amount:");
            double amount = input.nextDouble(); // TODO: 10/7/2022 why account is block throw exception? why try catch not catch?
            try {
                if (no == 3) {
                    transactionService.withdrawWithCardNum(cardNumber, cvv2, expireDate, password, amount);
                } else {
                    transactionService.depositWithCardNum(cardNumber, cvv2, expireDate, password, amount);
                }
                System.out.println(ANSI_GREEN + "the transaction was completed successfully" + ANSI_RESET);
            } catch (NotFoundClassException | NotMatchException | BlockAccountException | BalanceNotEnough | RangeException e) {
                System.err.println(e.getMessage());
            }
        } catch (NotFoundClassException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void transfer(Customer customer) {
        List<Account> accounts = accountService.loadByCustomerId(customer.getId());

        System.out.println("enter your origin card number:");
        String originCardNumber = input.next();
        System.out.println("enter your destination card number:");
        String destinationCardNumber = input.next();


        try {
            CreditCard originCard = creditCardService.loadByCardNumber(originCardNumber);

            Account loadedAccount = accountService.loadByCreditCardId(originCard.getId());
            boolean equals = false;
            for (Account account : accounts) {
                equals = account.getCreditCard().getId().equals(loadedAccount.getCreditCard().getId());
                if (equals) {
                    System.out.println(ANSI_GREEN + "Authentication was successful" + ANSI_RESET);
                }
            }
            if (!equals) {
                throw new NotMatchException("This card number does not belong to you");
            }

            System.out.println("enter cvv2:");
            int cvv2 = input.nextInt();
            System.out.println("enter expire date:");
            String expireDate = input.next();

            String password = null;
            for (int i = 1; i <= 3; i++) {
                System.out.println("enter password:");
                password = input.next();
                if (originCard.getPassword().equals(password)) {
                    break;
                } else if (i == 3) {
                    System.out.println("sorry, your account has been blocked");
                    originCard.setCounter(i);
                    originCard.setBlock(true);
                    creditCardService.saveOrUpdate(originCard);
                    break;
                }
            }

            System.out.println("enter your amount:");
            double amount = input.nextDouble();

            transactionService.transfer(originCardNumber, destinationCardNumber, cvv2, expireDate, password, amount);
            System.out.println(ANSI_GREEN + "the transfer was completed successfully" + ANSI_RESET);
        } catch (NotFoundClassException | NotMatchException | BlockAccountException | BalanceNotEnough | RangeException e) {
            System.err.println(e.getMessage());
        }

    }


    public static void editPassword() {
        System.out.println("enter your card number:");
        String cardNumber = input.next();
        System.out.println("enter your old password:");
        String oldPassword = input.next();
        System.out.println("enter your new password:");
        String newPassword = input.next();
        try {
            creditCardService.editPassword(cardNumber, oldPassword, newPassword);
            System.out.println(ANSI_GREEN + "password changed successfully" + ANSI_RESET);
        } catch (NotFoundClassException | NumberFormatException | NotMatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        menu0();
    }

    /*public static void createAccount() {
        Account account = new Account();
        //CreditCard creditCard = new CreditCard("1111");
        //account.setCreditCard(creditCard);

        System.out.println("enter your national code:");
        String nationalCode = input.next();

        try {
            Customer customer = customerService.loadByNationalCode(nationalCode);

            customer.addAccount(account);
            //creditCard.setAccount(account);

            customerService.saveOrUpdate(customer);
            accountService.saveOrUpdate(account);
            //creditCardService.saveOrUpdate(creditCard);
            System.out.println("your account has been successfully created");
        } catch (NotFoundClassException e) {
            System.err.println(e.getMessage());
        }
    }*/



/*    public static void createAccount() {
        Account account = new Account();

        System.out.println("enter your national code:");
        String nationalCode = input.next();
        Customer customer = customerService.loadByNationalCode(nationalCode);

//        System.out.println("enter your password for credit card:");
//        String password = input.next();
//        CreditCard creditCard = new CreditCard(password);

        customer.addAccount(account);
        //creditCard.addAccount(account);

        customerService.saveOrUpdate(customer);
        accountService.saveOrUpdate(account);
        //creditCardService.saveOrUpdate(creditCard);
    }*/

}
