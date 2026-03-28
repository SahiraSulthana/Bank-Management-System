package bank.dao;

import bank.model.Account;
import common.DBConnection;

import java.sql.*;
import java.util.*;

public class AccountDAO {

    // CREATE
    public void createAccount(Account acc) throws Exception {

        String sql = "INSERT INTO accounts(name,account_type,balance) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection("jdbc_bank");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, acc.getName());
            ps.setString(2, acc.getAccountType());
            ps.setDouble(3, acc.getBalance());
            ps.executeUpdate();

            System.out.println("Account Created Successfully!");
        }
    }

    // READ ALL
    public List<Account> getAllAccounts() throws Exception {

        List<Account> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection("jdbc_bank");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM accounts")) {

            while (rs.next()) {
                list.add(new Account(
                        rs.getInt("account_no"),
                        rs.getString("name"),
                        rs.getString("account_type"),
                        rs.getDouble("balance")));
            }
        }
        return list;
    }

    // UPDATE
    public void updateAccount(Account acc) throws Exception {

        String sql = "UPDATE accounts SET name=?,account_type=?,balance=? WHERE account_no=?";

        try (Connection con = DBConnection.getConnection("jdbc_bank");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, acc.getName());
            ps.setString(2, acc.getAccountType());
            ps.setDouble(3, acc.getBalance());
            ps.setInt(4, acc.getAccountNo());
            ps.executeUpdate();

            System.out.println("Account Updated Successfully!");
        }
    }

    // DELETE
    public void deleteAccount(int accountNo) throws Exception {

        try (Connection con = DBConnection.getConnection("jdbc_bank");
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM accounts WHERE account_no=?")) {

            ps.setInt(1, accountNo);
            ps.executeUpdate();

            System.out.println("Account Deleted Successfully!");
        }
    }

    // DEPOSIT (Transaction Safe)
    public void deposit(int accountNo, double amount) throws Exception {

        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_no=?";

        try (Connection con = DBConnection.getConnection("jdbc_bank");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, accountNo);
            ps.executeUpdate();

            System.out.println("Deposit Successful!");
        }
    }

    // WITHDRAW (Check balance first)
    public void withdraw(int accountNo, double amount) throws Exception {

        Connection con = DBConnection.getConnection("jdbc_bank");
        con.setAutoCommit(false);

        try {

            PreparedStatement check = con.prepareStatement(
                    "SELECT balance FROM accounts WHERE account_no=?");
            check.setInt(1, accountNo);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {

                double currentBalance = rs.getDouble("balance");

                if (currentBalance >= amount) {

                    PreparedStatement ps = con.prepareStatement(
                            "UPDATE accounts SET balance=balance-? WHERE account_no=?");
                    ps.setDouble(1, amount);
                    ps.setInt(2, accountNo);
                    ps.executeUpdate();

                    con.commit();
                    System.out.println("Withdrawal Successful!");

                } else {
                    System.out.println("Insufficient Balance!");
                }
            }

        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            con.close();
        }
    }
}

