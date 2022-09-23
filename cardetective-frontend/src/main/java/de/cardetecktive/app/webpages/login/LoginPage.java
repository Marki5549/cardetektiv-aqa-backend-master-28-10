package de.cardetecktive.app.webpages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.cardetecktive.app.utils.PostJsonWithHttpUrlConnection;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;

@Component
public class LoginPage extends DynamicWebPage {

    private final SelenideElement LOGIN_PAGE_TITLE = $(By.xpath("//h1[@class='login__title']"));

    private final SelenideElement EMAIL_INPUT = $(By.xpath("//input[@formcontrolname='username']"));
    private final SelenideElement PASSWORD_INPUT = $(By.xpath("//input[@formcontrolname='password']"));

    private final SelenideElement FORGOT_PASSWORD_BUTTON = $(By.xpath("//a[@routerlink='/forgot-password']"));

    private final SelenideElement SIGN_IN_BUTTON = $(By.xpath("//button[@type='submit']"));

    private final SelenideElement REGISTER_PAGE_BUTTON = $(By.xpath("//a[@routerlink='/register']"));

    private final SelenideElement LOGIN_ERROR = $(By.xpath("//mat-error"));

    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String s) {
        this.newPassword = s;
    }

    public String getToken() throws IOException {
        String s = PostJsonWithHttpUrlConnection.post("https://test-app.cardetektiv.de/api/authentication/get-token",
                "{\"username\": \"ihor.martyniuk+expert+stage@indealpro.com\", \"password\": \"Test0109\"}");
        JsonElement jsonElement = JsonParser.parseString(s);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        System.out.println(jsonObject);
        return jsonObject.get("token").getAsString();
    }

    public void login(@NotNull final String defaultEmail,
                      @NotNull final String defaultPassword) {
        EMAIL_INPUT.sendKeys(defaultEmail);
        PASSWORD_INPUT.sendKeys(defaultPassword);
        SIGN_IN_BUTTON.click();
    }

    public void loginWithInvalidParams(@NotNull final String invalidEmail,
                                       @NotNull final String invalidPassword) {
        EMAIL_INPUT.sendKeys(invalidEmail);
        PASSWORD_INPUT.sendKeys(invalidPassword);
        SIGN_IN_BUTTON.click();
    }


    public void loginAfterRegistration(@NotNull final String email,
                                       @NotNull final String password) {
        EMAIL_INPUT.sendKeys(email);
        PASSWORD_INPUT.sendKeys(password);
        SIGN_IN_BUTTON.click();
    }

    public void loginAfterPasswordRecovery(@NotNull final String email,
                                           @NotNull final String password) {
        EMAIL_INPUT.sendKeys(email);
        PASSWORD_INPUT.sendKeys(password);
        SIGN_IN_BUTTON.click();
    }

    public void changePasswordLoginWithOldPassword(@NotNull final String userName,
                                                   @NotNull final String oldPassword) {
        EMAIL_INPUT.sendKeys(userName);
        PASSWORD_INPUT.sendKeys(oldPassword);
        SIGN_IN_BUTTON.click();
    }

    public void changePasswordLoginWithNewPassword(@NotNull final String userName,
                                                   @NotNull final String newPassword) {
        EMAIL_INPUT.sendKeys(userName);
        PASSWORD_INPUT.sendKeys(newPassword);
        SIGN_IN_BUTTON.click();
    }

    public void clearLoginCredentials() {
        EMAIL_INPUT.clear();
        PASSWORD_INPUT.clear();
    }

    public void forgotPasswordPageButton() {
        FORGOT_PASSWORD_BUTTON.click();
    }

    public void registerPageButton() {
        REGISTER_PAGE_BUTTON.click();
    }

    public String getLoginErrorMessageText() {
        LOGIN_ERROR.shouldBe(Condition.visible);
        return LOGIN_ERROR.text();
    }

    @Override
    public boolean isCurrentPage() {
        return LOGIN_PAGE_TITLE
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
