package de.cardetecktive.app.webpages.components.carsearch;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.components.PageComponent;
import org.assertj.core.api.SoftAssertions;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Component
public class CarSearchHeader extends PageComponent {

    private final SelenideElement SEARCHMASK_RESET_SEARCH_BUTTON = $(By.xpath("//button[@attr.data-key='reset-search-button']"));

    private final SelenideElement SEARCHMASK_VIN_QUERY_BUTTON = $(By.xpath("//button[contains(@data-original-title, 'VIN') " +
            "and contains(@data-original-title, 'query')]"));
    private final SelenideElement VIN_QUERY_VIN_FIELD = $(By.xpath("//input[@data-key='searchmask-vin']"));
    private final SelenideElement VIN_QUERY_EZ_FIELD = $(By.xpath("//input[@data-key='searchmask-vinSearchYear']"));
    private final SelenideElement VIN_QUERY_KILOMETER_FIELD = $(By.xpath("//input[@data-key='searchmask-vinSearchMileage']"));
    private final SelenideElement VIN_QUERY_DAT_RATING_BUTTON = $(By.xpath("//*[text()=' DAT Bewertung ']//parent::button"));
    private final SelenideElement DAT_RATING_WINDOW = $(By.xpath("//mat-dialog-container[contains(@class, 'mat-dialog-container')]"));
    private final SelenideElement DAT_RATING_VIN_FIELD = $(By.xpath("//div[@class='value']"), 6);

    private final SelenideElement SEARCHMASK_OPERATION_NUMBER_BUTTON = $(By.xpath("//input[@data-key='searchmask-opnum']"));
    private final SelenideElement SEARCHMASK_OPERATION_NUMBER_FIELD = $(By.xpath("//input[@data-key='searchmask-opnum']")).closest("mat-form-field");

    private final SelenideElement TRUCK_SEARCHMASK_VEHICLE_TYPE_BUTTON = $(By.xpath("//mat-select[@data-key='searchmask-vehicleType']"));
    private final ElementsCollection TRUCK_SEARCHMASK_VEHICLE_TYPE_DROPDOWN = $$(By.xpath("//span[@class='mat-option-text']"));

