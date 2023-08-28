package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    static class PasswordCheck {
        static final String[] badPasswordList = { "passwort", "password", "pw", "none" };
        static final int minLength = 8;

        public static boolean checkLength(String password) {
            return true;
        }
    }
}