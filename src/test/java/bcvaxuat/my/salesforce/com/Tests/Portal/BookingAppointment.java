package bcvaxuat.my.salesforce.com.Tests.Portal;

import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

public class BookingAppointment extends BaseTest {
	
	@Test
	public void citizenPortalFlowDoseOne() throws InterruptedException {
		TestcaseID = "153419"; //C153419
		
		/*
		Go through the citizen flow as someone who does not have a PHN number
		Go through the citizen flow as someone who has a PHN number
		Go through the citizen flow as someone who put a wrong date of birth by mistake/wrong name
		Go through the citizen flow where Booking capacity is full on a particular clinic at a specific time slot
		Go through the citizen flow where Booking capacity is one less than the capacity and two citizen booking at almost the same time
		Go through the citizen flow where Booking capacity is full but one citizen cancelled so it became available for booking, then it gets booked and meets the capacity and should not be available anymore
		*/
		
	}
}