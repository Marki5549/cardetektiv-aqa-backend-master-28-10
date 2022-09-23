package de.cardetecktive.app.webpages.components.carsearch;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.components.PageComponent;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class ArchivePreview extends PageComponent {

    private final SelenideElement ARCHIVE_SEARCH_PREVIEW_BUTTON = $(By.xpath("//mat-expansion-panel-header"));
    private final ElementsCollection ARCHIVE_SEARCH_PREVIEW_CAR_CARDS_CONTAINER = $$(By.xpath("//div[contains(@class, 'archive-preview')]"));

    public void openArchiveSearchPreview() {
        ARCHIVE_SEARCH_PREVIEW_BUTTON.click();
    }

    public void checkArchiveSearchPreviewCarCards() {
        Assertions.assertThat(ARCHIVE_SEARCH_PREVIEW_CAR_CARDS_CONTAINER)
                .as("Archive preview is empty")
                .isNotEmpty();
    }

    @Override
    public SelenideElement elementContainer() {
        return $(By.xpath("//app-archive-preview[contains(@class, 'car-search__archive-preview')]"));
    }
}
