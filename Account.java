package bank.model;

public class Account {

    private int accountNo;
    private String name;
    private String accountType;
    private double balance;

    public Account() {}

    public Account(String name, String accountType, double balance) {
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Account(int accountNo, String name, String accountType, double balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
    }

    public int getAccountNo() { return accountNo; }
    public String getName() { return name; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
}
