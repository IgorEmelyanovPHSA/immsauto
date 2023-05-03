package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import static primarycare.tests.BaseTest_PrimaryCare.log;
import primarycare.pages.APIEstablishSFConnection;
import primarycare.tests.BaseTest_PrimaryCare;

public class API_Establish_Salesforce_Connection_SysAdmin extends BaseTest_PrimaryCare {

    //public String access_token;

    @Test
    public void API_Can_Establish_Salesforce_connection_as_SysAdmin(){
        TestcaseID = "252563"; //C252563
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        log("Connection to SF successful!");
        log("access_token is: "+ acc_token +" ");

    }

}
