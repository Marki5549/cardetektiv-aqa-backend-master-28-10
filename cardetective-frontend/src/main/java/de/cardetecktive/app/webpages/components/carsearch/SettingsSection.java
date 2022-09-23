package de.cardetecktive.app.webpages.components.carsearch;

import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.components.PageComponent;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Component
public class SettingsSection extends PageComponent {

    private final SelenideElement CLOSE_SETTINGS_BUTTON = $(By.xpath("//button[@data-key='close-settings']"));

    private final SelenideElement ACCOUNT_SETTINGS_BUTTON = $(By.xpath("//div[@data-key='settings-account-settings']"));
    private final SelenideElement DAT_BUTTON = $(By.xpath("//div[@data-key='settings-dat']"));

    private final SelenideElement ACCOUNT_SETTINGS_ACCOUNT_BUTTON = $(By.xpath("//div[@data-key='settings-account']"));
    private final SelenideElement ACCOUNT_OLD_PASSWORD_INPUT = $(By.xpath("//input[@data-key='settings-oldPassword']"));
    private final SelenideElement ACCOUNT_NEW_PASSWORD_INPUT = $(By.xpath("//input[@data-key='settings-newPassword']"));
    private final SelenideElement ACCOUNT_CONFIRM_NEW_PASSWORD_INPUT = $(By.xpath("//input[@data-key='settings-repeatedPassword']"));
    private final SelenideElement ACCOUNT_SAVE_PASSWORD_BUTTON = $(By.xpath("//button[@data-key='settings-submit-password']"));
    private final SelenideElement ACCOUNT_CLOSE_PASSWORD_IS_CHANGED_MESSAGE_BUTTON = $(By.xpath("//button[@data-key='close-message']"));

    private final SelenideElement DAT_CUSTOMER_NUMBER_INPUT = $(By.xpath("//input[@data-key='settings-datCustomerNumber']"));
    private final SelenideElement RESET_DAT_CUSTOMER_NUMBER_INPUT = $(By.xpath("//button[@data-key='reset-settings-datCustomerNumber']"));
    private final SelenideElement DAT_USERNAME_INPUT = $(By.xpath("//input[@data-key='settings-datCustomerName']"));
    private final SelenideElement RESET_DAT_USERNAME_INPUT = $(By.xpath("//button[@data-key='reset-settings-datCustomerName']"));
    private final SelenideElement DAT_PASSWORD_INPUT = $(By.xpath("//input[@data-key='settings-datPassword']"));
    private final SelenideElement RESET_DAT_PASSWORD_INPUT = $(By.xpath("//button[@data-key='reset-settings-datPassword']"));
    private final SelenideElement SAVE_DAT_CREDENTIALS_BUTTON = $(By.xpath("//button[@attr.data-key='settings-submit-datCredentials']"));
    private final SelenideElement DAT_CREDENTIALS_SUCCESSFULLY_SAVED = $(By.xpath("//p[@class='message__text']"));
    private final SelenideElement SAVING_DAT_CREDENTIALS_FORBIDDEN = $(By.xpath("//p[@class='message__text']"));
    private final SelenideElement DAT_CREDENTIALS_CLOSE_OVERLAY_BUTTON = $(By.xpath("//button[@data-key='close-message']"));

    public void openDat() {
        DAT_BUTTON.click();
    }

    public void changePassword(@NotNull final String oldPassword,
                               @NotNull final String newPassword) {
        ACCOUNT_SETTINGS_BUTTON.click();
        ACCOUNT_SETTINGS_ACCOUNT_BUTTON.click();
        ACCOUNT_OLD_PASSWORD_INPUT.sendKeys(oldPassword);
        ACCOUNT_NEW_PASSWORD_INPUT.sendKeys(newPassword);
        ACCOUNT_CONFIRM_NEW_PASSWORD_INPUT.sendKeys(newPassword);
        ACCOUNT_SAVE_PASSWORD_BUTTON.click();
        ACCOUNT_CLOSE_PASSWORD_IS_CHANGED_MESSAGE_BUTTON.click();
    }

    public void saveDatCredentials(@NotNull final String datCustomerNumber,
                                   @NotNull final String datUsername,
                                   @NotNull final String datPassword) {
        DAT_CUSTOMER_NUMBER_INPUT.sendKeys(datCustomerNumber);
        DAT_USERNAME_INPUT.sendKeys(datUsername);
        DAT_PASSWORD_INPUT.sendKeys(datPassword);
        SAVE_DAT_CREDENTIALS_BUTTON.click();
    }

    public void saveInvalidDatCredentials(@NotNull final String invalidDatCustomerNumber,
                                          @NotNull final String invalidDatUsername,
                                          @NotNull final String invalidDatPassword) {
        DAT_CUSTOMER_NUMBER_INPUT.sendKeys(invalidDatCustomerNumber);
        DAT_USERNAME_INPUT.sendKeys(invalidDatUsername);
        DAT_PASSWORD_INPUT.sendKeys(invalidDatPassword);
        SAVE_DAT_CREDENTIALS_BUTTON.click();
    }

    public void closeDatCredentialsOverlay() {
        DAT_CREDENTIALS_CLOSE_OVERLAY_BUTTON.click();
    }

    public void resetDatCredentials() {
        RESET_DAT_CUSTOMER_NUMBER_INPUT.click();
        RESET_DAT_USERNAME_INPUT.click();
        RESET_DAT_PASSWORD_INPUT.click();
        SAVE_DAT_CREDENTIALS_BUTTON.click();
        DAT_CREDENTIALS_CLOSE_OVERLAY_BUTTON.click();
    }

    public void closeSettings() {
        CLOSE_SETTINGS_BUTTON.click();
    }

    public String getDatCredentialsSuccessfullySavedText() {
        DAT_CREDENTIALS_SUCCESSFULLY_SAVED.shouldBe(visible);
        return DAT_CREDENTIALS_SUCCESSFULLY_SAVED.text();
    }

    public String getSavingDatCredentialsForbiddenText() {
        SAVING_DAT_CREDENTIALS_FORBIDDEN.shouldBe(visible);
        return SAVING_DAT_CREDENTIALS_FORBIDDEN.text();
    }

    @Override
    public SelenideElement elementContainer() {
        return $(By.xpath("//h1[@class='settings__title']"));
    }
}
