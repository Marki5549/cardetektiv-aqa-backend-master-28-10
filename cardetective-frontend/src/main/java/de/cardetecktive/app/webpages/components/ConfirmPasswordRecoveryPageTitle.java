package de.cardetecktive.app.webpages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class ConfirmPasswordRecoveryPageTitle extends PageComponent {

    @Override
    public SelenideElement elementContainer() {
        return $(By.xpath("//p[@class='forgot__text']"));
    }
}
