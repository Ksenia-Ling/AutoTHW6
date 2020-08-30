package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferNegativeTest {

    @Test
    void shouldNotVerify() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(authInfo);
    }

    @Test
    void shouldTransferMoreThanExist() {

        int transferAmount = 100000;

        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getValidAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val dashboardPage = new DashboardPage();
        int startBalanceOfFirstCard = dashboardPage.getFirstCardBalance();
        int startBalanceOfSecondCard = dashboardPage.getSecondCardBalance();

        val transferPage = dashboardPage.topUpSecondCard();
        val transferFromFirst = DataHelper.getFirstCardInfo();
        transferPage.transferFromFirstCard(transferFromFirst, transferAmount);
        val balanceFirstCardAfterTrans = DataHelper.getBalanceAfterDebiting(startBalanceOfFirstCard, transferAmount);
        val balanceSecondCardAfterTrans = DataHelper.getBalanceAfterReplenish(startBalanceOfSecondCard, transferAmount);

        assertEquals(balanceFirstCardAfterTrans, dashboardPage.getFirstCardBalance());
        assertEquals(balanceSecondCardAfterTrans, dashboardPage.getSecondCardBalance());
    }
}
