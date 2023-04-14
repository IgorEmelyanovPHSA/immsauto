package primarycare.tests.API.Patient_Attachment_and_Registry;


import Utilities.TestListener;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class API_Removing_Not_Attached_Patient_SYSAdmin extends BaseTest {
    private String legalFirstName = "Sandy";
    private String legalLastName = "Prior";
    private String dateOfBirth = "March 1, 1975";
    private String postalCode = "V3L5L1";
    private String personalHealthNumber = "9873010063";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";


    @Test(priority = 1)
    public void Can_Remove_Patient_from_SF_as_an_SYSAdmin() throws Exception {
        TestcaseID = "251434"; //C251434
        log("/---API call to remove duplicate citizen participant account if found--*/");
        //Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        Utilities.ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);
    }



}
