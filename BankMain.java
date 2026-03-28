package bank.main;

import bank.dao.AccountDAO;
import bank.model.Account;

import java.util.*;

public class BankMain {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        AccountDAO dao = new AccountDAO();

        while (true) {

            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Update Account");
            System.out.println("4. Delete Account");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Account Type: ");
                    String type = sc.nextLine();
                    System.out.print("Initial Balance: ");
                    double balance = sc.nextDouble();
                    dao.createAccount(new Account(name, type, balance));
                    break;

                case 2:
                    for (Account a : dao.getAllAccounts()) {
                        System.out.println(a.getAccountNo()+" "+
                                a.getName()+" "+
                                a.getAccountType()+" "+
                                a.getBalance());
                    }
                    break;

                case 3:
                    System.out.print("Account No: ");
                    int accNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    name = sc.nextLine();
                    System.out.print("Type: ");
                    type = sc.nextLine();
                    System.out.print("Balance: ");
                    balance = sc.nextDouble();
                    dao.updateAccount(new Account(accNo, name, type, balance));
                    break;

                case 4:
                    System.out.print("Account No: ");
                    dao.deleteAccount(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Account No: ");
                    accNo = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    dao.deposit(accNo, amt);
                    break;

                case 6:
                    System.out.print("Account No: ");
                    accNo = sc.nextInt();
                    System.out.print("Amount: ");
                    amt = sc.nextDouble();
                    dao.withdraw(accNo, amt);
                    break;

                case 7:
                	sc.close();
                    System.exit(0);
            }
        }
    }
}
