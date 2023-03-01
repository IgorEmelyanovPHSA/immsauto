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
public class BulkTransfers extends BaseTest {

    public CommunityPortalMainPage communityPortalMainPage;
    public SupplyConsolePage supplyConsolePage;
    public Tables tables;
    private final String supplyLocationFrom = SUPPLY_LOCATION_1;
    private final String supplyLocationTo = SUPPLY_LOCATION_2;

    @BeforeMethod
    public void setUpClass() throws Exception {
        log("Target Environment: " + Utils.getTargetEnvironment());
        communityPortalMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();
        tables = loginPage.getTables();
    }

    @Test(priority = 1)
    public void Can_doBulk_transfersBy_Dosages_form_one_Clinic_to_Another_as_Clinician_Community() throws Exception {
        TestcaseID = "223359";
        String vaccine1 = "COMIRNATY (Pfizer) - EK4241";
        String vaccine2 = "SPIKEVAX 6mo-5y";
        String vaccine3 = "JANSSEN COVID-19 VACCINE";
        double doses = 10;

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        supplyConsolePage = communityPortalMainPage.navigateToSupplyLocation(supplyLocationFrom);

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
        double conversationRate1 = tables.getConversionRateFromTransferRow(vaccine1TransferRow);
        double conversationRate2 = tables.getConversionRateFromTransferRow(vaccine2TransferRow);
        double conversationRate3 = tables.getConversionRateFromTransferRow(vaccine3TransferRow);

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
        assertEquals(df.format(remainingQuantityAfterDistribution1_1),
                df.format(remainingDosesAfterCalculationDistribution1_1 / conversationRate1));

        double remainingDosesAfterDistribution1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQtyAfterDistribution1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingDosesAfterCalculationDistribution1_2 = remainingDosesBeforeDistribution1_2 - doses;
        assertEquals(remainingDosesAfterDistribution1_2, remainingDosesAfterCalculationDistribution1_2);
        assertEquals(df.format(remainingQtyAfterDistribution1_2),
                df.format(remainingDosesAfterCalculationDistribution1_2 / conversationRate2));

        double remainingDosesAfterDistribution1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQtyAfterDistribution1_3 = tables.getRemainingQty(supplyContainerFromLocation3);
        double remainingDosesAfterCalculationDistribution1_3 = remainingDosesBeforeDistribution1_3 - doses;
        assertEquals(remainingDosesAfterDistribution1_3, remainingDosesAfterCalculationDistribution1_3);
        assertEquals(df.format(remainingQtyAfterDistribution1_3),
                df.format(remainingDosesAfterCalculationDistribution1_3 / conversationRate3));

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

        log("/----Count Remaining Supplies Before Transaction --*/");
        Map<String, String> supplyContainerToLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_2_1);
        double remainingDosesBeforeLocationDistribution1_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingDosesBeforeLocationDistribution1_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingDosesBeforeLocationDistribution1_3 = tables.getRemainingDoses(supplyContainerToLocation3);

