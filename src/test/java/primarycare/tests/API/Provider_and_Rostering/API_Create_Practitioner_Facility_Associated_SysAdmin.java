package primarycare.tests.API.Provider_and_Rostering;

import org.testng.annotations.Test;
import primarycare.pages.APICreatePractitionerAccount;
import primarycare.pages.APIDelete;
import primarycare.tests.API_BaseTest_PrimaryCare;

public class API_Create_Practitioner_Facility_Associated_SysAdmin extends API_BaseTest_PrimaryCare{
    public String practitionerFacilityName = "Lori-Ann May Bus | CASTLEGAR MEDICAL ASSOCIATES DIRECTOR222";
    public String accountId = "0018N00000F9StyQAF"; //CASTLEGAR MEDICAL ASSOCIATES
    public String practitionerId = "0038N00000D9NPoQAN";//Lori-Ann May Bus
    public String acceptingNewPatients = "Yes";
    public String maxNewRequests = "77777";
    //public String role = "Director";
    public String role = "Provider";
    //pulic String role = "Medical Office Assistant";
    public String isActive = "true";
    public String recordTypeId = "0128N000001Fy9XQAS";

    public String accId;

    @Test(priority = 1)
    public void API_Can_Create_Practitioner_DIRECTORs_Facility_Associated_in_Salesforce_Status_Code_204_as_SysAdmin(){
        TestcaseID = "252895"; //252895
        APICreatePractitionerAccount apiCreateDirectorAccount = new APICreatePractitionerAccount();
        log("Create Practitioner DIRECTOR's Associated account record.");
        String accountID = apiCreateDirectorAccount.insertDirectorAssociatedAccount(practitionerFacilityName,
                accountId,practitionerId, acceptingNewPatients, maxNewRequests, role,isActive,recordTypeId);
        log("Created MOA Practitioner's id is: " +accountID);
        accId=accountID;
    }


    //@Test(priority = 2)
    public void API_Remove_Practitioner_DIRECTORs_Facility_Associated_in_Salesforce_as_SysAdmin(){
        TestcaseID = "252885"; //C252885
        APIDelete apidelete = new APIDelete();
        log("Delete Practitioner account from Account.");
        String apiResponse = apidelete.deleteAccount(accId);
        log("Deleted Practitioner Account from Account is: " +accId);
        log(apiResponse);
        //Assert.assertEquals(accountNameReturned, name);
    }

}
