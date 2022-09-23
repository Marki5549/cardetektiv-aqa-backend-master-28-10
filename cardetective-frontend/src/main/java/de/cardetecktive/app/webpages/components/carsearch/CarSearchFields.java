package de.cardetecktive.app.webpages.components.carsearch;

import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.components.PageComponent;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class CarSearchFields extends PageComponent {

    private final SelenideElement SEARCHMASK_CATEGORY_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-carTypes']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_CONDITION_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-condition']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_MINIMAL_SEATS_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-minimalSeats']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_MAXIMAL_SEATS_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-maximalSeats']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_DOORS_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-doors']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_SLIDING_DOOR_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-slidingDoor']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_COLOR_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-color']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_INTERIOR_COLOR_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-colorInt']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_INTERIOR_TYPE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-interiorType']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_EURO_NORM_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-emissionClass']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_ENV_BADGE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-environmentBadge']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_CLIMATISATION_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-climatisation']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_PARKING_HELP_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-parkingAssistants']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_ADAPTIVE_CRUISE_CONTROL_BUTTON = $(By.xpath("//mat-list-option[@data-key='value-ADAPTIVE_CRUISE_CTL']"));

    private final SelenideElement SEARCHMASK_PREV_OWNERS_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-numberOfPreviousOwners']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_ACCIDENT_VEHICLES_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-accidentData']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_SELLER_TYPE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-sellerType']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_AD_ONLINE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-adAge']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_VATABLE_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-vatableOption']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_COUNTRY_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-country']")).closest("mat-form-field");
    private final SelenideElement SEARCHMASK_ZIP_CODE_FIELD = $(By.xpath("//input[@data-key='searchmask-zipCode']")).closest("mat-form-field");

    private final SelenideElement SEARCHMASK_RADIUS_FIELD = $(By.xpath("//mat-select[@data-key='searchmask-radius']")).closest("mat-form-field");

    public void checkUrlQueryParamsCarSearchFields() {
        SoftAssertions softlyUrlQueryParamsCarSearchFields = new SoftAssertions();
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_CATEGORY_FIELD)
                .getAttribute("class"))
                .as("Category is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_CONDITION_FIELD)
                .getAttribute("class"))
                .as("Condition is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_MINIMAL_SEATS_FIELD)
                .getAttribute("class"))
                .as("Minimal seats is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_MAXIMAL_SEATS_FIELD)
                .getAttribute("class"))
                .as("Maximal seats is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_DOORS_FIELD)
                .getAttribute("class"))
                .as("Doors is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_SLIDING_DOOR_FIELD)
                .getAttribute("class"))
                .as("Sliding door is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_COLOR_FIELD)
                .getAttribute("class"))
                .as("Color is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_INTERIOR_COLOR_FIELD)
                .getAttribute("class"))
                .as("Interior color is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_INTERIOR_TYPE_FIELD)
                .getAttribute("class"))
                .as("Interior type is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_EURO_NORM_FIELD)
                .getAttribute("class"))
                .as("Euro norm is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_ENV_BADGE_FIELD)
                .getAttribute("class"))
                .as("Env badge is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_CLIMATISATION_FIELD)
                .getAttribute("class"))
                .as("Climatisation is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_PARKING_HELP_FIELD)
                .getAttribute("class"))
                .as("Parking help is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_ADAPTIVE_CRUISE_CONTROL_BUTTON)
                .getAttribute("aria-selected"))
                .as("Adaptive cruise control is not set")
                .contains("true");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_PREV_OWNERS_FIELD)
                .getAttribute("class"))
                .as("Prev owners is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_ACCIDENT_VEHICLES_FIELD)
                .getAttribute("class"))
                .as("Accident vehicles is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_SELLER_TYPE_FIELD)
                .getAttribute("class"))
                .as("Seller type is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_AD_ONLINE_FIELD)
                .getAttribute("class"))
                .as("Ad online is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_VATABLE_FIELD)
                .getAttribute("class"))
                .as("Vatable is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_COUNTRY_FIELD)
                .getAttribute("class"))
                .as("Country is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_ZIP_CODE_FIELD)
                .getAttribute("class"))
                .as("Zip code is not set")
                .contains("input--active");
        softlyUrlQueryParamsCarSearchFields.assertThat($(SEARCHMASK_RADIUS_FIELD)
                .getAttribute("class"))
                .as("Radius is not set")
                .contains("select--active");
        softlyUrlQueryParamsCarSearchFields.assertAll();
    }

    @Override
    public SelenideElement elementContainer() {
        return $(By.xpath("//app-mainfields-section[contains(@class, 'car-search__fields')]"));
    }
}
