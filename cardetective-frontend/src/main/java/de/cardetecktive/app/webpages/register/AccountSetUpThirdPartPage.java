package de.cardetecktive.app.webpages.register;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class AccountSetUpThirdPartPage extends DynamicWebPage {

    private final SelenideElement ACCOUNT_SET_UP_THIRD_PART_PAGE_TITLE = $(By.xpath("//h1[contains(@class, 'step-title-3')]"));

    private final SelenideElement HONORIFIC_SELECTOR = $(By.xpath("//mat-select[@id='mat-select-2']"));
    private final SelenideElement HONORIFIC_MR_SELECTOR = $(By.xpath("//mat-option[@id='mat-option-16']"));

    private final SelenideElement PHONE_INPUT = $(By.xpath("//input[@id='mat-input-4']"));

    private final SelenideElement WHERE_DID_YOU_HEAR_ABOUT_US_INPUT = $(By.xpath("//input[@id='mat-input-5']"));

    private final SelenideElement DONE_SET_UP_THIRD_PAGE_BUTTON = $(By.xpath("//button[contains(@class, 'step-button-3')]"));

    public void accountSetUpThirdPart(@NotNull final String phone,
                                      @NotNull final String whereDidYouHearAboutUs) {
        HONORIFIC_SELECTOR.click();
        HONORIFIC_MR_SELECTOR.click();
        PHONE_INPUT.sendKeys(phone);
        WHERE_DID_YOU_HEAR_ABOUT_US_INPUT.sendKeys(whereDidYouHearAboutUs);
        DONE_SET_UP_THIRD_PAGE_BUTTON.click();
    }

    @Override
    public boolean isCurrentPage() {
        return ACCOUNT_SET_UP_THIRD_PART_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
