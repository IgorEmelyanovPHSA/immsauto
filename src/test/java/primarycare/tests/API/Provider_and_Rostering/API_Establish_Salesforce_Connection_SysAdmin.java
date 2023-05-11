package primarycare.tests.API.Provider_and_Rostering;

import org.testng.annotations.Test;
import primarycare.pages.APIEstablishSFConnection;
import primarycare.tests.BaseTest_PrimaryCare;

public class API_Establish_Salesforce_Connection_SysAdmin extends BaseTest_PrimaryCare{

    @Test
    public void API_Can_Establish_Salesforce_connection_as_SysAdmin(){
        TestcaseID = "252883"; //C252883
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        log("Connection to SF successful!");
        log("access_token is: "+ acc_token +" ");

    }

}
