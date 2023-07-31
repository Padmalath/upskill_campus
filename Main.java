import java.util.*;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("MyBank");
        Customer customer1 = new Customer("Padmalatha");
        Customer customer2 = new Customer("Bhavana");
        
        bank.openAccount(customer1, 1000);
        bank.openAccount(customer2, 500);

        bank.deposit(1, 500);
        bank.withdraw(2, 200);
        bank.checkBalance(1);
        bank.checkBalance(2);
    }
}

class Bank {
    private String name;
    private List<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void openAccount(Customer customer, double initialBalance) {
        Account account = new Account(customer, initialBalance);
        accounts.add(account);
        System.out.println("Account created successfully for " + customer.getName());
    }

    public void deposit(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println(amount + " deposited successfully to account " + accountNumber);
        } else {
            System.out.println("Account with number " + accountNumber + " not found.");
        }
    }

    public void withdraw(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                System.out.println(amount + " withdrawn successfully from account " + accountNumber);
            } else {
                System.out.println("Insufficient balance in account " + accountNumber);
            }
        } else {
            System.out.println("Account with number " + accountNumber + " not found.");
        }
    }

    public void checkBalance(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Balance in account " + accountNumber + ": " + account.getBalance());
        } else {
            System.out.println("Account with number " + accountNumber + " not found.");
        }
    }

    private Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}

class Account {
    private static int nextAccountNumber = 1;
    private int accountNumber;
    private Customer customer;
    private double balance;

    public Account(Customer customer, double initialBalance) {
        this.customer = customer;
        this.balance = initialBalance;
        this.accountNumber = nextAccountNumber++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

class Customer {
    private String name;
    public Customer(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
