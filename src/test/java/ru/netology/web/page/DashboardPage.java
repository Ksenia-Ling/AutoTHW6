package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCardData = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCardData = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement topUpFirstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] [role='button']");
    private SelenideElement topUpSecondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] [role='button']");

    private final String startBalance = "баланс: ";
    private final String finishBalance = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage topUpFirstCard() {
        topUpFirstCardButton.click();
        return new TransferPage();
    }

    public TransferPage topUpSecondCard() {
        topUpSecondCardButton.click();
        return new TransferPage();
    }

    public int getFirstCardBalance() {
        String firstValue = firstCardData.text();
        return findOutBalance(firstValue);
    }

    public int getSecondCardBalance() {
        String secondValue = secondCardData.text();
        return findOutBalance(secondValue);
    }

    private int findOutBalance(String text) {
        val start = text.indexOf(startBalance);
        val finish = text.indexOf(finishBalance);
        val value = text.substring(start + startBalance.length(), finish);
        return Integer.parseInt(value);
    }
}

