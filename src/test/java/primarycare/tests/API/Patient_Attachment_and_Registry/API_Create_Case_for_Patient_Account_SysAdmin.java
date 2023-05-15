package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APICreatePatientAccount;
import primarycare.pages.APIDelete;

import static primarycare.tests.BaseTest_PrimaryCare.*;

public class API_Create_Case_for_Patient_Account_SysAdmin {
    public String firstName = "Kenton3_Patient_With_Case";
    public String lastName = "Troup3";
    public String recordTypeId = "0125f0000003ekBAAQ ";
    public String phn = "9873010088";
    public String gender = "Male";
    public String birthdate = "1959-12-05";
    public String preferredCommunicationChannel = "Email";
    public String mobile = "7788793897";
    public String email = "accountToDelete@phsa.ca";
    public String street = "307-7631 Francis Rd";
    public String city = "Richmond";
    public String postalcode = "V6Y 1A3";
    public String isActive = "true";

    public String accId;

    @Test(priority = 1)
    public void API_Can_Create_Case_for_Patient_Account_in_Salesforce_as_SysAdmin(){
        APICreatePatientAccount apiinsertAccount = new APICreatePatientAccount();
        log("Create Patient account record.");
        String accountID = apiinsertAccount.insertAccount(recordTypeId, firstName,lastName,phn,gender,
                birthdate,preferredCommunicationChannel, mobile,email,street,city,postalcode,isActive);
        log("Created Patient's id is: " +accountID);
        accId=accountID;

        //TestcaseID = "252626"; //C252626
        //APICreateCase apiinsertAccount = new APICreatePatientAccount();
        log("Create CASE account record.");
    }

    //@Test(priority = 3)
    public void API_Removing_Case_for_Patient_Account_in_Salesforce_as_SysAdmin(){
        //TestcaseID = "252628"; //C252628
        APIDelete apidelete = new APIDelete();
        log("Delete Patient Case from Cases .");
        String apiResponse = apidelete.deleteAccount(accId);
        log("Deleted Patient Case from Cases is: " +accId);
        log(apiResponse);
        //Assert.assertEquals(accountNameReturned, name);
    }


    //@Test(priority = 4)
    public void API_Removing_Patient_Account_in_Salesforce_as_SysAdmin(){
        //TestcaseID = "252628"; //C252628
        APIDelete apidelete = new APIDelete();
        log("Delete account from Account.");
        String apiResponse = apidelete.deleteAccount(accId);
        log("Deleted Account from Account is: " +accId);
        log(apiResponse);
        //Assert.assertEquals(accountNameReturned, name);
    }
}
