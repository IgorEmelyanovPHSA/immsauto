package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APICreatePatientAccount;
import java.util.Random;
import static primarycare.tests.BaseTest_PrimaryCare.log;

public class API_Create_Patient_Account_SysAdmin {
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

    @Test
    public void API_Can_Create_Patient_Account_in_Salesforce_as_SysAdmin(){
        APICreatePatientAccount apiinsertAccount = new APICreatePatientAccount();
        log("Create Patient account record.");
        String accountID = apiinsertAccount.insertAccount(firstName,lastName,phn,gender,
                birthdate,preferredCommunicationChannel, mobile,email,street,city,postalcode,isActive);
        log("Created Patient's id is: " +accountID);

    }
}
