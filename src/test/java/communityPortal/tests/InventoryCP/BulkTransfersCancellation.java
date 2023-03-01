package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Tables;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import static constansts.Domain.*;
import static constansts.Header.*;
import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class BulkTransfersCancellation extends BaseTest {

    private final String supplyLocationFrom = SUPPLY_LOCATION_1;
    private final String supplyLocationTo = SUPPLY_LOCATION_2;
    public CommunityPortalMainPage communityPortalMainPage;
    public SupplyConsolePage supplyConsolePage;
    public Tables tables;

    @BeforeMethod
    public void setUpClass() throws Exception {
        log("Target Environment: " + Utils.getTargetEnvironment());
        communityPortalMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();
        tables = loginPage.getTables();

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        supplyConsolePage = communityPortalMainPage.navigateToSupplyLocation(supplyLocationFrom);
    }

    @Test(priority = 1)
    public void Can_doBulk_transfersBy_Doses_form_one_Clinic_to_Another_And_Cancel_as_Clinician_Community() throws Exception {
        TestcaseID = "223359";
        String vaccine1 = "COMIRNATY (Pfizer) - EK4241";
        String vaccine2 = "SPIKEVAX 6mo-5y";
        String vaccine3 = "JANSSEN COVID-19 VACCINE";
        double doses = 10;

        log("/----Count Remaining Supplies --*/");
        Map<String, String> supplyContainerFromLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_1_1);
        double remainingDosesBeforeDistribution1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingDosesBeforeDistribution1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingDosesBeforeDistribution1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);

        log("/----Select Items to Transfer and Submit --*/");
        tables.clickSupplyLocationCheckBox(supplyContainerFromLocation1)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation2)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation3);
        supplyConsolePage.clickBulkTransfersButton();

        log("/---- Get Dose Conversation Factor, Add doses And Perform Transfer --*/");
        Map<String, String> vaccine1TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine1, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> vaccine2TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine2, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> vaccine3TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine3, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);

        tables.typeDosesIntoTransferRow(vaccine1TransferRow, String.valueOf(doses));
        tables.typeDosesIntoTransferRow(vaccine2TransferRow, String.valueOf(doses));
        tables.typeDosesIntoTransferRow(vaccine3TransferRow, String.valueOf(doses));
        supplyConsolePage.selectSupplyLocation(supplyLocationTo).
                clickBulkTransfersModalButton().clickBulkTransfersCloseButton();

        log("/---- Count and Validate Remaining Supplies After Transfer --*/");
        tables.hardWait(2);//needs couple seconds to refresh results
        double remainingDosesAfterDistribution1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQuantityAfterDistribution1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingDosesAfterCalculationDistribution1_1 = remainingDosesBeforeDistribution1_1 - doses;
        assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);

        double remainingDosesAfterDistribution1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQtyAfterDistribution1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingDosesAfterCalculationDistribution1_2 = remainingDosesBeforeDistribution1_2 - doses;
        assertEquals(remainingDosesAfterDistribution1_2, remainingDosesAfterCalculationDistribution1_2);

        double remainingDosesAfterDistribution1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQtyAfterDistribution1_3 = tables.getRemainingQty(supplyContainerFromLocation3);
        double remainingDosesAfterCalculationDistribution1_3 = remainingDosesBeforeDistribution1_3 - doses;
        assertEquals(remainingDosesAfterDistribution1_3, remainingDosesAfterCalculationDistribution1_3);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

        log("/----Count Remaining Supplies Before Transaction --*/");
        Map<String, String> supplyContainerToLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_2_1);
        double remainingDosesBeforeLocationDistribution2_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingDosesBeforeLocationDistribution2_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingDosesBeforeLocationDistribution2_3 = tables.getRemainingDoses(supplyContainerToLocation3);

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        communityPortalMainPage.navigateToSupplyLocation(supplyLocationFrom);
        supplyConsolePage.clickTransactionsTab();

        log("/----Cancel Transfer --*/");
        tables.checkShippedTransactionsOutgoingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine1));
        tables.checkShippedTransactionsOutgoingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine2));
        tables.checkShippedTransactionsOutgoingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine3));
        supplyConsolePage.clickBulkCancelButton().cancelBulkTransfer();

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        communityPortalMainPage.selectRelatedTab();
        double remainingDosesAfterLocationDistribution1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingDosesAfterLocationDistribution1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingDosesAfterLocationDistribution1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);

        assertEquals(remainingDosesBeforeDistribution1_1, remainingDosesAfterLocationDistribution1_1);
        assertEquals(remainingDosesAfterLocationDistribution1_2, remainingDosesBeforeDistribution1_2);
        assertEquals(remainingDosesBeforeDistribution1_3, remainingDosesAfterLocationDistribution1_3);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

        log("/----Count Remaining Supplies After Cancel Transaction --*/");
        double remainingDosesAfterLocationDistribution2_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingDosesAfterLocationDistribution2_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingDosesAfterLocationDistribution2_3 = tables.getRemainingDoses(supplyContainerToLocation3);

        assertEquals(remainingDosesBeforeLocationDistribution2_1, remainingDosesAfterLocationDistribution2_1);
        assertEquals(remainingDosesAfterLocationDistribution2_2, remainingDosesAfterLocationDistribution2_2);
        assertEquals(remainingDosesAfterLocationDistribution2_3, remainingDosesBeforeLocationDistribution2_3);
    }

    @Test()
    public void Can_doBulk_transfersBy_Quantity_form_one_Clinic_to_Another_and_Cancel_as_PPHIS_Community() throws Exception {
        TestcaseID = "223359";
        String vaccine1 = "COMIRNATY (Pfizer) - EK4241";
        String vaccine2 = "SPIKEVAX 6mo-5y";
        String vaccine3 = "JANSSEN COVID-19 VACCINE";
        double quantity = 1;

        log("/----Count Remaining Supplies --*/");
        Map<String, String> supplyContainerFromLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_1_1);
        double remainingQtyBeforeDistribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingQtyBeforeDistribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingQtyBeforeDistribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

        log("/----Select Items to Transfer and Submit --*/");
        tables.clickSupplyLocationCheckBox(supplyContainerFromLocation1)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation2)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation3);
        supplyConsolePage.clickBulkTransfersButton();

        log("/---- Get Dose Conversation Factor, Add doses And Perform Transfer --*/");
        Map<String, String> vaccine1TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine1, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> vaccine2TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine2, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> vaccine3TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine3, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);

        tables.typeQtyIntoTransferRow(vaccine1TransferRow, String.valueOf(quantity));
        tables.typeQtyIntoTransferRow(vaccine2TransferRow, String.valueOf(quantity));
        tables.typeQtyIntoTransferRow(vaccine3TransferRow, String.valueOf(quantity));
        supplyConsolePage.selectSupplyLocation(supplyLocationTo).
                clickBulkTransfersModalButton().clickBulkTransfersCloseButton();

        log("/---- Count and Validate Remaining Supplies After Transfer --*/");
        tables.hardWait(2);//needs couple seconds to refresh results
        double remainingQuantityAfterDistribution1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingQtyAfterDistribution1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingQtyAfterDistribution1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

        assertEquals(remainingQuantityAfterDistribution1_1, remainingQtyBeforeDistribution_1_1 - quantity);
        assertEquals(remainingQtyAfterDistribution1_2, remainingQtyBeforeDistribution_1_2 - quantity);
        assertEquals(remainingQtyAfterDistribution1_3, remainingQtyBeforeDistribution_1_3 - quantity);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

        log("/----Count Remaining Supplies Before Transaction --*/");
        Map<String, String> supplyContainerToLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_2_1);
        double remainingQtyBeforeLocationDistribution2_1 = tables.getRemainingQty(supplyContainerToLocation1);
        double remainingQtyBeforeLocationDistribution2_2 = tables.getRemainingQty(supplyContainerToLocation2);
        double remainingQtyBeforeLocationDistribution2_3 = tables.getRemainingQty(supplyContainerToLocation3);

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        communityPortalMainPage.navigateToSupplyLocation(supplyLocationFrom);
        supplyConsolePage.clickTransactionsTab();

        log("/----Cancel Transfer --*/");
        tables.checkShippedTransactionsOutgoingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine1));
        tables.checkShippedTransactionsOutgoingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine2));
        tables.checkShippedTransactionsOutgoingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine3));
        supplyConsolePage.clickBulkCancelButton().cancelBulkTransfer();

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        communityPortalMainPage.selectRelatedTab();
        double remainingQtyAfterCancelLocationDistribution1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingQtyAfterCancelLocationDistribution1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingQtyAfterCancelLocationDistribution1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

        assertEquals(remainingQtyBeforeDistribution_1_1, remainingQtyAfterCancelLocationDistribution1_1);
        assertEquals(remainingQtyBeforeDistribution_1_2, remainingQtyAfterCancelLocationDistribution1_2);
        assertEquals(remainingQtyBeforeDistribution_1_3, remainingQtyAfterCancelLocationDistribution1_3);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

        log("/----Count Remaining Supplies After Cancel Transaction --*/");
        double remainingQtyAfterCancelLocationDistribution2_1 = tables.getRemainingQty(supplyContainerToLocation1);
        double remainingQtyAfterCancelLocationDistribution2_2 = tables.getRemainingQty(supplyContainerToLocation2);
        double remainingQtyAfterCancelLocationDistribution2_3 = tables.getRemainingQty(supplyContainerToLocation3);

        assertEquals(remainingQtyBeforeLocationDistribution2_1, remainingQtyAfterCancelLocationDistribution2_1);
        assertEquals(remainingQtyBeforeLocationDistribution2_2, remainingQtyAfterCancelLocationDistribution2_2);
        assertEquals(remainingQtyBeforeLocationDistribution2_3, remainingQtyAfterCancelLocationDistribution2_3);

    }
}