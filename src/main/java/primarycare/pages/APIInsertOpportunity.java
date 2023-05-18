package primarycare.pages;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class APIInsertOpportunity {
    public String insertOpportunity (String accId, String oppName, String oppStage, String closeDate)
    {
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        System.out.println("Connection for SF Establish with Status code 200");
        System.out.println("access_token is:" +acc_token);

        Map<String, Object> mapper =  new HashMap<String,Object>();
        mapper.put("AccountId", accId);
        mapper.put("Name", oppName);
        mapper.put("StageName", oppStage);
        mapper.put("CloseDate", closeDate);

        JSONObject requester = new JSONObject(mapper);
        System.out.println("Opportunity Object JSON is:" +requester.toString());

        return
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Authorization", "Bearer "+acc_token).
                header("Content-Type", "application/json").
                body(requester.toString()).
                when().
                post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/data/v57.0/sobjects/Opportunity").
                then().statusCode(201).log().body().extract().path("id");
                       // then().log().body();
        //return null;
    }
}
