package bcvaxuat.my.salesforce.com.Tests.ClincInBox;

import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.Assert.assertThat;

public class DIWA extends BaseTest {
	@Test
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_BCVAXUAT() throws InterruptedException {
		TestcaseID = "219745"; //C219745
		System.out.println("/*----1. Login as an Clinician to ICE --*/");
		System.out.println("/*----2. Go to Related Tab fro Person Account--*/");
		System.out.println("/*----3. clinck on 'create Immunisation Record' quick action --*/");
		
		Assert.assertTrue(false);
	}
}
