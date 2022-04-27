package bcvaxuat.my.salesforce.com.Tests.ClincInBox;

import bcvaxuat.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

public class BookingDose1 extends BaseTest {
	static Logger log = Logger.getLogger(BookingDose1.class);
	
	@Test
	public void Can_Book_Dose1_Appointment_as_Clinician_CIB_BCVAXUAT() throws InterruptedException {
		TestcaseID = "192878"; //C192878
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
		System.out.println("/*4.----click Register New Citizen -Ludovika --*/");
		clinicInBox.clickRegisterButton();
		Thread.sleep(2000);
		
		
	}
	
}
