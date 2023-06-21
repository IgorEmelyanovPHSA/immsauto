package primarycare.tests.API.Provider_and_Rostering;

import org.testng.annotations.Test;
import primarycare.pages.APIEstablishSFConnection;
import primarycare.tests.API_BaseTest_PrimaryCare;

public class API_Establish_Salesforce_Connection_SysAdmin extends API_BaseTest_PrimaryCare{

    @Test
    public void API_Can_Establish_Salesforce_connection_Status_Code_200_as_SysAdmin(){
        TestcaseID = "252883"; //C252883
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        log("Start establishing connection");
        String acc_token = sfConnection.establishConnection();
        log("Connection to SF successful! Status code 200");
        log("access_token is: "+ acc_token +" ");

    }

}
