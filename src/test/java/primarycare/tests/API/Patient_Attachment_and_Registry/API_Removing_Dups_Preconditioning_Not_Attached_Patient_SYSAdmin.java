package primarycare.tests.API.Patient_Attachment_and_Registry;

import primarycare.tests.Utilities.ApiQueries;
import primarycare.tests.Utilities.TestListener;
import primarycare.tests.API_BaseTest_PrimaryCare;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class API_Removing_Dups_Preconditioning_Not_Attached_Patient_SYSAdmin extends API_BaseTest_PrimaryCare {
    private String legalFirstName = "Sandy";
    private String legalLastName = "Prior";
    private String dateOfBirth = "March 1, 1975";
    private String postalCode = "V6Y 1A3";
    private String personalHealthNumber = "9873010063";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";


    @Test(priority = 1)
    public void API_Can_Remove_Duplicates_Preconditioning_Not_Attached_Patient_SYSAdmin() throws Exception {
        TestcaseID = "251774"; //C251774
        log("/---API call to remove duplicate Patient account and Case if found--*/");
        ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);
    }



}
