package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APIInsertOpportunity;

import java.util.Random;
import static primarycare.tests.BaseTest_PrimaryCare.log;

public class API_Insert_Opportunity_SysAdmin {
    public String accountId = "0015900000Y2denAAB";//IgorAPI_Account_440
    //public String accountId = "0015900000Y2deOAAR";//IgorAPI_Account_138
    public String name = "IgorAPI_Opportunity" + new Random().nextInt(1000);
    public String oppStage = "Closed Won";
    public String closeDate = "2023-09-07";

    @Test
    public void API_Can_Insert_Opportunity_to_Salesforce_as_SysAdmin(){
        APIInsertOpportunity apiinsertOpportunity = new APIInsertOpportunity();
        log("Inserting opportunity record.");
        String accountID = apiinsertOpportunity.insertOpportunity(accountId, name, oppStage, closeDate);
        log("Inserted Opportunities id is: " +accountID);

    }
}
