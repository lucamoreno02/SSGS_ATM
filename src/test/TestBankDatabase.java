package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import code.database.BankDatabase;
import code.database.Euro;

public class TestBankDatabase {
    private static BankDatabase bd;
    private static int theAccountNumber = 12345;

    @BeforeEach
    public void setup() {
        bd = new BankDatabase();
        bd.authenticateUser(theAccountNumber, 54321);
    }

    @AfterEach
    public void tearDown() {
        bd = null;
    }

    @ParameterizedTest
    @MethodSource("test.TestBankDatabase#testGetAvailableBalance")
    void testGetAvailableBalance(int userAccountNumber, Euro euro, boolean result) {
        assertEquals(result, bd.getAvailableBalance(userAccountNumber).ugualeA(euro));
    }

    protected static Stream<Arguments> testGetAvailableBalance() {
        return Stream.of(
            Arguments.of(12345, new Euro(1000), true),
            Arguments.of(98765, new Euro(205), false)
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestBankDatabase#testCredit")
    void testCredit(Euro euro, Euro result) {
        bd.credit(theAccountNumber, euro);
        assertTrue(bd.getTotalBalance(theAccountNumber).ugualeA(result));
    }

    protected static Stream<Arguments> testCredit() {
        return Stream.of(
            Arguments.of(new Euro(300), new Euro(1500)),
            Arguments.of(new Euro(63, 97), new Euro(1263, 97))
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestBankDatabase#testDebit")
    void testDebit(Euro euro, Euro result) {
        bd.debit(theAccountNumber, euro);
        assertTrue(bd.getTotalBalance(theAccountNumber).ugualeA(result));
    }

    protected static Stream<Arguments> testDebit() {
        return Stream.of(
            Arguments.of(new Euro(300), new Euro(900)),
            Arguments.of(new Euro(6200, 50), new Euro(-5000, 50))
        );
    }
}
