package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    static class PasswordCheck {
        static final String[] badPasswordList = { "passwort", "password", "pw", "none" };
        static final String allowedSpecialChars = ".,:;(){}[]+-*/!$%&=?<>";
        static final int minLength = 8;

        public static boolean checkLength(String password) {
            return false;
        }

        public static boolean checkNumbers(String password) {
            return false;
        }

        public static boolean checkCase(String password) {
            return false;
        }

        public static boolean checkBadPassword(String password) {
            return false;
        }

        public static boolean checkSpecialChars(String password) {
            return false;
        }
    }
}