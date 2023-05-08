package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APIInsertContact;
import primarycare.pages.APIInsertOpportunity;

import java.util.Random;
import static primarycare.tests.BaseTest_PrimaryCare.log;

public class API_Insert_Contact_SysAdmin {
    public String recordTypeId = "0125f0000001DXYAA2";
    public String accId = "0015900000Y2deOAAR"; //IgorAPI_Account_138
    //public String accId = "0015900000Y2denAAB";//IgorAPI_Account_440
    public String lastname = "IgorAPI_Contact" + new Random().nextInt(1000);

    @Test
    public void API_Can_Insert_Contact_to_Salesforce_as_SysAdmin(){
        APIInsertContact apiinsertContact = new APIInsertContact();
        log("Inserting Contact record.");
        String accountID = apiinsertContact.insertContact(recordTypeId , accId, lastname);
        log("Inserted Contacts id is: " +accountID);
    }

}
