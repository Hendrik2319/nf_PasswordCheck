package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void returnFalse_whenCheckLength_getsATooShortPassword() {
        // given
        String password = "#".repeat(Main.PasswordCheck.minLength-1);
        // when
        boolean actual = Main.PasswordCheck.checkLength(password);
        // then
        Assertions.assertFalse(actual);
    }
    @Test
    void returnTrue_whenCheckLength_getsALongEnoughPassword() {}
    @Test
    void returnFalse_whenCheckNumbers_getsAPasswordWithoutNumbers() {}
    @Test
    void returnTrue_whenCheckNumbers_getsAPasswordWithNumbers() {}
    @Test
    void returnFalse_whenCheckCase_getsAPasswordWithLowerCaseOnly() {}
    @Test
    void returnFalse_whenCheckCase_getsAPasswordWithUpperCaseOnly() {}
    @Test
    void returnTrue_whenCheckCase_getsAPasswordWithUpperAndLowerCase() {}
    @Test
    void returnFalse_whenCheckBadPW_getsABadPassword1() {}
    @Test
    void returnFalse_whenCheckBadPW_getsABadPassword2() {}
    @Test
    void returnFalse_whenCheckBadPW_getsABadPassword3() {}
    @Test
    void returnTrue_whenCheckBadPW_getsANotBadPassword() {}
}