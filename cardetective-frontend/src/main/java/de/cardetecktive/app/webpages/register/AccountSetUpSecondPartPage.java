package de.cardetecktive.app.webpages.register;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class AccountSetUpSecondPartPage extends DynamicWebPage {

    private final SelenideElement ACCOUNT_SET_UP_SECOND_PART_PAGE_TITLE = $(By.xpath("//h1[contains(@class, 'step-title-2')]"));

    private final SelenideElement NAME_OF_BUSINESS_INPUT = $(By.xpath("//input[@id='mat-input-2']"));
    private final SelenideElement PLZ_INPUT = $(By.xpath("//input[@id='mat-input-3']"));
    private final SelenideElement COUNTRY_SELECTOR = $(By.xpath("//mat-select[@id='mat-select-1']"));
    private final SelenideElement COUNTRY_AUSTRIA_SELECTOR = $(By.xpath("//mat-option[@id='mat-option-0']"));
    private final SelenideElement CONTINUE_SET_UP_SECOND_PAGE_BUTTON = $(By.xpath("//button[contains(@class, 'step-button-2')]"));

    public void accountSetUpSecondPart(@NotNull final String nameOfBusiness,
                                       @NotNull final String plz) {
        NAME_OF_BUSINESS_INPUT.sendKeys(nameOfBusiness);
        PLZ_INPUT.sendKeys(plz);
        COUNTRY_SELECTOR.click();
        COUNTRY_AUSTRIA_SELECTOR.click();
        CONTINUE_SET_UP_SECOND_PAGE_BUTTON.click();
    }

    @Override
    public boolean isCurrentPage() {
        return ACCOUNT_SET_UP_SECOND_PART_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
