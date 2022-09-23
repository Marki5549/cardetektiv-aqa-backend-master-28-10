package de.cardetecktive.app.webpages.components.carsearch;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import de.cardetecktive.app.webpages.components.PageComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class ApplicationHeader extends PageComponent {

    private final SelenideElement ARCHIVE_SEARCH_BUTTON = $(By.xpath("//button[contains(@class, 'toggle-search-mask-type__archive')]"));

    private final SelenideElement CAR_SEARCHMASK_PAGE_BUTTON = $(By.xpath("//button[@data-key='change-search-mask-button-car']"));
    private final SelenideElement MOTO_SEARCHMASK_PAGE_BUTTON = $(By.xpath("//button[@data-key='change-search-mask-button-moto']"));
    private final SelenideElement TRUCK_SEARCHMASK_PAGE_BUTTON = $(By.xpath("//button[@data-key='change-search-mask-button-truck']"));
    private final SelenideElement CAMPER_SEARCHMASK_PAGE_BUTTON = $(By.xpath("//button[@data-key='change-search-mask-button-camper']"));
    private final SelenideElement BRANCHES_PAGE_BUTTON = $(By.xpath("//a[@data-key='nav-tab-BRANCH']"));
    private final SelenideElement GROUPS_PAGE_BUTTON = $(By.xpath("//a[@data-key='nav-tab-GROUP']"));
    private final SelenideElement SEARCHMASK_PAGE_MY_ACCOUNT_BUTTON = $(By.xpath("//button[contains(@class, 'mat-focus-indicator') " +
            "and contains(@class, 'mat-menu-trigger') and contains(@class, 'main-toolbar__item') and contains(@class, 'profile-menu__trigger')]"));
    private final SelenideElement SEARCHMASK_PAGE_SETTINGS_BUTTON = $(By.xpath("//button[contains(@class, 'profile-menu__item1')]"));
    private final SelenideElement SEARCHMASK_PAGE_COUNTER_BUTTON = $(By.xpath("//button[contains(@class, 'profile-menu__item2')]"));
    private final SelenideElement SEARCHMASK_PAGE_COUNTER_REQUESTS_NUMBER_FIELD = $(By.xpath("//div[@class='cdk-overlay-pane']//tr//td"), 2);
    private final SelenideElement SEARCHMASK_PAGE_COUNTER_OPERATIONS_NUMBER_FIELD = $(By.xpath("//div[@class='cdk-overlay-pane']//tr//td"), 5);
    private final SelenideElement SEARCHMASK_PAGE_COUNTER_PDF_CREATED_NUMBER_FIELD = $(By.xpath("//div[@class='cdk-overlay-pane']//tr//td"), 8);
    private final SelenideElement SEARCHMASK_PAGE_CLOSE_COUNTER_BUTTON = $(By.xpath("//button[contains(@class, 'mat-focus-indicator') " +
            "and contains(@class, 'mat-focus-indicator close') and contains(@class, 'mat-focus-indicator close mat-icon-button')]"));
    private final SelenideElement SEARCHMASK_PAGE_LOG_OUT_EXPERT_BUTTON = $(By.xpath("//button[contains(@class, 'profile-menu__item3')]"));
    private final SelenideElement SEARCHMASK_PAGE_LOG_OUT_DEALER_BUTTON = $(By.xpath("//button[contains(@class, 'profile-menu__item2')]"));

    private final SelenideElement JOYRIDE_STEP_CLOSE_BUTTON = $(By.xpath("//joy-close-button[@class='joyride-step__close']"));

    @Getter
    private int counter;

    public void closeJoyRideStep() {
        JOYRIDE_STEP_CLOSE_BUTTON.click();
    }

    public void openArchiveSearch() {
        ARCHIVE_SEARCH_BUTTON.click();
    }

    public void openCarSearchmaskPage() {
        CAR_SEARCHMASK_PAGE_BUTTON.click();
    }

    public void openMotoSearchmaskPage() {
        MOTO_SEARCHMASK_PAGE_BUTTON.click();
    }

    public void openTruckSearchmaskPage() {
        TRUCK_SEARCHMASK_PAGE_BUTTON.click();
    }

    public void openCamperSearchmaskPage() {
        CAMPER_SEARCHMASK_PAGE_BUTTON.click();
    }

    public void openBranchesPage() {
        BRANCHES_PAGE_BUTTON.click();
    }

    public void openGroupsPage() {
        GROUPS_PAGE_BUTTON.click();
    }

    public void openSettings() {
        Configuration.timeout = 20000;
        SEARCHMASK_PAGE_MY_ACCOUNT_BUTTON.click();
        Configuration.timeout = 4000;
        SEARCHMASK_PAGE_SETTINGS_BUTTON.click();
    }

    public void logOutExpert() {
        SEARCHMASK_PAGE_MY_ACCOUNT_BUTTON.click();
        SEARCHMASK_PAGE_LOG_OUT_EXPERT_BUTTON.click();
    }

    public void logOutDealer() {
        SEARCHMASK_PAGE_MY_ACCOUNT_BUTTON.click();
        SEARCHMASK_PAGE_LOG_OUT_DEALER_BUTTON.click();
    }

    public void openCounter() {
        Configuration.timeout = 20000;
        SEARCHMASK_PAGE_MY_ACCOUNT_BUTTON.click();
        Configuration.timeout = 4000;
        SEARCHMASK_PAGE_COUNTER_BUTTON.click();
    }

    public void closeCounter() {
        SEARCHMASK_PAGE_CLOSE_COUNTER_BUTTON.click();
    }

    public void checkCounterRequests() {
        int counterRequests = Integer.parseInt(SEARCHMASK_PAGE_COUNTER_REQUESTS_NUMBER_FIELD.getText().trim());
        int prevCounterRequests = this.counter;
        System.out.println("Current counter requests: " + counterRequests);
        System.out.println("Prev counter requests: " + prevCounterRequests);
        if (counterRequests <= prevCounterRequests) {
            System.err.println("Current counter requests less or equal to prev counter requests");
        }
    }

    public void checkCounterEqualParamsOperations() {
        int counterEqualParamsOperations = Integer.parseInt(SEARCHMASK_PAGE_COUNTER_OPERATIONS_NUMBER_FIELD.getText().trim());
        int prevCounterEqualParamsOperations = this.counter;
        System.out.println("Current counter equal params operations: " + counterEqualParamsOperations);
        System.out.println("Prev counter equal params operations: " + prevCounterEqualParamsOperations);
        if (counterEqualParamsOperations > prevCounterEqualParamsOperations) {
            System.err.println("Current counter equal params operations greater than prev counter equal params operations");
        }
    }

    public void checkCounterDifferentParamsOperations() {
        int counterDifferentParamsOperations = Integer.parseInt(SEARCHMASK_PAGE_COUNTER_OPERATIONS_NUMBER_FIELD.getText().trim());
        int prevCounterDifferentParamsOperations = this.counter;
        System.out.println("Current counter different params operations: " + counterDifferentParamsOperations);
        System.out.println("Prev counter different params operations: " + prevCounterDifferentParamsOperations);
        if (counterDifferentParamsOperations <= prevCounterDifferentParamsOperations) {
            System.err.println("Current counter different params operations less or equal to prev counter different params operations");
        }
    }

    public void checkCounterPdfCreated() {
        int counterPdfCreated = Integer.parseInt(SEARCHMASK_PAGE_COUNTER_PDF_CREATED_NUMBER_FIELD.getText().trim());
        int prevCounterPdfCreated = this.counter;
        System.out.println("Current counter pdf created: " + counterPdfCreated);
        System.out.println("Prev counter pdf created: " + prevCounterPdfCreated);
        if (counterPdfCreated <= prevCounterPdfCreated) {
            System.err.println("Current counter pdf created less or equal to prev counter pdf created");
        }
    }

    public void saveCounterRequests() {
        this.counter = Integer.parseInt(SEARCHMASK_PAGE_COUNTER_REQUESTS_NUMBER_FIELD.getText().trim());
    }

    public void saveCounterOperations() {
        this.counter = Integer.parseInt(SEARCHMASK_PAGE_COUNTER_OPERATIONS_NUMBER_FIELD.getText().trim());
    }

    public void saveCounterPdfCreated() {
        this.counter = Integer.parseInt(SEARCHMASK_PAGE_COUNTER_PDF_CREATED_NUMBER_FIELD.getText().trim());
    }

    @Override
    public SelenideElement elementContainer() {
        return $(By.xpath("//app-header"));
    }
}
