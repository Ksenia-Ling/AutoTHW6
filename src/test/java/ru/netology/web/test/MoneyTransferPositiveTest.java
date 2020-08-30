package ru.netology.web.test;


import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferPositiveTest {


    @BeforeEach
    void shouldVerify() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getValidAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataForTests.csv")
    void shouldTransferFromSecondToFirstCard(int transferAmount) {

        val dashboardPage = new DashboardPage();
        int startBalanceOfFirstCard = dashboardPage.getFirstCardBalance();
        int startBalanceOfSecondCard = dashboardPage.getSecondCardBalance();

        val transferPage = dashboardPage.topUpFirstCard();
        val transferFromSecondToFirst = DataHelper.getSecondCardInfo();
        transferPage.transferFromSecondCard(transferFromSecondToFirst, transferAmount);
        val firstCardBalance = DataHelper.getBalanceAfterReplenish(startBalanceOfFirstCard, transferAmount);
        val secondCardBalance = DataHelper.getBalanceAfterDebiting(startBalanceOfSecondCard, transferAmount);

        assertEquals(firstCardBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalance, dashboardPage.getSecondCardBalance());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataForTests.csv")
    void shouldTransferFromFirstToSecondCard(int transferAmount) {

        val dashboardPage = new DashboardPage();
        int startBalanceOfFirstCard = dashboardPage.getFirstCardBalance();
        int startBalanceOfSecondCard = dashboardPage.getSecondCardBalance();

        val transferPage = dashboardPage.topUpSecondCard();
        val transferFromFirstToSecond = DataHelper.getFirstCardInfo();
        transferPage.transferFromFirstCard(transferFromFirstToSecond, transferAmount);
        val firstCardBalance = DataHelper.getBalanceAfterDebiting(startBalanceOfFirstCard, transferAmount);
        val secondCardBalance = DataHelper.getBalanceAfterReplenish(startBalanceOfSecondCard, transferAmount);

        assertEquals(firstCardBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalance, dashboardPage.getSecondCardBalance());
    }
}