    private final SelenideElement SEARCHMASK_MANUFACTURER_BUTTON = $(By.xpath("//mat-select[@data-key='searchmask-make']"));
    private final SelenideElement SEARCHMASK_MANUFACTURER_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-make']")).closest("mat-form-field");
    private final ElementsCollection SEARCHMASK_MANUFACTURER_DROPDOWN = $$(By.xpath("//span[@class='mat-option-text']"));
    private final SelenideElement SEARCHMASK_CUPRA_BUTTON = $(By.xpath("//*[text()=' Cupra ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_TESLA_BUTTON = $(By.xpath("//*[text()=' Tesla ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_SKODA_BUTTON = $(By.xpath("//*[text()=' Skoda ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_KIA_BUTTON = $(By.xpath("//*[text()=' Kia ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_BMW_BUTTON = $(By.xpath("//*[text()=' BMW ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_DAF_BUTTON = $(By.xpath("//*[text()=' DAF ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_ADRIA_BUTTON = $(By.xpath("//*[text()=' Adria ']//parent::mat-option"));

    private final SelenideElement SEARCHMASK_MODEL_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-model']")).closest("mat-form-field");
    private final ElementsCollection SEARCHMASK_MODEL_DROPDOWN = $$(By.xpath("//span[@class='mat-option-text']"));
    private final SelenideElement SEARCHMASK_ATECA_BUTTON = $(By.xpath("//*[text()=' Ateca ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_MODEL_S_BUTTON = $(By.xpath("//*[text()=' Model S ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_OCTAVIA_BUTTON = $(By.xpath("//*[text()=' Octavia ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_FABIA_BUTTON = $(By.xpath("//*[text()=' Fabia ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_STINGER_BUTTON = $(By.xpath("//*[text()=' Stinger ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_FOURTH_SERIES_BUTTON = $(By.xpath("//*[text()=' 420 ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_100_CS_BUTTON = $(By.xpath("//*[text()=' 100 CS ']//parent::mat-option"));

    private final SelenideElement SEARCHMASK_VERSION_FIELD = $(By.xpath("//input[@data-key='searchmask-version']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_FUEL_TYPE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-fuelTypes']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_GEARBOX_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-gearbox']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_MINIMAL_POWER_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-minimalPower']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_MAXIMAL_POWER_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-maximalPower']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_MINIMAL_YEAR_BUTTON = $(By.xpath("//mat-select[@data-key='searchmask-minimalYear']"));
    private final SelenideElement SEARCHMASK_MINIMAL_YEAR_2020_BUTTON = $(By.xpath("//*[text()=' 2020 ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_MINIMAL_YEAR_2021_BUTTON = $(By.xpath("//*[text()=' 2021 ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_MAXIMAL_YEAR_BUTTON = $(By.xpath("//mat-select[@data-key='searchmask-maximalYear']"));
    private final SelenideElement SEARCHMASK_MAXIMAL_YEAR_2020_BUTTON = $(By.xpath("//*[text()=' 2020 ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_MAXIMAL_YEAR_2021_BUTTON = $(By.xpath("//*[text()=' 2021 ']//parent::mat-option"));

    private final SelenideElement SEARCHMASK_MINIMAL_MILEAGE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-minimalMileage']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_MINIMAL_MILEAGE_BUTTON = $(By.xpath("//mat-select[@data-key='searchmask-minimalMileage']"));
    private final SelenideElement SEARCHMASK_MINIMAL_MILEAGE_5_000_BUTTON = $(By.xpath("//*[text()=' 5.000 km ']//parent::mat-option"));
    private final SelenideElement SEARCHMASK_MAXIMAL_MILEAGE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-maximalMileage']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_MAXIMAL_MILEAGE_BUTTON = $(By.xpath("//mat-select[@data-key='searchmask-maximalMileage']"));
    private final SelenideElement SEARCHMASK_MAXIMAL_MILEAGE_5_000_BUTTON = $(By.xpath("//*[text()=' 5.000 km ']//parent::mat-option"));

    private final SelenideElement ARCHIVE_SEARCH_AD_ONLINE_FROM_MIN_BUTTON = $(By.xpath("//button[contains(@aria-label, 'calendar')]"), 0);
    private final SelenideElement ARCHIVE_SEARCH_AD_ONLINE_FROM_MIN_CHOOSE_YEAR_BUTTON = $(By.xpath("//button[contains(@aria-label, 'date')]"));
    private final SelenideElement ARCHIVE_SEARCH_AD_ONLINE_FROM_MIN_CHOOSE_YEAR_2016_BUTTON = $(By.xpath("//*[text()=' 2016 ']//parent::td"));
    private final SelenideElement ARCHIVE_SEARCH_AD_ONLINE_FROM_MIN_CHOOSE_MONTH_JAN_BUTTON = $(By.xpath("//*[text()=' JAN ']//parent::td"));

    private final SelenideElement ARCHIVE_SEARCH_AD_ONLINE_FROM_MAX_BUTTON = $(By.xpath("//button[contains(@aria-label, 'calendar')]"), 1);
    private final SelenideElement ARCHIVE_SEARCH_AD_ONLINE_FROM_MAX_CHOOSE_YEAR_BUTTON = $(By.xpath("//button[contains(@aria-label, 'date')]"));
    private final SelenideElement ARCHIVE_SEARCH_AD_ONLINE_FROM_MAX_CHOOSE_YEAR_2021_BUTTON = $(By.xpath("//*[text()=' 2021 ']//parent::td"));
    private final SelenideElement ARCHIVE_SEARCH_AD_ONLINE_FROM_MAX_CHOOSE_MONTH_JAN_BUTTON = $(By.xpath("//*[text()=' JAN ']//parent::td"));

    private final SelenideElement SEARCHMASK_MINIMAL_PRICE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-minimalPrice']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_MAXIMAL_PRICE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-maximalPrice']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_VEHICLES_FOUND_BUTTON = $(By.xpath("//button[@attr.data-key='vehicles-found-button']"));

    private final SelenideElement SEARCHMASK_BUY_REPORT_BUTTON = $(By.xpath("//button[contains(@class, 'submit')]"));
    private final SelenideElement SEARCHMASK_CONFIRM_BUY_REPORT_BUTTON = $(By.xpath("//button[@attr.data-key='dialog-confirm']"));

    private final SelenideElement SEARCHMASK_DEALER_SEARCH_BUTTON = $(By.xpath("//button[contains(@class, 'dealer-search-button')]"));

    private String defaultOperationNumber;

    public String getDefaultOperationNumber() {
        return defaultOperationNumber;
    }

    public void searchVehicles() {
        SEARCHMASK_VEHICLES_FOUND_BUTTON.shouldNotHave(attribute("mat-button-disabled")).click();
    }

    public void buyReport() {
        SEARCHMASK_BUY_REPORT_BUTTON.shouldHave(text("Report kaufen:")).click();
        SEARCHMASK_CONFIRM_BUY_REPORT_BUTTON.click();
    }

    public void cupraAteca(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_CUPRA_BUTTON.click();
        SEARCHMASK_ATECA_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2020_BUTTON.click();
    }

    public void teslaModelS(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_TESLA_BUTTON.click();
        SEARCHMASK_MODEL_S_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2020_BUTTON.click();
    }

    public void skodaOctavia(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_SKODA_BUTTON.click();
        SEARCHMASK_OCTAVIA_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2020_BUTTON.click();
    }

    public void kiaStinger(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_KIA_BUTTON.click();
        SEARCHMASK_STINGER_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2020_BUTTON.click();
    }

    public void bmwFourthSeries(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_BMW_BUTTON.click();
        SEARCHMASK_FOURTH_SERIES_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2021_BUTTON.click();
    }

    public void bmw100Cs(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_BMW_BUTTON.click();
        SEARCHMASK_100_CS_BUTTON.click();
    }

    public void daf(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_DAF_BUTTON.click();
    }

    public void adria(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_ADRIA_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2021_BUTTON.click();
        SEARCHMASK_MAXIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MAXIMAL_YEAR_2021_BUTTON.click();
    }

    public void switchTruckVehicleTypes() {
        int step = 0;
        while (true) {
            if (step >= 9)
                return;
            int count = 0;
            TRUCK_SEARCHMASK_VEHICLE_TYPE_BUTTON.click();
            for (SelenideElement selenideElement : TRUCK_SEARCHMASK_VEHICLE_TYPE_DROPDOWN.shouldHave(sizeGreaterThan(0))) {
                if (selenideElement.getText().trim().isEmpty())
                    continue;
                count++;
                if (step != count - 1)
                    continue;
                selenideElement.click();
                step++;
                try {
                    Thread.sleep(1_000);
                } catch (Exception ignored) {
                }
                for (String logEntry : getWebDriverLogs(LogType.BROWSER, Level.ALL)) {
                    System.out.println("Logs: " + step + " / " + logEntry);
                }
                break;
            }
        }
    }

    public void resultListMakeDuplicates() {
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        ArrayList<String> make = new ArrayList<>();
        ArrayList<String> makeDuplicates = new ArrayList<>();
        for (SelenideElement selenideElement : SEARCHMASK_MANUFACTURER_DROPDOWN.shouldHave(sizeGreaterThan(0))) {
            String makeName = selenideElement.getText();
            if (containsInList(make, makeName) && !containsInList(makeDuplicates, makeName))
                makeDuplicates.add(makeName);
            make.add(makeName);
        }
        if (makeDuplicates.size() > 0) {
            System.out.println("Found duplicates in: ");
            for (int i = 0; i < makeDuplicates.size(); i++)
                System.out.println((i + 1) + ") " + makeDuplicates.get(i));
        }
        SEARCHMASK_MANUFACTURER_BUTTON.pressEscape();
    }

    public void resultListModelDuplicates() {
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        for (SelenideElement selenideElement : SEARCHMASK_MANUFACTURER_DROPDOWN.shouldHave(sizeGreaterThan(0))) {
            if (selenideElement.getText().trim().isEmpty())
                continue;
            String makeName = selenideElement.getText();
            selenideElement.click();
            ArrayList<String> model = new ArrayList<>();
            ArrayList<String> modelDuplicates = new ArrayList<>();
            for (SelenideElement modelElement : SEARCHMASK_MODEL_DROPDOWN.shouldHave(sizeGreaterThan(0))) {
                String modelName = modelElement.getText();
                if (containsInList(model, modelName) && !containsInList(modelDuplicates, modelName))
                    modelDuplicates.add(modelName);
                model.add(modelName);
            }
            if (modelDuplicates.size() > 0) {
                System.out.println("Found duplicates in " + makeName + ": ");
                for (int i = 0; i < modelDuplicates.size(); i++)
                    System.out.println((i + 1) + ") " + modelDuplicates.get(i));
            }
            SEARCHMASK_MANUFACTURER_BUTTON.pressEscape();
            SEARCHMASK_MANUFACTURER_BUTTON.click();
        }
        SEARCHMASK_MANUFACTURER_BUTTON.pressEscape();
    }

    private boolean containsInList(List<String> list, String element) {
        for (String s : list)
            if (s.equalsIgnoreCase(element))
                return true;
        return false;
    }

    public void resultListPdf(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_CUPRA_BUTTON.click();
        SEARCHMASK_ATECA_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2020_BUTTON.click();
        SEARCHMASK_MAXIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MAXIMAL_YEAR_2020_BUTTON.click();
    }

    public void equalCounterParams(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_BMW_BUTTON.click();
        SEARCHMASK_FOURTH_SERIES_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2021_BUTTON.click();
    }

    public void differentOpNumsCounterParams(@NotNull final String defaultOperationNumber) {
        this.defaultOperationNumber = defaultOperationNumber;
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_BMW_BUTTON.click();
        SEARCHMASK_FOURTH_SERIES_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2021_BUTTON.click();
    }

    public void differentMakesCounterParams(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_SKODA_BUTTON.click();
        SEARCHMASK_OCTAVIA_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2021_BUTTON.click();
        SEARCHMASK_MAXIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MAXIMAL_YEAR_2021_BUTTON.click();
        SEARCHMASK_MINIMAL_MILEAGE_BUTTON.click();
        SEARCHMASK_MINIMAL_MILEAGE_5_000_BUTTON.click();
        SEARCHMASK_MAXIMAL_MILEAGE_BUTTON.click();
        SEARCHMASK_MAXIMAL_MILEAGE_5_000_BUTTON.click();
    }

    public void differentModelsCounterParams(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_SKODA_BUTTON.click();
        SEARCHMASK_FABIA_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2021_BUTTON.click();
        SEARCHMASK_MAXIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MAXIMAL_YEAR_2021_BUTTON.click();
        SEARCHMASK_MINIMAL_MILEAGE_BUTTON.click();
        SEARCHMASK_MINIMAL_MILEAGE_5_000_BUTTON.click();
        SEARCHMASK_MAXIMAL_MILEAGE_BUTTON.click();
        SEARCHMASK_MAXIMAL_MILEAGE_5_000_BUTTON.click();
    }

    public void pdfCreatedCounterParams(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_BMW_BUTTON.click();
        SEARCHMASK_FOURTH_SERIES_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2021_BUTTON.click();
    }

    public void archiveSearchParams(@NotNull final String defaultOperationNumber) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        SEARCHMASK_MANUFACTURER_BUTTON.click();
        SEARCHMASK_BMW_BUTTON.click();
        SEARCHMASK_FOURTH_SERIES_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_BUTTON.click();
        SEARCHMASK_MINIMAL_YEAR_2021_BUTTON.click();
        ARCHIVE_SEARCH_AD_ONLINE_FROM_MIN_BUTTON.click();
        ARCHIVE_SEARCH_AD_ONLINE_FROM_MIN_CHOOSE_YEAR_BUTTON.click();
        ARCHIVE_SEARCH_AD_ONLINE_FROM_MIN_CHOOSE_YEAR_2016_BUTTON.click();
        ARCHIVE_SEARCH_AD_ONLINE_FROM_MIN_CHOOSE_MONTH_JAN_BUTTON.click();
        ARCHIVE_SEARCH_AD_ONLINE_FROM_MAX_BUTTON.click();
        ARCHIVE_SEARCH_AD_ONLINE_FROM_MAX_CHOOSE_YEAR_BUTTON.click();
        ARCHIVE_SEARCH_AD_ONLINE_FROM_MAX_CHOOSE_YEAR_2021_BUTTON.click();
        ARCHIVE_SEARCH_AD_ONLINE_FROM_MAX_CHOOSE_MONTH_JAN_BUTTON.click();
    }

    public void openVinQuery() {
        SEARCHMASK_VIN_QUERY_BUTTON.click();
    }

    public void vinSearch(@NotNull final String defaultOperationNumber,
                          @NotNull final String vin,
                          @NotNull final String ez,
                          @NotNull final String kilometer) {
        SEARCHMASK_OPERATION_NUMBER_BUTTON.sendKeys(defaultOperationNumber);
        VIN_QUERY_VIN_FIELD.sendKeys(vin);
        VIN_QUERY_EZ_FIELD.sendKeys(ez);
        VIN_QUERY_KILOMETER_FIELD.sendKeys(kilometer);
        VIN_QUERY_DAT_RATING_BUTTON.click();
    }

    public String getDatRatingVinFieldText() {
        DAT_RATING_VIN_FIELD.shouldBe(visible);
        return DAT_RATING_VIN_FIELD.text();
    }

    public void closeDatRatingWindow() {
        DAT_RATING_WINDOW.pressEscape();
    }

    public void checkUrlQueryParamsCarSearchHeader() {
        SoftAssertions softlyUrlQueryParamsCarSearchHeader = new SoftAssertions();
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_OPERATION_NUMBER_FIELD)
                        .getAttribute("class"))
                .as("Opnum is not set")
                .contains("input--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_MANUFACTURER_FIELD)
                        .getAttribute("class"))
                .as("Manufacturer is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_MODEL_FIELD)
                        .getAttribute("class"))
                .as("Model is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_VERSION_FIELD)
                        .getAttribute("class"))
                .as("Version is not set")
                .contains("input--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_FUEL_TYPE_FIELD)
                        .getAttribute("class"))
                .as("Fuel type is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_GEARBOX_FIELD)
                        .getAttribute("class"))
                .as("Gearbox is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_MINIMAL_POWER_FIELD)
                        .getAttribute("class"))
                .as("Minimal power is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_MAXIMAL_POWER_FIELD)
                        .getAttribute("class"))
                .as("Maximal power is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_MINIMAL_MILEAGE_FIELD)
                        .getAttribute("class"))
                .as("Minimal mileage is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_MAXIMAL_MILEAGE_FIELD)
                        .getAttribute("class"))
                .as("Maximal mileage is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_MINIMAL_PRICE_FIELD)
                        .getAttribute("class"))
                .as("Minimal price is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertThat($(SEARCHMASK_MAXIMAL_PRICE_FIELD)
                        .getAttribute("class"))
                .as("Maximal price is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchHeader.assertAll();
    }

    public void checkUrlDatEuroCodeParams() {
        SoftAssertions softlyUrlDatEuroCodeParams = new SoftAssertions();
        softlyUrlDatEuroCodeParams.assertThat($(SEARCHMASK_MANUFACTURER_FIELD)
                        .getAttribute("class"))
                .as("Manufacturer is not set")
                .contains("select--active");
        softlyUrlDatEuroCodeParams.assertThat($(SEARCHMASK_MODEL_FIELD)
                        .getAttribute("class"))
                .as("Model is not set")
                .contains("select--active");
    }

    public void openDealerSearch() {
        SEARCHMASK_DEALER_SEARCH_BUTTON.click();
    }

    public void resetSearch() {
        SEARCHMASK_RESET_SEARCH_BUTTON.click();
    }

    @Override
    public SelenideElement elementContainer() {
        return $(By.xpath("//header[contains(@class, 'car-search__section')]"));
    }
}
