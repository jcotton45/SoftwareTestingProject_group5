package finance;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import finance.BankAccountManager.Status;

public class BankAccountManagerTest {
    private static Object[][] createAccountData = new Object[][]{
            {"T2.1.1", Integer.MIN_VALUE, Status.INVALID_INPUT},
            {"T2.1.2", -1, Status.INVALID_INPUT},
            {"T2.1.3", 0, Status.SUCCESS},
            {"T2.1.4", Integer.MAX_VALUE, Status.SUCCESS},
            {"T2.2.1", Integer.MIN_VALUE, Status.SUCCESS},
    };

    private static Object[][] depositData = new Object[][]{
            {"T2.2.1", 1, Integer.MIN_VALUE, Status.INVALID_INPUT},
            {"T2.2.2", Integer.MAX_VALUE, 0, Status.SUCCESS},
            {"T2.2.3", -1, 1, Status.ACCOUNT_NOT_FOUND},
            {"T2.2.4", 1, Integer.MAX_VALUE, Status.SUCCESS},
    };

    private static Object[][] withdrawData = new Object[][]{
            {"T2.3.1", 1, Integer.MIN_VALUE, Status.INVALID_INPUT},
            {"T2.3.2", Integer.MAX_VALUE, 0, Status.SUCCESS},
            {"T2.3.3", -1, 1, Status.ACCOUNT_NOT_FOUND},
            {"T2.3.4", 1, 0, Status.SUCCESS},
            {"T2.3.5", 1, Integer.MAX_VALUE, Status.INSUFFICIENT_FUNDS},
    };

    private static Object[][] checkBalanceData = new Object[][]{
            {"T2.4.1", 1, 0},
            {"T2.4.2", Integer.MAX_VALUE, Integer.MAX_VALUE},
            {"T2.4.3", -1, -1},
    };

    @DataProvider(name = "createAccountData")
    public Object[][] getCreateAccountData() {
        return createAccountData;
    }

    @DataProvider(name = "depositData")
    public Object[][] getDepositData() {
        return depositData;
    }

    @DataProvider(name = "withdrawData")
    public Object[][] getWithdrawData() {
        return withdrawData;
    }

    @DataProvider(name = "checkBalanceData")
    public Object[][] getCheckBalanceData() {
        return checkBalanceData;
    }

    @Test(dataProvider = "createAccountData")
    public void test_createAccount(String id, int initialBalance, Status expectedStatus) {
        assertEquals(BankAccountManager.createAccount(initialBalance), expectedStatus);

    }

    @Test(dataProvider = "depositData")
    public void test_deposit(String id, int accountNumber, int amount, Status expectedStatus) {
        assertEquals(BankAccountManager.deposit(accountNumber, amount), expectedStatus);
    }

    @Test(dataProvider = "withdrawData")
    public void test_withdraw(String id, int accountNumber, int amount, Status expectedStatus) {
        assertEquals(BankAccountManager.withdraw(accountNumber, amount), expectedStatus);
    }

    @Test(dataProvider = "checkBalanceData")
    public void test_checkBalance(String id, int accountNumber, int expectedBalance) {
        assertEquals(BankAccountManager.checkBalance(accountNumber), expectedBalance);
    }
}

