package primarycare.tests.API.Patient_Attachment_and_Registry;

import org.testng.annotations.Test;
import primarycare.pages.APIEstablishSFConnection;
import primarycare.tests.API_BaseTest_PrimaryCare;


public class API_Establish_Salesforce_Connection_SysAdmin extends API_BaseTest_PrimaryCare {

    @Test
    public void API_Can_Establish_Salesforce_connection_Status_Code_200_as_SysAdmin(){
        TestcaseID = "252563"; //C252563
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        log("Start establishing connection");
        String acc_token = sfConnection.establishConnection();
        log("Connection to SF successful!Status Code is 200");
        log("access_token is: "+ acc_token +" ");

    }

}
