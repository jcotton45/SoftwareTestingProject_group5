package SENGFinal.test;

import SENGFinal.src.BankAccountManager.*;
import SENGFinal.src.BankAccountManager;

import static SENGFinal.src.BankAccountManager.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankAccountManagerTest {

    private static Stream<Arguments> createAccountTestData() {
        return Stream.of(
                Arguments.of("T1.1.1", -400, Status.INVALID_INPUT),
                Arguments.of("T1.1.2", 1000, Status.SUCCESS),
                Arguments.of("T2.1.1", Integer.MIN_VALUE, Status.INVALID_INPUT),
                Arguments.of("T2.1.2", -1, Status.INVALID_INPUT),
                Arguments.of("T2.1.3", 0, Status.SUCCESS),
                Arguments.of("T2.1.4", Integer.MAX_VALUE, Status.SUCCESS)
        );
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("createAccountTestData")
    public void testCreateAccount(String id, int amount, Status expectedStatus) {
        assertEquals(expectedStatus, createAccount(amount));
    }

    private static Stream<Arguments> depositTestData() {
        return Stream.of(
                Arguments.of("T1.2.1", 1, -400, Status.INVALID_INPUT),
                Arguments.of("T1.2.2", 1, 5000, Status.SUCCESS),
                Arguments.of("T1.2.3", 999, 2500, Status.ACCOUNT_NOT_FOUND),
                Arguments.of("T2.2.1", 1, Integer.MIN_VALUE, Status.INVALID_INPUT),
                Arguments.of("T2.2.2", Integer.MAX_VALUE, 0, Status.ACCOUNT_NOT_FOUND),
                Arguments.of("T2.2.3", -1, 1, Status.ACCOUNT_NOT_FOUND),
                Arguments.of("T2.2.4", 2, Integer.MAX_VALUE, Status.SUCCESS)
        );
    }

    @ParameterizedTest
    @Order(2)
    @MethodSource("depositTestData")
    public void testDeposit(String id, int accountNumber, int amount, Status expectedStatus) {
        assertEquals(expectedStatus, deposit(accountNumber, amount));
    }

    private static Stream<Arguments> withdrawTestData() {
        return Stream.of(
                Arguments.of("T1.3.1", 1, -400, Status.INVALID_INPUT),
                Arguments.of("T1.3.2", 1, 2500, Status.SUCCESS),
                Arguments.of("T1.3.3", 1, 50000, Status.INSUFFICIENT_FUNDS),
                Arguments.of("T1.3.4", 999, 2500, Status.ACCOUNT_NOT_FOUND),
                Arguments.of("T2.3.1", 1, Integer.MIN_VALUE, Status.INVALID_INPUT),
                Arguments.of("T2.3.2", Integer.MAX_VALUE, 0, Status.ACCOUNT_NOT_FOUND),
                Arguments.of("T2.3.3", -1, 1, Status.ACCOUNT_NOT_FOUND),
                Arguments.of("T2.3.4", 3, Integer.MAX_VALUE, Status.SUCCESS),
                Arguments.of("T2.3.5", 1, Integer.MAX_VALUE, Status.INSUFFICIENT_FUNDS)
        );
    }

    @ParameterizedTest
    @Order(3)
    @MethodSource("withdrawTestData")
    public void testWithdraw(String id, int accountNumber, int amount, Status expectedStatus) {
        assertEquals(expectedStatus, withdraw(accountNumber, amount));
    }

    private static Stream<Arguments> checkBalanceTestData() {
        return Stream.of(
                Arguments.of("T1.4.1", 999, -1),
                Arguments.of("1.4.2", 1, 3500)
        );
    }

    @ParameterizedTest
    @Order(4)
    @MethodSource("checkBalanceTestData")
    public void testCheckBalance(String id, int accountNumber, int expectedStatus) {
        assertEquals(expectedStatus, checkBalance(accountNumber));
    }
}
