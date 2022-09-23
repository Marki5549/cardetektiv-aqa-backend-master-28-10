package de.cardetecktive.app.webpages.forgotpassword;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class PasswordIsChangedPage extends DynamicWebPage {

    private final SelenideElement PASSWORD_IS_CHANGED_PAGE_TITLE = $(By.xpath("//p[@class='forgot__text']"));

    private final SelenideElement SIGN_IN_CHANGE_PASSWORD_BUTTON = $(By.xpath("//button[@routerlink='/login']"));

    public void loginAfterPasswordRecovery() {
        SIGN_IN_CHANGE_PASSWORD_BUTTON.click();
    }

    @Override
    public boolean isCurrentPage() {
        return PASSWORD_IS_CHANGED_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
