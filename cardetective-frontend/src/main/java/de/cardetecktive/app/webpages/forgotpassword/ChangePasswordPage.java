package de.cardetecktive.app.webpages.forgotpassword;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class ChangePasswordPage extends DynamicWebPage {

    private final SelenideElement CHANGE_PASSWORD_PAGE_TITLE = $(By.xpath("//h1[contains(@class, 'reset-password__title')]"));

    private final SelenideElement PASSWORD_INPUT = $(By.xpath("//input[@formcontrolname='password']"));
    private final SelenideElement REPEAT_YOUR_NEW_PASSWORD_INPUT = $(By.xpath("//input[@formcontrolname='repeatedPassword']"));

    private final SelenideElement RESET_PASSWORD_BUTTON = $(By.xpath("//button[contains(@class, 'forgot__button')]"));

    private String password;

    public String getPassword() {
        return password;
    }

    public void changePassword(@NotNull final String password) {
        this.password = password;
        PASSWORD_INPUT.sendKeys(password);
        REPEAT_YOUR_NEW_PASSWORD_INPUT.sendKeys(password);
        RESET_PASSWORD_BUTTON.click();
    }

    @Override
    public boolean isCurrentPage() {
        return CHANGE_PASSWORD_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
