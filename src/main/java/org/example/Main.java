package org.example;

import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String[] args) {
        // nothing
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
    }
}