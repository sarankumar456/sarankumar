import java.util.ArrayList;
import java.util.List;

interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    void calculateInterest();
    void viewBalance();
}

class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added successfully.");
    }

    public void viewAllAccounts() {
        for (Account account : accounts) {
            account.viewBalance();
        }
    }
}

class SavingsAccount implements Account {
    private double balance;
    private static final double INTEREST_RATE = 0.03; 

    public SavingsAccount(double initialDeposit) {
        this.balance = initialDeposit;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        }
    }

    @Override
    public void calculateInterest() {
        double interest = balance * INTEREST_RATE;
        System.out.println("Calculated interest: $" + interest);
        deposit(interest); 
    }

    @Override
    public void viewBalance() {
        System.out.println("Savings Account Balance: $" + balance);
    }

    public void applyInterest() {
        calculateInterest();
    }
}
class CurrentAccount implements Account {
    private double balance;
    private static final double OVERDRAFT_LIMIT = 500.0;

    public CurrentAccount(double initialDeposit) {
        this.balance = initialDeposit;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance + OVERDRAFT_LIMIT) {
            System.out.println("Overdraft limit exceeded!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        }
    }

    @Override
    public void calculateInterest() {
       
        System.out.println("Current Account does not earn interest.");
    }

    @Override
    public void viewBalance() {
        System.out.println("Current Account Balance: $" + balance);
    }

    public void checkOverdraft() {
        if (balance < 0) {
            System.out.println("Overdraft amount: $" + Math.abs(balance));
        } else {
            System.out.println("No overdraft.");
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();

        SavingsAccount savingsAccount = new SavingsAccount(1000);
        CurrentAccount currentAccount = new CurrentAccount(500);

        bank.addAccount(savingsAccount);
        bank.addAccount(currentAccount);

        savingsAccount.viewBalance();
        savingsAccount.deposit(200);
        savingsAccount.withdraw(50);
        savingsAccount.applyInterest(); 

        System.out.println();

        currentAccount.viewBalance();
        currentAccount.deposit(300);
        currentAccount.withdraw(900); 
        currentAccount.checkOverdraft(); 

        System.out.println();
        bank.viewAllAccounts(); 
    }
}
