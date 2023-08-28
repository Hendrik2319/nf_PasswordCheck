package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}