package de.cardetecktive.app.webpages.forgotpassword;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class ForgotPasswordPage extends DynamicWebPage {

    private final SelenideElement FORGOT_PASSWORD_PAGE_TITLE = $(By.xpath("//h1[@class='forgot__title']"));

    private final SelenideElement EMAIL_INPUT = $(By.xpath("//input[contains(@class, 'mat-input-element')]"));

    private final SelenideElement RECOVER_PASSWORD_BUTTON = $(By.xpath("//button[contains(@class, 'mat-focus-indicator')]"));

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void forgotPasswordEmailInput(@NotNull final String userName) {
        this.userName = userName;
        EMAIL_INPUT.sendKeys(userName);
        RECOVER_PASSWORD_BUTTON.click();
    }

    @Override
    public boolean isCurrentPage() {
        return FORGOT_PASSWORD_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
