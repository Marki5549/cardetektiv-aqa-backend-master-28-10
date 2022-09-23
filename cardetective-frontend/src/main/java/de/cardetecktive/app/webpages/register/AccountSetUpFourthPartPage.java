package de.cardetecktive.app.webpages.register;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class AccountSetUpFourthPartPage extends DynamicWebPage {

    private final SelenideElement ACCOUNT_SET_UP_FOURTH_PART_PAGE_TITLE = $(By.xpath("//h1[contains(@class, 'setup__title')]"));

    private final SelenideElement SIGN_IN_FOURTH_PAGE_BUTTON = $(By.xpath("//button[@routerlink='/login']"));

    public void signInAfterRegistration() {
        SIGN_IN_FOURTH_PAGE_BUTTON.click();
    }

    @Override
    public boolean isCurrentPage() {
        return ACCOUNT_SET_UP_FOURTH_PART_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
