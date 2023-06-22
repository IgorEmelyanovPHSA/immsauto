package primarycare.tests.API.Provider_and_Rostering;

import org.testng.annotations.Test;
import primarycare.pages.APICreatePractitionerAccount;
import primarycare.pages.APIDelete;
import primarycare.tests.API_BaseTest_PrimaryCare;

public class API_Create_Practitioner_Account_NO_Associated_SysAdmin extends API_BaseTest_PrimaryCare {
    //public String lastname = "IgorAPI_Account_" + new Random().nextInt(1000);
    //public String salutation = "Ms.";
    public String firstName = "Tanya222_Practitioner";
    public String lastName = "Drysdale";
    public String birthdate = "1983-07-11";
    public String gender = "Women";
    public String email = "accountToDelete@phsa.ca";
    public String phone = "2502946960";
    public String isActive = "true";
    public String recordTypeId = "0125f000000qtflAAA";
    public String MSP = "54321";

    public String accId;

    @Test(priority = 1)
    public void API_Can_Create_Practitioner_Account_No_Associated_in_Salesforce_Status_Code_201_as_SysAdmin(){
        TestcaseID = "252884"; //C252884
        APICreatePractitionerAccount apiCreatePractitionerAccount = new APICreatePractitionerAccount();
        log("Create Practitioner account record.");
        String accountID = apiCreatePractitionerAccount.insertPractitionerAccount(recordTypeId, firstName,lastName,gender,
                birthdate,phone,email,isActive,MSP);
        log("Created Practitioner's id is: " +accountID);
        log("Status Code 201- created success");
        accId=accountID;
    }


    @Test(priority = 2)
    public void API_Delete_Practitioner_MOA_Account_No_Associated_in_Salesforce_as_SysAdmin(){
        TestcaseID = "252885"; //C252885
        APIDelete apidelete = new APIDelete();
        log("Delete Practitioner account from Account.");
        String apiResponse = apidelete.deleteAccount(accId);
        log("Deleted Practitioner Account from Account is: " +accId);
        log(apiResponse);
        //Assert.assertEquals(accountNameReturned, name);
    }
}
