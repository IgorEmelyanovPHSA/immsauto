package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.MainPageCP;
import bcvax.pages.MainPageOrg;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static java.lang.Math.round;

@Listeners({TestListener.class})
public class TransferCancellationCP extends BaseTest {
	MainPageCP cpMainPage;
	MainPageOrg orgMainPage;
	SupplyConsolePage supplyConsolePage;
	String env;
	Map<String, Object> testData;
	String supply_location_from;
	String supply_location_to;
	String distribution_from;
	String distribution_to;
	String distribution_to_same_clinic;

	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
		supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
		distribution_from = String.valueOf(testData.get("distributionFrom"));
		distribution_to = String.valueOf(testData.get("distributionTo"));
		distribution_to_same_clinic = String.valueOf(testData.get("distributionToSameClinic"));

		if(env.contains("immsbc_admin")) {
			log("/*1.----Login to CP (newUI) as ImmsBC_Admin --*/");
			orgMainPage = loginPage.orgLoginAsImmsBCAdminCP();
			Thread.sleep(1000);
			orgMainPage.switchApp(Apps.BCH_VACCINATION_PORTAL.value);
			Thread.sleep(3000);
			cpMainPage = new MainPageCP(driver);
			cpMainPage.clickGoToUserDefaultsButton();
		} else {
			log("/*1.----Login to CP (newUI) as Clinician --*/");
			cpMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();;
		}
		Thread.sleep(5000);
		supplyConsolePage = cpMainPage.navigateToSupplyLocation(supply_location_from);
	}

	@Test(priority = 1)
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_And_Cancel() throws Exception {
		TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "223184"; //C223184
		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerTo"));
		double doses = 5;

		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
		double remainingDosesBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution1_1);
		double remainingQtyBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);;
		log("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution1_1);

		log("/---- Perform doses transfer to  location " + supply_location_to + "--*/");
		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
		Thread.sleep(2000);
		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		Thread.sleep(2000);

		log("/*9.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		log("/*--  the Trade Name is:  " + tradeName);
		Thread.sleep(2000);
		log("/*10.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		Thread.sleep(2000);
		log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferDosages(Double.toString(doses));
		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
		supplyConsolePage.selectSupplyLocation_2_To();
		Thread.sleep(2000);
		System.out.println("/*12.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		Thread.sleep(2000);
		System.out.println("/*13.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersCloseButton();
		Thread.sleep(5000);
		log("/---- Count and Validate Remaining Supplies After Transfer --*/");
		double remainingDosesAfterDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterDistribution1_1);
		double remainingQtyAfterDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterDistribution1_1);
		double remainingDosesAfterCalculationDistribution1_1 = remainingDosesBeforeDistribution1_1 - doses;
		assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);
		double remainingQtyAfterCalculationDistribution1_1 =
				remainingDosesAfterCalculationDistribution1_1 / dose_conversation_factor;
		assertEquals(remainingQtyAfterDistribution1_1, remainingQtyAfterCalculationDistribution1_1);

		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		supplyConsolePage = cpMainPage.navigateToSupplyLocation(supply_location_to);

		Thread.sleep(2000);
		supplyConsolePage.refreshBrowser();
		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDosesBeforeDistribution2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution2_1);
		double remainingQtyBeforeDistribution2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution2_1);

		log("/*22.----Go to Supply Location Related Tab where Transferring From --*/");
		supplyConsolePage = cpMainPage.navigateToSupplyLocation(supply_location_from);

		Thread.sleep(2000);
		supplyConsolePage.refreshBrowser();
		supplyConsolePage.clickTransactionsTab();
		System.out.println("/*23----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		System.out.println("/*23.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
		Thread.sleep(5000);
		System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
		int kk = countOutgoingTransactions;
		System.out.println("/*23.2---Get Outgoing Transaction id 'from' --*/");
		String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
		System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
		System.out.println("/*23.3----Click on the latest created Outgoing Transactions --*/");
		supplyConsolePage.clickOnOutgoingTransactionsDropDownMenu(kk);
		log("/*23.----Cancel Transfer --*/");
		//tables.openShippedTransactionsOutgoingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
		supplyConsolePage.cancelIncomingTransfer();
		supplyConsolePage.clickOnRelatedItemTab();

		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		double remainingDosesAfterCancelDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution1_1);
		double remainingQtyAfterCancelDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution1_1);
		assertEquals(remainingDosesAfterCancelDistribution1_1, remainingDosesBeforeDistribution1_1);
		assertEquals(remainingQtyAfterCancelDistribution1_1, remainingQtyBeforeDistribution1_1);

		log("/----Go to Supply Location Related Tab where Transferring To --*/");
		supplyConsolePage = cpMainPage.navigateToSupplyLocation(supply_location_to);

		Thread.sleep(2000);
		supplyConsolePage.refreshBrowser();
		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		double remainingDosesAfterCancelDistribution2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution2_1);
		double remainingQtyAfterCancelDistribution2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution2_1);
		assertEquals(remainingDosesAfterCancelDistribution2_1, remainingDosesBeforeDistribution2_1);
		assertEquals(remainingQtyAfterCancelDistribution2_1, remainingQtyBeforeDistribution2_1);
	}

	@Test()
	public void Can_do_Transfer_by_Quantity_from_one_Clinic_to_Another_And_Cancel() throws Exception {
		TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "223184"; //C223184
		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerTo"));
		double quantity = 1;

		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
		double remainingDosesBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution1_1);
		double remainingQtyBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);;
		log("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution1_1);

		log("/---- Perform Quantity transfer to another location --*/");
		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
		Thread.sleep(2000);
		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		Thread.sleep(2000);

		log("/*9.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		log("/*--  the Trade Name is:  " + tradeName);
		Thread.sleep(2000);
		log("/*10.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		Thread.sleep(2000);
		log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferQuantity(Double.toString(quantity));
		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
		supplyConsolePage.selectSupplyLocation_2_To();
		Thread.sleep(2000);
		System.out.println("/*12.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		Thread.sleep(2000);
		System.out.println("/*13.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersCloseButton();
		Thread.sleep(5000);
		log("/---- Count and Validate Remaining Supplies After Transfer --*/");
		double remainingDosesAfterDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterDistribution1_1);
		double remainingQtyAfterDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterDistribution1_1);
		double remainingDosesAfterCalculationDistribution1_1 = remainingDosesBeforeDistribution1_1 - quantity * dose_conversation_factor ;
		assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);
		double remainingQtyAfterCalculationDistribution1_1 = remainingQtyBeforeDistribution1_1 - quantity;
		assertEquals(remainingQtyAfterDistribution1_1, remainingQtyAfterCalculationDistribution1_1);
		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		supplyConsolePage = cpMainPage.navigateToSupplyLocation(supply_location_to);

		Thread.sleep(2000);
		supplyConsolePage.refreshBrowser();
		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDosesBeforeDistribution2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution2_1);
		double remainingQtyBeforeDistribution2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution2_1);

		log("/*22.----Go to Supply Location Related Tab where Transferring From --*/");
		supplyConsolePage = cpMainPage.navigateToSupplyLocation(supply_location_from);

		Thread.sleep(2000);
		supplyConsolePage.refreshBrowser();
		supplyConsolePage.clickTransactionsTab();

		System.out.println("/*23----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		System.out.println("/*23.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
		Thread.sleep(5000);
		System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
		int kk = countOutgoingTransactions;
		System.out.println("/*23.2---Get Outgoing Transaction id 'from' --*/");
		String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
		System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
		System.out.println("/*23.3----Click on the latest created Outgoing Transactions --*/");
		supplyConsolePage.clickOnOutgoingTransactionsDropDownMenu(kk);
		log("/*23.----Cancel Transfer --*/");
		//tables.openShippedTransactionsOutgoingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
		supplyConsolePage.cancelIncomingTransfer();
		supplyConsolePage.clickOnRelatedItemTab();

		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		double remainingDosesAfterCancelDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution1_1);
		double remainingQtyAfterCancelDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution1_1);
		assertEquals(remainingDosesAfterCancelDistribution1_1, remainingDosesBeforeDistribution1_1);
		assertEquals(remainingQtyAfterCancelDistribution1_1, remainingQtyBeforeDistribution1_1);

		log("/----Go to Supply Location Related Tab where Transferring To --*/");
		supplyConsolePage = cpMainPage.navigateToSupplyLocation(supply_location_to);

		Thread.sleep(2000);
		supplyConsolePage.refreshBrowser();
		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		double remainingDosesAfterCancelDistribution2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution2_1);
		double remainingQtyAfterCancelDistribution2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution2_1);
		assertEquals(remainingDosesAfterCancelDistribution2_1, remainingDosesBeforeDistribution2_1);
		assertEquals(remainingQtyAfterCancelDistribution2_1, remainingQtyBeforeDistribution2_1);
	}

}
