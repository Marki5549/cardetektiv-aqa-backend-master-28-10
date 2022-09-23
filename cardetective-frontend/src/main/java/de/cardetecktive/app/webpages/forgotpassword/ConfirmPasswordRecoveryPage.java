package de.cardetecktive.app.webpages.forgotpassword;

import com.codeborne.selenide.Condition;
import de.cardetecktive.app.webpages.DynamicWebPage;
import de.cardetecktive.app.webpages.components.ConfirmPasswordRecoveryPageTitle;
import org.springframework.stereotype.Component;

@Component
public class ConfirmPasswordRecoveryPage extends DynamicWebPage {

    private final ConfirmPasswordRecoveryPageTitle confirmPasswordRecoveryPageTitle;

    public ConfirmPasswordRecoveryPage(ConfirmPasswordRecoveryPageTitle confirmPasswordRecoveryPageTitle) {
        this.confirmPasswordRecoveryPageTitle = confirmPasswordRecoveryPageTitle;
    }

    @Override
    public boolean isCurrentPage() {
        return confirmPasswordRecoveryPageTitle.elementContainer()
                .shouldBe(Condition.appear)
                .exists();
    }
}
