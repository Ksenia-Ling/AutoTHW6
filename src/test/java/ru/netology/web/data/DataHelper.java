package ru.netology.web.data;

import lombok.Value;


public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("petya", "qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CreditCardInfo {
        private String number;

    }

    public static CreditCardInfo getFirstCardInfo() {
        return new CreditCardInfo("5559 0000 0000 0001");
    }

    public static CreditCardInfo getSecondCardInfo() {
        return new CreditCardInfo("5559 0000 0000 0002");
    }

    public static int getBalanceAfterReplenish(int balance, int transferAmount) {
        int cardBalancePlus = balance + transferAmount;
        return cardBalancePlus;
    }

    public static int getBalanceAfterDebiting(int balance, int transferAmount) {
        int cardBalanceMinus = balance - transferAmount;
        if (cardBalanceMinus < 0) {
            return balance;
        }
        return cardBalanceMinus;
    }

}