        log("/----Go To Transaction Accept Incoming Transfer --*/");
        supplyConsolePage.clickTransactionsTab();
        tables.hardWait(2);
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine1));
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine2));
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine3));
        supplyConsolePage.acceptBulkTransferToDistribution(SUPPLY_DISTRIBUTION_2_1);
        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        communityPortalMainPage.selectRelatedTab();
        double remainingDosesAfterLocationDistribution1_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingQtyAfterLocationDistribution1_1 = tables.getRemainingQty(supplyContainerToLocation1);
        double remainingDosesAfterCalculationLocationDistribution1_1 = remainingDosesBeforeLocationDistribution1_1 + doses;

        assertEquals(remainingDosesAfterLocationDistribution1_1, remainingDosesAfterCalculationLocationDistribution1_1);
        assertEquals(df.format(remainingQtyAfterLocationDistribution1_1),
                df.format(remainingDosesAfterCalculationLocationDistribution1_1 / conversationRate1));

        double remainingDosesAfterLocationDistribution1_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingQtyAfterLocationDistribution1_2 = tables.getRemainingQty(supplyContainerToLocation2);
        double remainingDosesAfterCalculationLocationDistribution1_2 = remainingDosesBeforeLocationDistribution1_2 + doses;

        assertEquals(remainingDosesAfterLocationDistribution1_2, remainingDosesAfterCalculationLocationDistribution1_2);
        assertEquals(df.format(remainingQtyAfterLocationDistribution1_2),
                df.format(remainingDosesAfterCalculationLocationDistribution1_2 / conversationRate2));


        double remainingDosesAfterLocationDistribution1_3 = tables.getRemainingDoses(supplyContainerToLocation3);
        double remainingQtyAfterLocationDistribution1_3 = tables.getRemainingQty(supplyContainerToLocation3);
        double remainingDosesAfterCalculationLocationDistribution1_3 = remainingDosesBeforeLocationDistribution1_3 + doses;

        assertEquals(remainingDosesAfterLocationDistribution1_3, remainingDosesAfterCalculationLocationDistribution1_3);
        assertEquals(df.format(remainingQtyAfterLocationDistribution1_3),
                df.format(remainingDosesAfterCalculationLocationDistribution1_3 / conversationRate3));
    }

    @Test()
    public void Can_doBulk_transfersBy_Quantity_form_one_Clinic_to_Another_as_PPHIS_Community() throws Exception {
        TestcaseID = "223359";
        String vaccine1 = "COMIRNATY (Pfizer) - EK4241";
        String vaccine2 = "SPIKEVAX 6mo-5y";
        String vaccine3 = "JANSSEN COVID-19 VACCINE";
        double quantity = 1;

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        supplyConsolePage = communityPortalMainPage.navigateToSupplyLocation(supplyLocationFrom);

        log("/----Count Remaining Supplies --*/");
        Map<String, String> supplyContainerFromLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_1_1);
        Map<String, String> supplyContainerFromLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_1_1);
        double remainingDosesBeforeDistribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQtyBeforeDistribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);

        double remainingDosesBeforeDistribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQtyBeforeDistribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);

        double remainingDosesBeforeDistribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQtyBeforeDistribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

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

        double conversationRate1 = tables.getConversionRateFromTransferRow(vaccine1TransferRow);
        double conversationRate2 = tables.getConversionRateFromTransferRow(vaccine2TransferRow);
        double conversationRate3 = tables.getConversionRateFromTransferRow(vaccine3TransferRow);

        tables.typeQtyIntoTransferRow(vaccine1TransferRow, String.valueOf(quantity));
        tables.typeQtyIntoTransferRow(vaccine2TransferRow, String.valueOf(quantity));
        tables.typeQtyIntoTransferRow(vaccine3TransferRow, String.valueOf(quantity));
        supplyConsolePage.selectSupplyLocation(supplyLocationTo).
                clickBulkTransfersModalButton().clickBulkTransfersCloseButton();

        log("/---- Count and Validate Remaining Supplies After Transfer --*/");
        tables.hardWait(2);
        double remainingDosesAfterDistribution1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQuantityAfterDistribution1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        assertEquals(remainingDosesAfterDistribution1_1, remainingDosesBeforeDistribution_1_1 - conversationRate1);
        assertEquals(remainingQuantityAfterDistribution1_1, remainingQtyBeforeDistribution_1_1 - quantity);

        double remainingDosesAfterDistribution1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQtyAfterDistribution1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        assertEquals(remainingDosesAfterDistribution1_2, remainingDosesBeforeDistribution_1_2 - conversationRate2);
        assertEquals(remainingQtyAfterDistribution1_2, remainingQtyBeforeDistribution_1_2 - quantity);

        double remainingDosesAfterDistribution1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQtyAfterDistribution1_3 = tables.getRemainingQty(supplyContainerFromLocation3);
        assertEquals(remainingDosesAfterDistribution1_3, remainingDosesBeforeDistribution_1_3 - conversationRate3);
        assertEquals(remainingQtyAfterDistribution1_3, remainingQtyBeforeDistribution_1_3 - quantity);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

        log("/----Count Remaining Supplies Before Transaction --*/");
        Map<String, String> supplyContainerToLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_2_1);
        double remainingDosesBeforeLocationDistribution1_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingQtyBeforeLocationDistribution1_1 = tables.getRemainingQty(supplyContainerToLocation1);

        double remainingDosesBeforeLocationDistribution1_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingQtyBeforeLocationDistribution1_2 = tables.getRemainingQty(supplyContainerToLocation2);

        double remainingDosesBeforeLocationDistribution1_3 = tables.getRemainingDoses(supplyContainerToLocation3);
        double remainingQtyBeforeLocationDistribution1_3 = tables.getRemainingQty(supplyContainerToLocation3);

        log("/----Go To Transaction Accept Incoming Transfer --*/");
        supplyConsolePage.clickTransactionsTab();
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine1));
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine2));
        tables.checkShippedTransactionsIncomingCheckbox(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine3));
        supplyConsolePage.acceptBulkTransferToDistribution(SUPPLY_DISTRIBUTION_2_1);

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        communityPortalMainPage.selectRelatedTab();
        double remainingDosesAfterLocationDistribution1_1 = tables.getRemainingDoses(supplyContainerToLocation1);
        double remainingQtyAfterLocationDistribution1_1 = tables.getRemainingQty(supplyContainerToLocation1);
        assertEquals(remainingDosesAfterLocationDistribution1_1, remainingDosesBeforeLocationDistribution1_1 + conversationRate1);
        assertEquals(remainingQtyAfterLocationDistribution1_1, remainingQtyBeforeLocationDistribution1_1 + quantity);

        double remainingDosesAfterLocationDistribution1_2 = tables.getRemainingDoses(supplyContainerToLocation2);
        double remainingQtyAfterLocationDistribution1_2 = tables.getRemainingQty(supplyContainerToLocation2);
        assertEquals(remainingDosesAfterLocationDistribution1_2, remainingDosesBeforeLocationDistribution1_2 + conversationRate2);
        assertEquals(remainingQtyAfterLocationDistribution1_2, remainingQtyBeforeLocationDistribution1_2 + quantity);

        double remainingDosesAfterLocationDistribution1_3 = tables.getRemainingDoses(supplyContainerToLocation3);
        double remainingQtyAfterLocationDistribution1_3 = tables.getRemainingQty(supplyContainerToLocation3);
        assertEquals(remainingDosesAfterLocationDistribution1_3, remainingDosesBeforeLocationDistribution1_3 + conversationRate3);
        assertEquals(remainingQtyAfterLocationDistribution1_3, remainingQtyBeforeLocationDistribution1_3 + quantity);
    }

    @Test()
    public void Can_doBulk_transfersBy_Quantity_within_the_same_Clinic_as_PPHIS_CommunityT() throws Exception {
        TestcaseID = "223359";
        String vaccine1 = "COMIRNATY (Pfizer) - EK4241";
        String vaccine2 = "VAXZEVRIA";
        String vaccine3 = "JANSSEN COVID-19 VACCINE";
        double quantity = 1;

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        supplyConsolePage = communityPortalMainPage.navigateToSupplyLocation(SUPPLY_LOCATION_2);

        log("/----Count Remaining Supplies --*/");
        Map<String, String> supplyContainerFromLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_1_2);
        Map<String, String> supplyContainerFromLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_1_2);
        Map<String, String> supplyContainerFromLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_1_2);

        Map<String, String> supplyContainerToDistribution1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToDistribution2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToDistribution3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_2_1);

        // First distribution data
        double remainingDosesBeforeDistribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQtyBeforeDistribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingDosesBeforeDistribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQtyBeforeDistribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingDosesBeforeDistribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQtyBeforeDistribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

        // Second distribution data
        double remainingQtyBeforeDistribution_2_1 = tables.getRemainingQty(supplyContainerToDistribution1);
        double remainingQtyBeforeDistribution_2_2 = tables.getRemainingQty(supplyContainerToDistribution2);
        double remainingQtyBeforeDistribution_2_3 = tables.getRemainingQty(supplyContainerToDistribution3);

        log("/----Select Items to Transfer and Submit --*/");
        tables.clickSupplyLocationCheckBox(supplyContainerFromLocation1)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation2)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation3);
        supplyConsolePage.clickBulkTransfersButton();

        Map<String, String> vaccine1TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine1, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_2);
        Map<String, String> vaccine2TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine2, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_2);
        Map<String, String> vaccine3TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine3, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_2);

        double conversationRate1 = tables.getConversionRateFromTransferRow(vaccine1TransferRow);
        double conversationRate2 = tables.getConversionRateFromTransferRow(vaccine2TransferRow);
        double conversationRate3 = tables.getConversionRateFromTransferRow(vaccine3TransferRow);

        tables.typeQtyIntoTransferRow(vaccine1TransferRow, String.valueOf(quantity));
        tables.typeQtyIntoTransferRow(vaccine2TransferRow, String.valueOf(quantity));
        tables.typeQtyIntoTransferRow(vaccine3TransferRow, String.valueOf(quantity));
        supplyConsolePage.transferToDistributionWithinSameClinic(supplyLocationTo, SUPPLY_DISTRIBUTION_2_1);

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        tables.hardWait(2);//needs couple seconds to refresh results
        double remainingDosesAfterDistribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQtyAfterDistribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingDosesAfterDistribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQtyAfterDistribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingDosesAfterDistribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQtyAfterDistribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

        double remainingDosesAfterDistribution_2_1 = tables.getRemainingDoses(supplyContainerToDistribution1);
        double remainingQtyAfterDistribution_2_1 = tables.getRemainingQty(supplyContainerToDistribution1);
        double remainingDosesAfterDistribution_2_2 = tables.getRemainingDoses(supplyContainerToDistribution2);
        double remainingQtyAfterDistribution_2_2 = tables.getRemainingQty(supplyContainerToDistribution2);
        double remainingDosesAfterDistribution_2_3 = tables.getRemainingDoses(supplyContainerToDistribution3);
        double remainingQtyAfterDistribution_2_3 = tables.getRemainingQty(supplyContainerToDistribution3);

        assertEquals(remainingQtyBeforeDistribution_1_1 - quantity, remainingQtyAfterDistribution_1_1);
        assertEquals(remainingQtyBeforeDistribution_2_1 + quantity, remainingQtyAfterDistribution_2_1);
        assertEquals(remainingQtyBeforeDistribution_1_2 - quantity, remainingQtyAfterDistribution_1_2);
        assertEquals(remainingQtyBeforeDistribution_2_2 + quantity, remainingQtyAfterDistribution_2_2);
        assertEquals(remainingQtyBeforeDistribution_1_3 - quantity, remainingQtyAfterDistribution_1_3);
        assertEquals(remainingQtyBeforeDistribution_2_3 + quantity, remainingQtyAfterDistribution_2_3);

        double remainingDosesAfterCalculationDistribution_1_1 = remainingDosesBeforeDistribution_1_1 - (quantity * conversationRate1);
        assertEquals(
                df.format(remainingDosesAfterCalculationDistribution_1_1),
                df.format(remainingDosesAfterDistribution_1_1));

        double remainingDosesAfterCalculationDistribution_1_2 = remainingDosesBeforeDistribution_1_2 - (quantity * conversationRate2);
        assertEquals(
                df.format(remainingDosesAfterCalculationDistribution_1_2),
                df.format(remainingDosesAfterDistribution_1_2));

        double remainingDosesAfterCalculationDistribution_1_3 = remainingDosesBeforeDistribution_1_3 - (quantity * conversationRate3);
        assertEquals(
                df.format(remainingDosesAfterCalculationDistribution_1_3),
                df.format(remainingDosesAfterDistribution_1_3));
    }

    @Test()
    public void Can_doBulk_transfersBy_Dosages_within_the_same_Clinic_as_PPHIS_Community() throws Exception {
        TestcaseID = "223359";
        String vaccine1 = "COMIRNATY (Pfizer) - EK4241";
        String vaccine2 = "VAXZEVRIA";
        String vaccine3 = "JANSSEN COVID-19 VACCINE";
        double doses = 10;

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        supplyConsolePage = communityPortalMainPage.navigateToSupplyLocation(SUPPLY_LOCATION_2);

        log("/----Count Remaining Supplies --*/");
        Map<String, String> supplyContainerFromLocation1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_1_2);
        Map<String, String> supplyContainerFromLocation2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_1_2);
        Map<String, String> supplyContainerFromLocation3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_1_2);

        Map<String, String> supplyContainerToDistribution1 = searchCriteria(vaccine1, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToDistribution2 = searchCriteria(vaccine2, SUPPLY_DISTRIBUTION_2_1);
        Map<String, String> supplyContainerToDistribution3 = searchCriteria(vaccine3, SUPPLY_DISTRIBUTION_2_1);

        // First distribution data
        double remainingDosesBeforeDistribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQtyBeforeDistribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingDosesBeforeDistribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQtyBeforeDistribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingDosesBeforeDistribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQtyBeforeDistribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

        // Second distribution data
        double remainingDosesBeforeDistribution_2_1 = tables.getRemainingDoses(supplyContainerToDistribution1);
        double remainingQtyBeforeDistribution_2_1 = tables.getRemainingQty(supplyContainerToDistribution1);
        double remainingDosesBeforeDistribution_2_2 = tables.getRemainingDoses(supplyContainerToDistribution2);
        double remainingQtyBeforeDistribution_2_2 = tables.getRemainingQty(supplyContainerToDistribution2);
        double remainingDosesBeforeDistribution_2_3 = tables.getRemainingDoses(supplyContainerToDistribution3);
        double remainingQtyBeforeDistribution_2_3 = tables.getRemainingQty(supplyContainerToDistribution3);

        log("/----Select Items to Transfer and Submit --*/");
        tables.clickSupplyLocationCheckBox(supplyContainerFromLocation1)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation2)
                .clickSupplyLocationCheckBox(supplyContainerFromLocation3);
        supplyConsolePage.clickBulkTransfersButton();

        Map<String, String> vaccine1TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine1, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_2);
        Map<String, String> vaccine2TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine2, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_2);
        Map<String, String> vaccine3TransferRow = ImmutableMap.of
                (SUPPLY_CONTAINER_NAME, vaccine3, SUPPLY_DISTRIBUTION_DESCRIPTION, SUPPLY_DISTRIBUTION_1_2);

        double conversationRate1 = tables.getConversionRateFromTransferRow(vaccine1TransferRow);
        double conversationRate2 = tables.getConversionRateFromTransferRow(vaccine2TransferRow);
        double conversationRate3 = tables.getConversionRateFromTransferRow(vaccine3TransferRow);

        tables.typeDosesIntoTransferRow(vaccine1TransferRow, String.valueOf(doses));
        tables.typeDosesIntoTransferRow(vaccine2TransferRow, String.valueOf(doses));
        tables.typeDosesIntoTransferRow(vaccine3TransferRow, String.valueOf(doses));
        supplyConsolePage.transferToDistributionWithinSameClinic(supplyLocationTo, SUPPLY_DISTRIBUTION_2_1);

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        tables.hardWait(2);//needs couple seconds to refresh results
        double remainingDosesAfterDistribution_1_1 = tables.getRemainingDoses(supplyContainerFromLocation1);
        double remainingQtyAfterDistribution_1_1 = tables.getRemainingQty(supplyContainerFromLocation1);
        double remainingDosesAfterDistribution_1_2 = tables.getRemainingDoses(supplyContainerFromLocation2);
        double remainingQtyAfterDistribution_1_2 = tables.getRemainingQty(supplyContainerFromLocation2);
        double remainingDosesAfterDistribution_1_3 = tables.getRemainingDoses(supplyContainerFromLocation3);
        double remainingQtyAfterDistribution_1_3 = tables.getRemainingQty(supplyContainerFromLocation3);

        double remainingDosesAfterDistribution_2_1 = tables.getRemainingDoses(supplyContainerToDistribution1);
        double remainingQtyAfterDistribution_2_1 = tables.getRemainingQty(supplyContainerToDistribution1);
        double remainingDosesAfterDistribution_2_2 = tables.getRemainingDoses(supplyContainerToDistribution2);
        double remainingQtyAfterDistribution_2_2 = tables.getRemainingQty(supplyContainerToDistribution2);
        double remainingDosesAfterDistribution_2_3 = tables.getRemainingDoses(supplyContainerToDistribution3);
        double remainingQtyAfterDistribution_2_3 = tables.getRemainingQty(supplyContainerToDistribution3);

        assertEquals(remainingDosesBeforeDistribution_1_1 - doses, remainingDosesAfterDistribution_1_1);
        assertEquals(remainingDosesBeforeDistribution_2_1 + doses, remainingDosesAfterDistribution_2_1);
        assertEquals(remainingDosesBeforeDistribution_1_2 - doses, remainingDosesAfterDistribution_1_2);
        assertEquals(remainingDosesBeforeDistribution_2_2 + doses, remainingDosesAfterDistribution_2_2);
        assertEquals(remainingDosesBeforeDistribution_1_3 - doses, remainingDosesAfterDistribution_1_3);
        assertEquals(remainingDosesBeforeDistribution_2_3 + doses, remainingDosesAfterDistribution_2_3);

        double remainingDosesAfterCalculationDistribution2_1 = (remainingDosesBeforeDistribution_2_1 + doses) / conversationRate1;
        assertEquals(
                df.format(remainingDosesAfterCalculationDistribution2_1),
                df.format(remainingDosesAfterCalculationDistribution2_1));

        double remainingDosesAfterCalculationDistribution2_2 = (remainingDosesBeforeDistribution_2_2 + doses) / conversationRate2;
        assertEquals(
                df.format(remainingDosesAfterCalculationDistribution2_2),
                df.format(remainingDosesAfterCalculationDistribution2_2));

        double remainingDosesAfterCalculationDistribution2_3 = (remainingDosesBeforeDistribution_2_3 + doses) / conversationRate3;
        assertEquals(
                df.format(remainingDosesAfterCalculationDistribution2_3),
                df.format(remainingDosesAfterCalculationDistribution2_3));
    }

}