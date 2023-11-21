package test;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import code.database.Euro;

public class TestEuro {
    private static Euro e;
    private static Euro e2;

    @BeforeAll
    public static void setup() {
        e2 = new Euro(100, 0);
    
    }

    @AfterAll
    public static void tearDown() {
        e = null;
        e2 = null;
    }

    @Test
    public void testCostruttore() {
        e = new Euro(100, 50);
        assertEquals(100, e.getEuro());
        assertEquals(50, e.getValore() - e.getEuro() * 100);
    }

    @ParameterizedTest
    @MethodSource("test.TestEuro#testGetEuro")
    public void testGetEuro(long euro, long cent, long result) {
        e = new Euro(euro, cent);
        assertEquals(result, e.getEuro());
    }

    protected static Stream<Arguments> testGetEuro() {
        return Stream.of(
            Arguments.of(50, 2, 50),
            Arguments.of(75, 200, 77)
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestEuro#testGetValore")
    void testGetValore(long euro, long cent, long result) {
        e = new Euro(euro, cent);
        assertEquals(result, e.getValore());
    }

    protected static Stream<Arguments> testGetValore() {
        return Stream.of(
            Arguments.of(50, 2, 5002),
            Arguments.of(75, 200, 7700)
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestEuro#testMinoreDi")
    void testMinoreDi(long euro, long cent, boolean result) {
        e = new Euro(euro, cent);
        assertEquals(result, e.minoreDi(e2));
    }

    protected static Stream<Arguments> testMinoreDi() {
        return Stream.of(
            Arguments.of(50, 2, true),
            Arguments.of(100, 0, true),
            Arguments.of(101, 25, false)
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestEuro#testSomma")
    void testSomma(long euro, long cent, long result) {
        e = new Euro(euro, cent);
        e.somma(e2);
        assertEquals(result, e.getEuro());
    }

    protected static Stream<Arguments> testSomma() {
        return Stream.of(
            Arguments.of(50, 2, 150),
            Arguments.of(100, 0, 200)
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestEuro#testSottrai")
    void testSottrai(long euro, long cent, long result) {
        e = new Euro(euro, cent);
        e.sottrai(e2);
        assertEquals(result, e.getEuro());
    }

    protected static Stream<Arguments> testSottrai() {
        return Stream.of(
            Arguments.of(50, 0, -50),
            Arguments.of(101, 25, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("test.TestEuro#testStampa")
    void testStampa(long euro, long cent, String result) {
        e = new Euro(euro, cent);
        assertEquals(result, e.stampa());
    }

    protected static Stream<Arguments> testStampa() {
        return Stream.of(
            Arguments.of(50, 0, "50.0 euro"),
            Arguments.of(101, 25, "101.25 euro")
        );
    }


    @ParameterizedTest
    @MethodSource("testUgualeA")
    void testUgualeA(long euro, long cent, boolean result) {
        e = new Euro(euro, cent);
        assertEquals(result, e.ugualeA(e2));
    }

    protected static Stream<Arguments> testUgualeA() {
        return Stream.of(
                Arguments.of(50, 0, false),
                Arguments.of(100, 0, true)
        );
    }
}
