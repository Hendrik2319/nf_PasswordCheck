package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        for (int i=0; i<10; i++)
            System.out.printf("GeneratedPassword[%02d] = %s%n", i+1, PasswordCheck.generatePassword());
    }

    static class PasswordCheck {
        static final String[] BAD_PASSWORD_LIST = { "passwort", "password", "pw", "none" };
        static final String ALLOWED_SPECIAL_CHARS = ".,:;(){}[]+-*/!$%&=?<>";
        static final int MIN_LENGTH = 8;

        public static boolean checkLength(String password) {
            return password!=null && password.length()>= MIN_LENGTH;
        }

        public static boolean checkNumbers(String password) {
            if (password!=null)
                for (char ch : password.toCharArray())
                    if (Character.isDigit(ch))
                        return true;
            return false;
        }

        public static boolean checkCase(String password) {
            if (password!=null) {
                boolean foundLowerCase = false;
                boolean foundUpperCase = false;
                for (char ch : password.toCharArray()) {
                    foundLowerCase |= Character.isLowerCase(ch);
                    foundUpperCase |= Character.isUpperCase(ch);
                    if (foundLowerCase && foundUpperCase)
                        return true;
                }
            }
            return false;
        }

        public static boolean checkBadPassword(String password) {
            if (password==null) return false;

            if (isAscendingSequence(password)) return false;
            if (isDescendingSequence(password)) return false;

            for (String badPW : BAD_PASSWORD_LIST)
                if (badPW.equalsIgnoreCase(password))
                    return false;

            return true;
        }

        private static boolean isAscendingSequence(@NotNull String password) {
            char[] chars = password.toCharArray();
            for (int i = 1; i < chars.length; i++)
                if (chars[i - 1] + 1 != chars[i])
                    return false;
            return true;
        }

        private static boolean isDescendingSequence(@NotNull String password) {
            char[] chars = password.toCharArray();
            for (int i = 1; i < chars.length; i++)
                if (chars[i - 1] - 1 != chars[i])
                    return false;
            return true;
        }

        public static boolean checkSpecialChars(String password) {
            if (password!=null)
                for (char ch : password.toCharArray())
                    if (ALLOWED_SPECIAL_CHARS.contains(""+ch))
                        return true;
            return false;
        }

        private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ";
        private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyzäöüß";
        private static final String DIGIT_CHARS = "0123456789";

        public static @NotNull String generatePassword() {
            Random rnd = new Random();

            // 2..10 chars more than min. length
            int length = MIN_LENGTH + 2 + rnd.nextInt(9);

            String rawPassword = "";
            rawPassword += createChunk(rnd, length/4, DIGIT_CHARS);
            rawPassword += createChunk(rnd, length/4, ALLOWED_SPECIAL_CHARS);
            rawPassword += createChunk(rnd, length/4, UPPERCASE_CHARS);
            rawPassword += createChunk(rnd, length-rawPassword.length(), LOWERCASE_CHARS);

            return scramble(rnd, rawPassword);
        }

        private static @NotNull String scramble(Random rnd, @NotNull String rawPassword) {
            Vector<Character> chars = rawPassword.chars().collect(
                    Vector::new,
                    (v,n) -> v.add((char)n),
                    Vector::addAll);

            StringBuilder result = new StringBuilder();
            while (!chars.isEmpty()) {
                int pos = rnd.nextInt(chars.size());
                char ch = chars.remove(pos);
                result.append(ch);
            }

            return result.toString();
        }

        private static @NotNull String createChunk(Random rnd, int length, String chars) {
            char[] chunk = new char[length];
            for (int i=0; i<length; i++)
                chunk[i] = chars.charAt(rnd.nextInt(chars.length()));
            return String.valueOf(chunk);
        }
    }
}