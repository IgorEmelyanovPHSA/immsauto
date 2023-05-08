package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APICreatePatientAccount;
import primarycare.pages.APIDelete;
import primarycare.tests.BaseTest_PrimaryCare;
import java.util.Random;

public class API_Create_Patient_Account_SysAdmin extends BaseTest_PrimaryCare {
    //public String lastname = "IgorAPI_Account_" + new Random().nextInt(1000);
    public String firstName = "Kenton111";
    public String lastName = "Troup111";
    //public String recordTypeId = "0125f0000002f6sAAA";
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
    public void API_Can_Create_Patient_Account_in_Salesforce_as_SysAdmin(){
        TestcaseID = "252626"; //C252626
        APICreatePatientAccount apiinsertAccount = new APICreatePatientAccount();
        log("Create Patient account record.");
        String accountID = apiinsertAccount.insertAccount(firstName,lastName,phn,gender,
                birthdate,preferredCommunicationChannel, mobile,email,street,city,postalcode,isActive);
        log("Created Patient's id is: " +accountID);
        accId=accountID;
    }


    @Test(priority = 2)
    public void API_Remove_Patient_Account_in_Salesforce_as_SysAdmin(){
        TestcaseID = "252628"; //C252628
        APIDelete apidelete = new APIDelete();
        log("Delete account from Account.");
        String apiResponse = apidelete.deleteAccount(accId);
        log("Deleted Account from Account is: " +accId);
        log(apiResponse);
        //Assert.assertEquals(accountNameReturned, name);
    }
}

