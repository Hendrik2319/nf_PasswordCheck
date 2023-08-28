package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

class MainTest {

    @Test
    void returnFalse_whenCheckLength_getsATooShortPassword() {
        // given
        String password = "#".repeat(Main.PasswordCheck.MIN_LENGTH -1);
        // when
        boolean actual = Main.PasswordCheck.checkLength(password);
        // then
        Assertions.assertFalse(actual);
    }
    @Test
    void returnTrue_whenCheckLength_getsALongEnoughPassword() {
        // given
        String password = "#".repeat(Main.PasswordCheck.MIN_LENGTH);
        // when
        boolean actual = Main.PasswordCheck.checkLength(password);
        // then
        Assertions.assertTrue(actual);
    }
    @Test
    void returnFalse_whenCheckNumbers_getsAPasswordWithoutNumbers() {
        // given
        String password = "abcde";
        // when
        boolean actual = Main.PasswordCheck.checkNumbers(password);
        // then
        Assertions.assertFalse(actual);
    }
    @Test
    void returnTrue_whenCheckNumbers_getsAPasswordWithNumbers() {
        // given
        String password = "abcde123";
        // when
        boolean actual = Main.PasswordCheck.checkNumbers(password);
        // then
        Assertions.assertTrue(actual);
    }
    @Test
    void returnFalse_whenCheckCase_getsAPasswordWithLowerCaseOnly() {
        // given
        String password = "abcde";
        // when
        boolean actual = Main.PasswordCheck.checkCase(password);
        // then
        Assertions.assertFalse(actual);
    }
    @Test
    void returnFalse_whenCheckCase_getsAPasswordWithUpperCaseOnly() {
        // given
        String password = "ABCDE";
        // when
        boolean actual = Main.PasswordCheck.checkCase(password);
        // then
        Assertions.assertFalse(actual);
    }
    @Test
    void returnTrue_whenCheckCase_getsAPasswordWithUpperAndLowerCase() {
        // given
        String password = "AbcdE";
        // when
        boolean actual = Main.PasswordCheck.checkCase(password);
        // then
        Assertions.assertTrue(actual);
    }
    @Test
    void returnFalse_whenCheckBadPW_getsABadPassword1() {
        // given
        String password = Main.PasswordCheck.BAD_PASSWORD_LIST[0];
        // when
        boolean actual = Main.PasswordCheck.checkBadPassword(password);
        // then
        Assertions.assertFalse(actual);
    }
    @Test
    void returnFalse_whenCheckBadPW_getsABadPassword2() {
        // given
        String password = "12345";
        // when
        boolean actual = Main.PasswordCheck.checkBadPassword(password);
        // then
        Assertions.assertFalse(actual);
    }
    @Test
    void returnTrue_whenCheckBadPW_getsANotBadPassword() {
        // given
        String password = "AGoodPassword";
        // when
        boolean actual = Main.PasswordCheck.checkBadPassword(password);
        // then
        Assertions.assertTrue(actual);
    }
    @Test
    void returnFalse_whenCheckSpecialChars_getsAPasswordWithoutSpecialChars() {
        // given
        String password = "abcde";
        // when
        boolean actual = Main.PasswordCheck.checkSpecialChars(password);
        // then
        Assertions.assertFalse(actual);
    }
    @Test
    void returnTrue_whenCheckSpecialChars_getsAPasswordWithSpecialChars() {
        // given
        String password = "abcde(){}";
        // when
        boolean actual = Main.PasswordCheck.checkSpecialChars(password);
        // then
        Assertions.assertTrue(actual);
    }
    @Test
    void returnAllTrue_whenAllChecks_appliedToGeneratedPasswords() {
        for (int i=0; i<5; i++)
            testGeneratedPassword(Main.PasswordCheck.generatePassword());
    }
    private void testGeneratedPassword(String password) {
        // given
        //   -> password
        // when
        boolean actualHasCorrectLength = Main.PasswordCheck.checkLength      (password);
        boolean actualHasNumbers       = Main.PasswordCheck.checkNumbers     (password);
        boolean actualHasCorrectCases  = Main.PasswordCheck.checkCase        (password);
        boolean actualIsNotABadPW      = Main.PasswordCheck.checkBadPassword (password);
        boolean actualHasSpecialChars  = Main.PasswordCheck.checkSpecialChars(password);
        // then
        Assertions.assertTrue(actualHasCorrectLength, String.format("Test: %s, Tested Password: \"%s\"", "Length"      , password));
        Assertions.assertTrue(actualHasNumbers      , String.format("Test: %s, Tested Password: \"%s\"", "Numbers"     , password));
        Assertions.assertTrue(actualHasCorrectCases , String.format("Test: %s, Tested Password: \"%s\"", "Case"        , password));
        Assertions.assertTrue(actualIsNotABadPW     , String.format("Test: %s, Tested Password: \"%s\"", "BadPassword" , password));
        Assertions.assertTrue(actualHasSpecialChars , String.format("Test: %s, Tested Password: \"%s\"", "SpecialChars", password));
    }

    static class StaticGeneratedPasswords implements ArgumentsProvider {
        private static final String[] passwords = generate(5);

        @SuppressWarnings("SameParameterValue")
        private static String[] generate(int amount) {
            String[] strs = new String[amount];
            //strs[0] = "abcdef123";
            for (int i=0; i<amount; i++)
                strs[i] = Main.PasswordCheck.generatePassword();
            return strs;
        }

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            //System.out.printf( "StaticGeneratedPasswords.provideArguments( %s )%n", Arrays.toString(passwords) );
            return Stream
                    .of(passwords)
                    .map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(StaticGeneratedPasswords.class)
    void returnTrue_whenCheckLength_getsAGeneratedPassword(String password) {
        Assertions.assertTrue(Main.PasswordCheck.    checkLength    (password));
    }

    @ParameterizedTest
    @ArgumentsSource(StaticGeneratedPasswords.class)
    void returnTrue_whenCheckNumbers_getsAGeneratedPassword(String password) {
        Assertions.assertTrue(Main.PasswordCheck.    checkNumbers    (password));
    }

    @ParameterizedTest
    @ArgumentsSource(StaticGeneratedPasswords.class)
    void returnTrue_whenCheckCase_getsAGeneratedPassword(String password) {
        Assertions.assertTrue(Main.PasswordCheck.    checkCase    (password));
    }

    @ParameterizedTest
    @ArgumentsSource(StaticGeneratedPasswords.class)
    void returnTrue_whenCheckBadPassword_getsAGeneratedPassword(String password) {
        Assertions.assertTrue(Main.PasswordCheck.    checkBadPassword    (password));
    }

    @ParameterizedTest
    @ArgumentsSource(StaticGeneratedPasswords.class)
    void returnTrue_whenCheckSpecialChars_getsAGeneratedPassword(String password) {
        Assertions.assertTrue(Main.PasswordCheck.    checkSpecialChars    (password));
    }

}