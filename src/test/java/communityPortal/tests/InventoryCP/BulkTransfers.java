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

import static Constansts.Domain.*;
import static Constansts.Header.*;
import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class BulkTransfers extends BaseTest {

    public CommunityPortalMainPage communityPortalMainPage;
    public SupplyConsolePage supplyConsolePage;
    public Tables tables;
    private final String supplyLocationFrom = SUPPLY_LOCATION_1;
    private final String supplyLocationTo = SUPPLY_LOCATION_2;

    @BeforeMethod
    public void setUpClass() throws Exception {
        log("Target Environment: " + Utils.getTargetEnvironment());
        communityPortalMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
        tables = loginPage.getTables();

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        supplyConsolePage = communityPortalMainPage.navigateToSupplyLocationRelatedTab(supplyLocationFrom);
    }

    @Test(priority = 1)
    public void Can_do_Bulk_transfers_by_Dosages_form_one_Clinic_to_Another_as_PPHIS_BCVAXDEVIT() throws Exception {
        TestcaseID = "223359";
        String vaccine1 = "COMIRNATY (Pfizer) - EK4241";
        String vaccine2 = "VAXZEVRIA";
        String vaccine3 = "JANSSEN COVID-19 VACCINE";
        double doses = 10;

        log("/----Count Remaining Supplies --*/");
        Map<String, String> supplyContainerFromLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_1_1);
        tables.hardWait(2);
        double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingDoses_before_Distribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingDoses_before_Distribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);

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
        tables.hardWait(2);
        double conversationRate1 = tables.getConversionRateFromTransferRow(vaccine1TransferRow);
        double conversationRate2 = tables.getConversionRateFromTransferRow(vaccine2TransferRow);
        double conversationRate3 = tables.getConversionRateFromTransferRow(vaccine3TransferRow);

        tables.typeDosesIntoTransferRow(vaccine1TransferRow, String.valueOf(doses));
        tables.typeDosesIntoTransferRow(vaccine2TransferRow, String.valueOf(doses));
        tables.typeDosesIntoTransferRow(vaccine3TransferRow, String.valueOf(doses));
        supplyConsolePage.selectSupplyLocation(supplyLocationTo).clickBulkTransfersModalButton().clickBulkTransfersCloseButton();

        log("/---- Count and Validate Remaining Supplies After Transfer --*/");
        tables.hardWait(2);
        double remainingDoses_after_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQuantity_after_Distribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingDoses_after_Calculation_Distribution_1_1 = remainingDoses_before_Distribution_1_1 - doses;
        assertEquals(remainingDoses_after_Distribution_1_1, remainingDoses_after_Calculation_Distribution_1_1);
        assertEquals(df.format(remainingQuantity_after_Distribution_1_1), df.format(remainingDoses_after_Calculation_Distribution_1_1 / conversationRate1));

        double remainingDoses_after_Distribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQty_after_Distribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingDoses_after_Calculation_Distribution_1_2 = remainingDoses_before_Distribution_1_2 - doses;
        assertEquals(remainingDoses_after_Distribution_1_2, remainingDoses_after_Calculation_Distribution_1_2);
        assertEquals(df.format(remainingQty_after_Distribution_1_2), df.format(remainingDoses_after_Calculation_Distribution_1_2 / conversationRate2));

        double remainingDoses_after_Distribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQty_after_Distribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);
        double remainingDoses_after_Calculation_Distribution_1_3 = remainingDoses_before_Distribution_1_3 - doses;
        assertEquals(remainingDoses_after_Distribution_1_3, remainingDoses_after_Calculation_Distribution_1_3);
        assertEquals(df.format(remainingQty_after_Distribution_1_3), df.format(remainingDoses_after_Calculation_Distribution_1_3 / conversationRate3));

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        communityPortalMainPage.navigateToSupplyLocationRelatedTab(supplyLocationTo);

        log("/----Count Remaining Supplies Before Transaction --*/");
        Map<String, String> supplyContainerToLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_2_1);
        tables.hardWait(3);
        double remainingDoses_before_Location_Distribution_1_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingDoses_before_Location_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingDoses_before_Location_Distribution_1_3 = tables.getRemainingDoses(supplyContainerToLocation3);

        log("/----Go To Transaction Accept Incoming Transfer --*/");
        supplyConsolePage.clickTransactionsTab();
        tables.hardWait(2);
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine1));
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine2));
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine3));
        supplyConsolePage.acceptBulkTransferToDistribution(SUPPLY_DISTRIBUTION_2_1);
        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        communityPortalMainPage.selectRelatedTab();
        tables.hardWait(2);
        double remainingDoses_after_Location_Distribution_1_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingQty_after_Location_Distribution_1_1 = tables.getRemainingQty(supplyContainerToLocation1);
        double remainingDoses_after_Calculation_Location_Distribution_1_1 = remainingDoses_before_Location_Distribution_1_1 + doses;

        assertEquals(remainingDoses_after_Location_Distribution_1_1, remainingDoses_after_Calculation_Location_Distribution_1_1);
        assertEquals(df.format(remainingQty_after_Location_Distribution_1_1),
                df.format(remainingDoses_after_Calculation_Location_Distribution_1_1 / conversationRate1));

        double remainingDoses_after_Location_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingQty_after_Location_Distribution_1_2 = tables.getRemainingQty(supplyContainerToLocation2);
        double remainingDoses_after_Calculation_Location_Distribution_1_2 = remainingDoses_before_Location_Distribution_1_2 + doses;

        assertEquals(remainingDoses_after_Location_Distribution_1_2, remainingDoses_after_Calculation_Location_Distribution_1_2);
        assertEquals(df.format(remainingQty_after_Location_Distribution_1_2),
                df.format(remainingDoses_after_Calculation_Location_Distribution_1_2 / conversationRate2));


        double remainingDoses_after_Location_Distribution_1_3 = tables.getRemainingDoses(supplyContainerToLocation3);
        double remainingQty_after_Location_Distribution_1_3 = tables.getRemainingQty(supplyContainerToLocation3);

        double remainingDoses_after_Calculation_Location_Distribution_1_3 = remainingDoses_before_Location_Distribution_1_3 + doses;

        assertEquals(remainingDoses_after_Location_Distribution_1_3, remainingDoses_after_Calculation_Location_Distribution_1_3);
        assertEquals(df.format(remainingQty_after_Location_Distribution_1_3), df.format(remainingDoses_after_Calculation_Location_Distribution_1_3 / conversationRate3));
    }

    @Test(priority = 1)
    public void Can_do_Bulk_transfers_by_Quantity_form_one_Clinic_to_Another_as_PPHIS_BCVAXDEVIT() throws Exception {
        TestcaseID = "223359";
        String vaccine1 = "COMIRNATY (Pfizer) - EK4241";
        String vaccine2 = "VAXZEVRIA";
        String vaccine3 = "JANSSEN COVID-19 VACCINE";
        double quantity = 1;

        log("/----Count Remaining Supplies --*/");
        Map<String, String> supplyContainerFromLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_1_1);
        tables.hardWait(2);
        double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQty_before_Distribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);

        double remainingDoses_before_Distribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQty_before_Distribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);

        double remainingDoses_before_Distribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQty_before_Distribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

        log("/----Select Items to Transfer and Submit --*/");
        tables.clickSupplyLocationCheckBox(supplyContainerFromLocation1)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation2)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation3);
        supplyConsolePage.clickBulkTransfersButton();

        log("/---- Get Dose Conversation Factor, Add quantity And Perform Transfer --*/");
        Map<String, String> vaccine1TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine1, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> vaccine2TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine2, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> vaccine3TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine3, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_1);
        tables.hardWait(2);
        double conversationRate1 = tables.getConversionRateFromTransferRow(vaccine1TransferRow);
        double conversationRate2 = tables.getConversionRateFromTransferRow(vaccine2TransferRow);
        double conversationRate3 = tables.getConversionRateFromTransferRow(vaccine3TransferRow);

        tables.typeQtyIntoTransferRow(vaccine1TransferRow, String.valueOf(quantity));
        tables.typeQtyIntoTransferRow(vaccine2TransferRow, String.valueOf(quantity));
        tables.typeQtyIntoTransferRow(vaccine3TransferRow, String.valueOf(quantity));
        supplyConsolePage.selectSupplyLocation(supplyLocationTo).clickBulkTransfersModalButton().clickBulkTransfersCloseButton();

        log("/---- Count and Validate Remaining Supplies After Transfer --*/");
        tables.hardWait(2);
        double remainingDoses_after_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQuantity_after_Distribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        assertEquals(remainingDoses_after_Distribution_1_1, remainingDoses_before_Distribution_1_1 - conversationRate1);
        assertEquals(remainingQuantity_after_Distribution_1_1, remainingQty_before_Distribution_1_1 - quantity);

        double remainingDoses_after_Distribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQty_after_Distribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        assertEquals(remainingDoses_after_Distribution_1_2, remainingDoses_before_Distribution_1_2 - conversationRate2);
        assertEquals(remainingQty_after_Distribution_1_2, remainingQty_before_Distribution_1_2 - quantity);

        double remainingDoses_after_Distribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQty_after_Distribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);
        assertEquals(remainingDoses_after_Distribution_1_3, remainingDoses_before_Distribution_1_3 - conversationRate3);
        assertEquals(remainingQty_after_Distribution_1_3, remainingQty_before_Distribution_1_3 - quantity);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        communityPortalMainPage.navigateToSupplyLocationRelatedTab(supplyLocationTo);

        log("/----Count Remaining Supplies Before Transaction --*/");
        Map<String, String> supplyContainerToLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_2_1);
        tables.hardWait(2);
        double remainingDoses_before_Location_Distribution_1_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingQty_before_Location_Distribution_1_1 = tables.getRemainingQty(supplyContainerToLocation1);

        double remainingDoses_before_Location_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingQty_before_Location_Distribution_1_2 = tables.getRemainingQty(supplyContainerToLocation2);

        double remainingDoses_before_Location_Distribution_1_3 = tables.getRemainingDoses(supplyContainerToLocation3);
        double remainingQty_before_Location_Distribution_1_3 = tables.getRemainingQty(supplyContainerToLocation3);

        log("/----Go To Transaction Accept Incoming Transfer --*/");
        supplyConsolePage.clickTransactionsTab();
        tables.hardWait(2);
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine1));
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine2));
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine3));
        supplyConsolePage.acceptBulkTransferToDistribution(SUPPLY_DISTRIBUTION_2_1);

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        communityPortalMainPage.selectRelatedTab();
        tables.hardWait(2);
        double remainingDoses_after_Location_Distribution_1_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingQty_after_Location_Distribution_1_1 = tables.getRemainingQty(supplyContainerToLocation1);
        assertEquals(remainingDoses_after_Location_Distribution_1_1, remainingDoses_before_Location_Distribution_1_1 + conversationRate1);
        assertEquals(remainingQty_after_Location_Distribution_1_1, remainingQty_before_Location_Distribution_1_1 + quantity);


        double remainingDoses_after_Location_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingQty_after_Location_Distribution_1_2 = tables.getRemainingQty(supplyContainerToLocation2);
        assertEquals(remainingDoses_after_Location_Distribution_1_2, remainingDoses_before_Location_Distribution_1_2 + conversationRate2);
        assertEquals(remainingQty_after_Location_Distribution_1_2, remainingQty_before_Location_Distribution_1_2 + quantity);


        double remainingDoses_after_Location_Distribution_1_3 = tables.getRemainingDoses(supplyContainerToLocation3);
        double remainingQty_after_Location_Distribution_1_3 = tables.getRemainingQty(supplyContainerToLocation3);
        assertEquals(remainingDoses_after_Location_Distribution_1_3, remainingDoses_before_Location_Distribution_1_3 + conversationRate3);
        assertEquals(remainingQty_after_Location_Distribution_1_3, remainingQty_before_Location_Distribution_1_3 + quantity);
    }

    private static Map<String, String> searchCriteria(String vaccine, String fromDistributionLocation) {
        return ImmutableMap.of
                (SUPPLY_CONTAINER_NAME_, vaccine, SUPPLY_DISTRIBUTION_DESCRIPTION_, fromDistributionLocation);
    }
}