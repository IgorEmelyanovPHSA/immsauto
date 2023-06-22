package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APICreatePatientAccount;
import primarycare.pages.APIDelete;
import primarycare.tests.API_BaseTest_PrimaryCare;
import java.util.Random;

public class API_Create_Patient_Account_SysAdmin extends API_BaseTest_PrimaryCare {
    //public String lastname = "IgorAPI_Account_" + new Random().nextInt(1000);
    public String firstName = "Kenton2_Patient_NO_Case";
    public String lastName = "Troup2";
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
    public void API_Can_Create_Patient_Account_in_Salesforce_Status_Code_201_as_SysAdmin(){
        TestcaseID = "252626"; //C252626
        APICreatePatientAccount apiinsertAccount = new APICreatePatientAccount();
        log("Create Patient account record.");
        String accountID = apiinsertAccount.insertAccount(recordTypeId, firstName,lastName,phn,gender,
                birthdate,preferredCommunicationChannel, mobile,email,street,city,postalcode,isActive);
        log("Created Patient's id is: " +accountID);
        log("Status Code 201- created success");
        accId=accountID;
    }




    @Test(priority = 2)
    public void API_Delete_Patient_Account_in_Salesforce_Status_Code_204_as_SysAdmin(){
        TestcaseID = "252628"; //C252628
        APIDelete apidelete = new APIDelete();
        log("Delete Patient account from Account.");
        String apiResponse = apidelete.deleteAccount(accId);
        log("Deleted Patient Account from Account is: " +accId);
        log("Status Code 204 - No Content success status response code indicates that a request has succeeded ");
        log(apiResponse);
        //Assert.assertEquals(accountNameReturned, name);
    }
}

