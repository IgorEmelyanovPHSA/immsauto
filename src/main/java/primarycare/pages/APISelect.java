package primarycare.pages;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class APISelect {
    public String selectSQL (String sql_request, String fieldName)
    {
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        System.out.println("access_token is:" +acc_token);
        System.out.println("Connection for SF Establish with Status code 200");
        System.out.println("SQL request is:" +sql_request);

        List<Map<String, Object>> recordsArray =
                given().
                        contentType(ContentType.JSON).
                        header("Authorization", "Bearer "+acc_token).
                        get("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/data/v57.0/query?q="+sql_request+"").
                        then().extract().path("records");
        // then().log().body();

        String fieldValue = (String)recordsArray.get(0).get(fieldName);
        return  fieldValue;
        //return null;
    }

}
