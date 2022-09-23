package de.cardetecktive.app.archive;

import de.cardetecktive.app.ApplicationBaseTest;
import de.cardetecktive.app.services.archive.ArchiveSearchAds;
import de.cardetecktive.app.services.archive.ArchiveSearchCount;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collection;

public class ArchiveSearchTest extends ApplicationBaseTest {

    @Autowired
    private ArchiveSearchAds archiveSearchAds;
    @Autowired
    private ArchiveSearchCount archiveSearchCount;

    @Test(description = "Archive search ads functionality")
    public void archiveSearchAdsFunctionality() {
        Collection<ArchiveSearchAds.ArchiveSearchAdsReader.Items> it
                = archiveSearchAds.getArchiveSearchAdsResponse();

        System.out.println("Items in archive search: " + it);

        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(it)
                .as("Archive search result is empty")
                .isNotEmpty();

        assertions.assertThat(
                ArchiveSearchAds.removeItemsWhenMinYearGreaterThanOrEqual(it))
                .as("Archive search result contains elements when Min Year < "
                        + ArchiveSearchAds.MIN_YEAR + " values are false")
                .isEmpty();

        assertions.assertThat(
                ArchiveSearchAds.removeItemsWhenFuelTypesEqual(it))
                .as("Archive search result contains elements when Fuel Types not equal to "
                        + ArchiveSearchAds.FUEL_TYPES + " values are false")
                .isEmpty();

        assertions.assertThat(
                ArchiveSearchAds.removeItemsWhenMakeAndModelEqual(it))
                .as("Archive search result contains elements when Make not equal to "
                        + ArchiveSearchAds.MAKE + " and Model not equal to "
                        + ArchiveSearchAds.MODEL + " values are false")
                .isEmpty();

        assertions.assertThat(
                        ArchiveSearchAds.removeItemsWhenVehicleTypeEqual(it))
                .as("Archive search result contains elements when Vehicle Type not equal to "
                        + ArchiveSearchAds.VEHICLE_TYPE + " values are false")
                .isEmpty();

        assertions.assertThat(
                        ArchiveSearchAds.removeItemsWhenAdOnlineFromGreaterThanOrEqual(it))
                .as("Archive search result contains elements when Ad Online From < "
                        + ArchiveSearchAds.AD_ONLINE_FROM + " values are false")
                .isEmpty();

        assertions.assertThat(
                        ArchiveSearchAds.removeItemsWhenAdOnlineToLessThanOrEqual(it))
                .as("Archive search result contains elements when Ad Online To > "
                        + ArchiveSearchAds.AD_ONLINE_TO + " values are false")
                .isEmpty();
        assertions.assertAll();
    }

    @Test(description = "Archive search count functionality")
    public void archiveSearchCountFunctionality() throws IOException {
        long currentTotal = archiveSearchCount.getCurrentTotal();
        long lastTotal = archiveSearchCount.getLastTotal();
        long diff = currentTotal - lastTotal;
        archiveSearchCount.saveTotal(currentTotal);

        System.out.println("currentTotal: " + currentTotal);
        System.out.println("lastTotal: " + lastTotal);
        System.out.println("diff: " + diff);

        Assertions.assertThat(diff)
                .as("Archive search count diff (current-last) is less than 0")
                .isGreaterThanOrEqualTo(0L);
    }
}
