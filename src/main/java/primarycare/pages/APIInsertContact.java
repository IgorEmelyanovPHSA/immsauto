package primarycare.pages;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class APIInsertContact {
    public String insertContact (String recordTypeId, String accId,  String lastName)
    {
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        System.out.println("Connection for SF Establish with Status code 200");
        System.out.println("access_token is:" +acc_token);

        Map<String, Object> mapper =  new HashMap<String,Object>();
        mapper.put("RecordTypeId", recordTypeId);
        mapper.put("AccountId", accId);
        mapper.put("LastName", lastName);

        JSONObject requester = new JSONObject(mapper);
        System.out.println("Contact Object JSON is:" +requester.toString());

        return
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        header("Authorization", "Bearer "+acc_token).
                        header("Content-Type", "application/json").
                        body(requester.toString()).
                        when().
                        post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/data/v57.0/sobjects/Contact").
                        then().statusCode(201).log().body().extract().path("id");
                         //then().log().body();
        //return null;
    }
}
