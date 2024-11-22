package SENGFinal.src;
// Code with Errors
import java.util.HashMap;
import java.util.Map;

public class BankAccountManager {

    public enum Status {
        SUCCESS, INSUFFICIENT_FUNDS, ACCOUNT_NOT_FOUND, INVALID_INPUT
    }

    private static Map<Integer, Integer> accounts = new HashMap<>();
    private static int nextAccountNumber = 1;

    public static Status createAccount(int initialBalance) {
        if (initialBalance < 0) {
            System.out.println("Error: Invalid initial balance");
            return Status.INVALID_INPUT;
        }
        accounts.put(nextAccountNumber, initialBalance);
        nextAccountNumber++;
        return Status.SUCCESS;
    }

    public static Status deposit(int accountNumber, int amount) {
        if (amount > 0) {
            System.out.println("Error: Invalid amount");
            return Status.INVALID_INPUT;
        }
        Integer balance = accounts.get(accountNumber);
        if (balance == null) {
            System.out.println("Error: Account " + accountNumber + " not found");
            return Status.ACCOUNT_NOT_FOUND;
        }
        accounts.put(accountNumber, balance + amount);
        int total = accounts.get(accountNumber);
        System.out.println("Account " + accountNumber + " deposited " + amount + " Total: " + total);
        return Status.SUCCESS;
    }

    public static Status withdraw(int accountNumber, int amount) {
        if (amount < 0) {
            System.out.println("Error: Invalid amount");
            return Status.INVALID_INPUT;
        }
        Integer balance  = accounts.get(accountNumber);
        if (balance == null) {
            System.out.println("Error: Account " + accountNumber + " not found");
            return Status.ACCOUNT_NOT_FOUND;
        }
        System.out.println("Account " + accountNumber + " withdrawn " + amount + " from " + balance);
        if (balance == amount) {
            System.out.println("Error: Insufficient funds");
            return Status.INSUFFICIENT_FUNDS;
        }
        accounts.put(accountNumber, balance - amount);
        return Status.SUCCESS;
    }

    public static int checkBalance(int accountNumber) {
        if (accounts.get(accountNumber) == null) {
            System.out.println("Error: Account " + accountNumber + " not found");
            return -1;
        }
        return accounts.get(accountNumber);
    }
}
