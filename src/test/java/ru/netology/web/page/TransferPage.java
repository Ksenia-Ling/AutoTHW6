package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement transferAmount = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransferPage() {
        fromField.shouldBe(Condition.visible);
    }

    public DashboardPage transferFromSecondCard(DataHelper.CreditCardInfo info, int amount) {
        transferAmount.setValue(String.valueOf(amount));
        fromField.setValue(info.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage transferFromFirstCard(DataHelper.CreditCardInfo info, int amount) {
        transferAmount.setValue(String.valueOf(amount));
        fromField.setValue(info.getNumber());
        transferButton.click();
        return new DashboardPage();
    }
}
