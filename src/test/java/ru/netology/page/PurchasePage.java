package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInf;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PurchasePage {
    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardNumberFieldError = $x("//*[text()='Номер карты']/..//*[@class='input__sub']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement monthFieldError = $x("//*[text()='Месяц']/..//*[@class='input__sub']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement yearFieldError = $x("//*[text()='Год']/..//*[@class='input__sub']");
    private final ElementsCollection fieldSet = $$(".input__control");
    private final SelenideElement holderField = fieldSet.get(3);
    private final SelenideElement ownerField = $(byText("Владелец")).parent().$("input");
    private final SelenideElement ownerFieldError = $x("//*[text()='Владелец']/..//*[@class='input__sub']");
    private final SelenideElement cvcField = $("input[placeholder='999']");
    private final SelenideElement cvcFieldError = $x("//*[text()='CVC/CVV']/..//*[@class='input__sub']");
    private final SelenideElement notificationSuccessfully = $(".notification_status_ok");
    private final SelenideElement notificationError = $(".notification_status_error");
    private final SelenideElement continueButton = $("form button");


    public void completedPaymentForm(CardInf.CardInfo cardData) {
        cardNumberField.setValue(cardData.getNumberCard());
        monthField.setValue(cardData.getMonth());
        yearField.setValue(cardData.getYear());
        ownerField.setValue(cardData.getOwner());
        cvcField.setValue(cardData.getCvc());
        continueButton.click();
    }

    public void waitSuccessResult() {
        notificationSuccessfully.shouldBe(Condition.visible);
    }

    public void waitInvalidCardExpirationDate() {
        yearFieldError.shouldBe(Condition.visible);
    }

    public void waitIncorrectFormat() {
        cardNumberFieldError.shouldBe(Condition.visible);
    }

    public void waitError() {
        notificationError.shouldBe(Condition.visible);
    }

    public void waitCardExpired() {
        monthFieldError.shouldBe(Condition.visible);
    }

    public void waitThisFieldIsRequired() {
        ownerFieldError.shouldBe(Condition.visible);
    }

    public void waitIncorrectCVC() {
        cvcFieldError.shouldBe(Condition.visible);
    }

    public void emptyForm() {
        continueButton.click();
        cardNumberFieldError.shouldBe(Condition.visible);
        monthFieldError.shouldBe(Condition.visible);
        yearFieldError.shouldBe(Condition.visible);
        ownerFieldError.shouldBe(Condition.visible);
        cvcFieldError.shouldBe(Condition.visible);
    }
}

