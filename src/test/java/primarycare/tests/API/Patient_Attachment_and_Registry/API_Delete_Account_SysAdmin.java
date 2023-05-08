package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APIDelete;

import static primarycare.tests.BaseTest_PrimaryCare.log;

public class API_Delete_Account_SysAdmin {
    public String accId = "0015900000Y2dfvAAB";//IgorAPI_Account_902

    @Test
    public void API_Can_Delete_Account_in_Salesforce_as_SysAdmin(){
        APIDelete apidelete = new APIDelete();
        log("Delete account from Account.");
        String apiResponse = apidelete.deleteAccount(accId);
        log("Deleted Account from Account is: " +accId);
        log(apiResponse);
        //Assert.assertEquals(accountNameReturned, name);
    }
}
