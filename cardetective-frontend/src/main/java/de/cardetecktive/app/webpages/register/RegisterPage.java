package de.cardetecktive.app.webpages.register;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class RegisterPage extends DynamicWebPage {

    private final SelenideElement REGISTER_PAGE_TITLE = $(By.xpath("//h1[@class='register__title']"));

    private final SelenideElement EMAIL_REGISTER_INPUT = $(By.xpath("//input[@formcontrolname='email']"));
    private final SelenideElement BRANCH_SELECTOR = $(By.xpath("//mat-select[@formcontrolname='role']"));
    private final SelenideElement BRANCH_AUTOHOUSE_SELECTOR = $(By.xpath("//mat-option[contains(@class, 'role-option-1')]"));

    private final SelenideElement APP_CARDETEKTIV_DE_CHECKBOX = $(By.xpath("//mat-list-option[@value='app_cd']"));

    private final SelenideElement TERMS_AND_CONDITIONS_CHECKBOX = $(By.xpath("//mat-list-option[@value='agb']//mat-pseudo-checkbox"));
    private final SelenideElement SECRET_INPUT_FOR_AVOIDING_CAPTCHA = $(By.xpath("//input[contains(@class, 'secretfieldfortests')]"));
    private final SelenideElement DATA_PROTECTION_NOTICE_CHECKBOX = $(By.xpath("//mat-list-option[@value='privacy_politics']//mat-pseudo-checkbox"));

    private String email;

    public String getEmail() {
        return email;
    }

    public void registration(@NotNull final String email,
                             @NotNull final String secretFieldForTests) {
        this.email = email;
        EMAIL_REGISTER_INPUT.sendKeys(email);
        BRANCH_SELECTOR.click();
        BRANCH_AUTOHOUSE_SELECTOR.click();
        APP_CARDETEKTIV_DE_CHECKBOX.click();
        TERMS_AND_CONDITIONS_CHECKBOX.click();
        SECRET_INPUT_FOR_AVOIDING_CAPTCHA.sendKeys(secretFieldForTests);
        DATA_PROTECTION_NOTICE_CHECKBOX.click();
    }

    @Override
    public boolean isCurrentPage() {
        return REGISTER_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}

