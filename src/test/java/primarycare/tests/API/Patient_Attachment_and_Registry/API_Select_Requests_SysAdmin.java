package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.Assert;
import org.testng.annotations.Test;
import primarycare.pages.APIInsertOpportunity;
import primarycare.pages.APISelect;

import static primarycare.tests.BaseTest_PrimaryCare.log;

public class API_Select_Requests_SysAdmin {
    public String name = "IgorAPI_Account_138";
    public String accId = "0015900000Y2deOAAR";


    @Test
    public void API_Can_Select_From_Account_Salesforce_as_SysAdmin(){

        APISelect sqlQuery = new APISelect();
        log("Select account Name from Account.");
        String accountNameReturned = sqlQuery.selectSQL("SELECT Name from Account WHERE id = '"+accId+"'", "Name");
        log("Selected Account Name from Account is: " +accountNameReturned);
        Assert.assertEquals(accountNameReturned, name);
    }
}
