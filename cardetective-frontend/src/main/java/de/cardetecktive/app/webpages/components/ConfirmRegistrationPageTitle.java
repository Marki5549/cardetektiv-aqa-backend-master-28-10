package de.cardetecktive.app.webpages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class ConfirmRegistrationPageTitle extends PageComponent {

    @Override
    public SelenideElement elementContainer() {
        return $(By.xpath("//h1[@class='register__title']"));
    }
}
