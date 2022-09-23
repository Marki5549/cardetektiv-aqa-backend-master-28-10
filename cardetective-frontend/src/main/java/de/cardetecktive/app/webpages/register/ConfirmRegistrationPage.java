package de.cardetecktive.app.webpages.register;

import com.codeborne.selenide.Condition;
import de.cardetecktive.app.webpages.DynamicWebPage;
import de.cardetecktive.app.webpages.components.ConfirmRegistrationPageTitle;
import org.springframework.stereotype.Component;

@Component
public class ConfirmRegistrationPage extends DynamicWebPage {

    private final ConfirmRegistrationPageTitle confirmRegistrationPageTitle;

    public ConfirmRegistrationPage(ConfirmRegistrationPageTitle confirmRegistrationPageTitle) {
        this.confirmRegistrationPageTitle = confirmRegistrationPageTitle;
    }

    @Override
    public boolean isCurrentPage() {
        return confirmRegistrationPageTitle.elementContainer()
                .shouldBe(Condition.appear)
                .exists();
    }
}
