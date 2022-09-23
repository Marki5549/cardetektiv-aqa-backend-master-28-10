package de.cardetecktive.app.webpages.carsearch;

import com.codeborne.selenide.*;
import de.cardetecktive.app.webpages.DynamicWebPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogType;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.logging.Level;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

@Component
public class ResultListPage extends DynamicWebPage {

    private final SelenideElement RESULT_SECTION_HEAD_FIELDS = $(By.xpath("//section[contains(@class, 'result-section__head-fields')]"));

    public static final ElementsCollection MAKE_CUPRA_CONTAINER = $$(By.xpath("//*[text()=' Cupra ']//parent::div[contains(@class, 'car-cell__container-flex')]"));
    public static final ElementsCollection MAKE_TESLA_CONTAINER = $$(By.xpath("//*[text()=' Tesla ']//parent::div[contains(@class, 'car-cell__container-flex')]"));
    public static final ElementsCollection MAKE_SKODA_CONTAINER = $$(By.xpath("//*[text()=' Skoda ']//parent::div[contains(@class, 'car-cell__container-flex')]"));
    public static final ElementsCollection MAKE_KIA_CONTAINER = $$(By.xpath("//*[text()=' Kia ']//parent::div[contains(@class, 'car-cell__container-flex')]"));
    public static final ElementsCollection MAKE_BMW_CONTAINER = $$(By.xpath("//*[text()=' BMW ']//parent::div[contains(@class, 'car-cell__container-flex')]"));
    public static final ElementsCollection MAKE_DAF_CONTAINER = $$(By.xpath("//*[text()=' DAF ']//parent::div[contains(@class, 'car-cell__container-flex')]"));
    public static final ElementsCollection MAKE_ADRIA_CONTAINER = $$(By.xpath("//*[text()=' Adria ']//parent::div[contains(@class, 'car-cell__container-flex')]"));
    public static final ElementsCollection ARCHIVE_MAKE_BMW_CONTAINER = $$(By.xpath("//*[text()=' BMW ']//parent::div[contains(@class, 'car-cell__container') " +
            "and contains(@class, 'car-cell__container-flex')]"));

    public static final ElementsCollection MODEL_ATECA_CONTAINER = $$(By.xpath("//*[text()=' Ateca ']//parent::div[contains(@class, 'car-cell__container')]"));
    public static final ElementsCollection MODEL_MODEL_S_CONTAINER = $$(By.xpath("//*[text()=' Model S ']//parent::div[contains(@class, 'car-cell__container')]"));
    public static final ElementsCollection MODEL_OCTAVIA_CONTAINER = $$(By.xpath("//*[text()=' Octavia ']//parent::div[contains(@class, 'car-cell__container')]"));
    public static final ElementsCollection MODEL_STINGER_CONTAINER = $$(By.xpath("//*[text()=' Stinger ']//parent::div[contains(@class, 'car-cell__container')]"));
    public static final ElementsCollection MODEL_FOURTH_SERIES_CONTAINER = $$(By.xpath("//*[text()=' 420 ']//parent::div[contains(@class, 'car-cell__container')]"));
    public static final ElementsCollection MODEL_100_CS_CONTAINER = $$(By.xpath("//*[text()=' 100 CS ']//parent::div[contains(@class, 'car-cell__container')]"));

    public static final ElementsCollection CAR_TABLE = $$(By.xpath("//table[contains(@class, 'car-table')]"));
    public static final ElementsCollection CAR_TABLE_ROW = $$(By.xpath("//tr[contains(@class, 'car-table__row')]"));
    public static final SelenideElement CAR_DETAIL_INFO_FUEL_TYPE_CONTAINER = $(By.xpath("//div[contains(@class, 'car-detail-info-content__overview__item') " +
            "and contains(@class, 'ng-star-inserted')]"), 3);

    private final SelenideElement CREATE_PDF_BUTTON = $(By.xpath("//button[@data-key='result-pdf-data']"));
    private final SelenideElement ALL_ADS_LIST_PDF_BUTTON = $(By.xpath("//*[text()=' alle Liste ']//parent::button"));
    private final SelenideElement ALL_ADS_DETAILS_PDF_BUTTON = $(By.xpath("//*[text()=' alle Detail ']//parent::button"));

    private final SelenideElement SEARCH_CHIPS_MENU_BUTTON = $(By.xpath("//app-car-menu[@attr.data-key='result-list-header-menu']//button"));
    private final SelenideElement SEARCH_CHIPS_MENU_REFRESH_BUTTON = $(By.xpath("//button[@data-key='menu-option-refresh']"));
    private final SelenideElement SEARCH_CHIPS_MENU_CONFIRM_REFRESH_BUTTON = $(By.xpath("//button[@attr.data-key='dialog-confirm']"));

    private final SelenideElement PDF_VIEWER_CONTAINER = $(By.xpath("//pdf-viewer[@id='viewer']"));

    private void scroll() {
        Selenide.executeJavaScript("setTimeout(() => document.getElementsByClassName('table-wrap')[0].scrollTo(0, document.body.scrollHeight), 100);");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCars(ElementsCollection el) {
        scroll();
        ArrayList<String> cars = new ArrayList<>();
        for (SelenideElement selenideElement : el.shouldHave(sizeGreaterThan(0))) {
            cars.add(selenideElement.getText());
        }
        return cars;
    }

    public void createAllAdsListPdf() {
        getCars(MAKE_CUPRA_CONTAINER);
        CREATE_PDF_BUTTON.click();
        ALL_ADS_LIST_PDF_BUTTON.click();
        switchTo().window(1).getWindowHandle();
        for(String logEntry : getWebDriverLogs(LogType.BROWSER, Level.ALL)) {
            System.out.println("Logs: " + logEntry);
        }
        switchTo().defaultContent().getWindowHandle();
    }

    public void createAllAdsDetailsPdf() {
        getCars(MAKE_CUPRA_CONTAINER);
        CREATE_PDF_BUTTON.click();
        ALL_ADS_DETAILS_PDF_BUTTON.click();
        PDF_VIEWER_CONTAINER.shouldBe(visible,enabled);
    }

    public void createCounterAllAdsListPdf() {
        CREATE_PDF_BUTTON.click();
        ALL_ADS_LIST_PDF_BUTTON.click();
        PDF_VIEWER_CONTAINER.shouldBe(visible,enabled);
    }

    public void checkDealerResultsAviability() {
        SEARCH_CHIPS_MENU_BUTTON.click();
        SEARCH_CHIPS_MENU_REFRESH_BUTTON.click();
        SEARCH_CHIPS_MENU_CONFIRM_REFRESH_BUTTON.click();
        try {
            Thread.sleep(5_000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Assertions.assertThat(CAR_TABLE)
                .as("No dealer search results in branches and groups pages")
                .hasSizeGreaterThan(0);
    }

    public void resultListCarDetailedAviability() {
        try {
            Thread.sleep(5000);
        } catch (Exception ignored) {
        }
        for (SelenideElement selenideElement : CAR_TABLE_ROW) {
            selenideElement.click();
            String fuel = CAR_DETAIL_INFO_FUEL_TYPE_CONTAINER.shouldBe(visible,enabled).find(By.cssSelector("span"), 1).getText();
            System.out.println("Fuel: " + fuel);
            if (fuel.isEmpty()) {
                System.err.println("Car detailed is empty");
            }
        }
    }

    @Override
    public boolean isCurrentPage() {
        return RESULT_SECTION_HEAD_FIELDS
                .shouldBe(Condition.appear)
                .isDisplayed();
    }
}
