package bcvaxdevit.my.salesforce.com.Tests.ClincInBox;

import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

public class BookingDose2 extends BaseTest {
	static Logger log = Logger.getLogger(BookingDose2.class);


	@Test(testName = "Clinician can do Dose#2 Vaccine Administration pathway for new Registered Citizen in ICE")
	public void Can_Book_Dose2_Appointment_as_Clinician_CIB_BCVAXDEVIT() throws InterruptedException {
		//TestcaseID = "219926"; C219926

		PropertyConfigurator.configure("log4j.properties");
		System.out.println("/*1.----Login as an Clinician to CIB --*/");
		log.info("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
		Thread.sleep(2000);
		System.out.println("/*2.----Clinic In Box(IPM) page displayed --*/");
		log.info("/*2.----Clinic In Box(IPM) page displayed --*/");
		clinicInBox.verifyIsClinicInBoxPageDisplayed();
		Thread.sleep(5000);
		System.out.println("/*3.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(5000);
		System.out.println("/*----3. Search for Participant account Hugues BCVaxLampard ---*/");
		Thread.sleep(2000);
		clinicInBox.SearchDose2Citizen("Hugues BCVaxLampard");
		Thread.sleep(2000);
		System.out.println("/*----4. select Citizen from search results --*/");
		clinicInBox.clickDose2Citizen();
		Thread.sleep(2000);


	}
}
