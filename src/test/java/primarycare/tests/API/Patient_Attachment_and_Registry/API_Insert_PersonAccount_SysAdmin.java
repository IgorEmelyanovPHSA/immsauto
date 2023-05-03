package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APIInsertAccount;
import java.util.Random;
import static primarycare.tests.BaseTest_PrimaryCare.log;

public class API_Insert_PersonAccount_SysAdmin {
    public String lastname = "IgorAPI_Account_" + new Random().nextInt(1000);
    //public String recordTypeId = "0125f0000002f6sAAA";
    public String phone = "7788793897";

    @Test
    public void API_Can_Insert_PersonAccount_to_Salesforce_as_SysAdmin(){
        APIInsertAccount apiinsertAccount = new APIInsertAccount();
        log("Inserting account record.");
        String accountID = apiinsertAccount.insertAccount(lastname, phone);
        log("Inserted Account's id is: " +accountID);

    }
}
