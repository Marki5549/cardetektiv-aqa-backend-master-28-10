package de.cardetecktive.app.webpages.carsearch;

import com.codeborne.selenide.Condition;
import de.cardetecktive.app.webpages.DynamicWebPage;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import org.springframework.stereotype.Component;

@Component
public class CarSearchPage extends DynamicWebPage {

    private final CarSearchHeader carSearchHeader;

    public CarSearchPage(CarSearchHeader carSearchHeader) {
        this.carSearchHeader = carSearchHeader;
    }

    @Override
    public boolean isCurrentPage() {
        return carSearchHeader.elementContainer()
                .shouldBe(Condition.appear)
                .exists();
    }
}

