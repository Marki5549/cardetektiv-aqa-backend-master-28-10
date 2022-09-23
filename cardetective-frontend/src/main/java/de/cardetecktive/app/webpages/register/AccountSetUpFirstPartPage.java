package de.cardetecktive.app.webpages.register;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class AccountSetUpFirstPartPage extends DynamicWebPage {

    private final SelenideElement ACCOUNT_SET_UP_FIRST_PART_PAGE_TITLE = $(By.xpath("//h1[contains(@class, 'step-title-1')]"));

    private final SelenideElement FIRST_NAME_INPUT = $(By.xpath("//input[@id='mat-input-0']"));
    private final SelenideElement LAST_NAME_INPUT = $(By.xpath("//input[@id='mat-input-1']"));
    private final SelenideElement CONTINUE_SET_UP_FIRST_PAGE_BUTTON = $(By.xpath("//button[contains(@class, 'step-button-1')]"));

    public void accountSetUpFirstPart(@NotNull final String firstName,
                                      @NotNull final String lastName) {
        FIRST_NAME_INPUT.sendKeys(firstName);
        LAST_NAME_INPUT.sendKeys(lastName);
        CONTINUE_SET_UP_FIRST_PAGE_BUTTON.click();
    }

    @Override
    public boolean isCurrentPage() {
        return ACCOUNT_SET_UP_FIRST_PART_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
