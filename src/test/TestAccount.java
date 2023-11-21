package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import code.database.Account;
import code.database.Euro;

public class TestAccount {
    private static Account account;
    private static int theAccountNumber = 12345;
    private static int thePin = 12345;

    @BeforeEach
    public void setup() {
        account = new Account(theAccountNumber, thePin, new Euro(10000, 50), new Euro(20000, 75));
    }

    @AfterEach
    public void tearDown() {
        account = null;
    }

    @ParameterizedTest
    @MethodSource("test.TestAccount#testValidatePIN")
    void testValidatePIN(int thePin, boolean result) {
        assertEquals(result, account.validatePIN(thePin));
    }

    protected static Stream<Arguments> testValidatePIN() {
        return Stream.of(
            Arguments.of(12345, true),
            Arguments.of(98765, false)
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestAccount#testGetAccountNumber")
    void testGetAccountNumber(int result) {
        assertEquals(result, account.getAccountNumber());
    }

    protected static Stream<Arguments> testGetAccountNumber() {
        return Stream.of(
            Arguments.of(12345)
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestAccount#testGetTotalBalance")
    void testGetTotalBalance(Euro euro, boolean result) {
        asserTrue(account.getTotalBalance().ugualeA(euro), result);
    }

    private void asserTrue(boolean ugualeA, boolean result) {
    }

    protected static Stream<Arguments> testGetTotalBalance() {
        return Stream.of(
            Arguments.of(new Euro(20000, 75), true),
            Arguments.of(new Euro(20399600, 75), false)
        );
    }
}
