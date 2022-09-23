package de.cardetecktive.app.webpages.components.carsearch;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.components.PageComponent;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class DealerSearchSection extends PageComponent {

    private final SelenideElement DEALER_NAME_INPUT = $(By.xpath("//input[@formcontrolname='dealerName']"));

    private final SelenideElement SEARCH_BUTTON = $(By.xpath("//button[contains(@class, 'dealer-search__button')]"));

    private final ElementsCollection DEALER_RESULTS = $$(By.xpath("//div[@class='dealer-search__result']"));
    private final SelenideElement ADD_DEALER_CARD_TO_BRANCHES_BUTTON = $(By.xpath("//button[@data-key='dealer-card-BRANCH']"), 0);
    private final SelenideElement ADD_DEALER_CARD_TO_GROUPS_BUTTON = $(By.xpath("//button[@data-key='dealer-card-GROUP']"), 1);

    private final SelenideElement BRANCHES_BUTTON = $(By.xpath("//div[@data-key='dealer-tab-BRANCH']"));
    private final SelenideElement CLEAR_BRANCHES_DEALER_CARD_BUTTON = $(By.xpath("//button[@attr.data-key='dealer-card-delete']"), 0);

    private final SelenideElement GROUPS_BUTTON = $(By.xpath("//div[@data-key='dealer-tab-GROUP']"));
    private final SelenideElement CLEAR_GROUPS_DEALER_CARD_BUTTON = $(By.xpath("//div[@data-key='GROUP-card-0']//button[@attr.data-key='dealer-card-delete']"));

    private final SelenideElement CLOSE_DEALER_SEARCH_BUTTON = $(By.xpath("//button[contains(@class, 'close-button')]"));

    public void dealerSearchResults(@NotNull final String autohouse) {
        DEALER_NAME_INPUT.sendKeys(autohouse);
        SEARCH_BUTTON.click();
        Assertions.assertThat(DEALER_RESULTS)
                .as("No dealer search results in dealer search section")
                .hasSizeGreaterThan(0);
        try {
            Thread.sleep(5_000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ADD_DEALER_CARD_TO_BRANCHES_BUTTON.click();
        ADD_DEALER_CARD_TO_GROUPS_BUTTON.click();
        CLOSE_DEALER_SEARCH_BUTTON.click();
    }

    public void clearDealerSearchResults() {
        DEALER_NAME_INPUT.clear();
        BRANCHES_BUTTON.click();
        CLEAR_BRANCHES_DEALER_CARD_BUTTON.click();
        GROUPS_BUTTON.click();
        CLEAR_GROUPS_DEALER_CARD_BUTTON.click();
        CLOSE_DEALER_SEARCH_BUTTON.click();
    }

    @Override
    public SelenideElement elementContainer() {
        return $(By.xpath("//app-dealer-search[contains(@class, 'dealer-search')]"));
    }
}
