package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class UserArrivingWrongClinicRebookAppointment extends BaseTest {
	
	@Test(priority = 1)
	public void Pre_conditions_step_Remove_DupsJodie_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
	}
	
	@Test(priority = 2)
	public void Can_Rebook_Walk_In_Appointment_Arrive_At_Wrong_Clinic_as_Clinician_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "219910"; //C219910
	}
	
	@Test(priority = 3)
	public void Post_conditions_step_Remove_DupsJodie_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
	}
}
