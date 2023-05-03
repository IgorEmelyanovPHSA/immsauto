package primarycare.tests.API.Patient_Attachment_and_Registry;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static primarycare.tests.BaseTest_PrimaryCare.log;

public class API_Registering_Not_Attached_Patient_SYSAdmin {
    @Test
    void Registering_Patient_POST(){
        Response response = post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9E8TNx7FN9y53NY7pkEQZLSSW4SoCXmPvef.rD0ZfaG.uMSpBKdaYM5tdWX13D_3Sj8Pkn1EGM08VMuX6&client_secret=FED2C722B45AF22B54387C55C4B3AE485BBFA1110FD6AA5B90ED9BD8A3EB6ACE&username=igor.emelyanov@phsa.hlthbcqax.ca&password=Technology1990!!!!!!!");

        log("/*---API Get String: " + response.asString() + " --*/");
        //log(response.asString());
        log("/*---API Get Body: " + response.getBody() + " --*/");
        //log(response.getBody().asString());
        log("/*---API Status code: " + response.getStatusCode() + " --*/");
        //log(String.valueOf());
        log(response.getHeader("content-type"));
        System.out.println(response.getTime());
        log(String.valueOf(response.getTime()));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    void test_02_POST() {
        given().post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9E8TNx7FN9y53NY7pkEQZLSSW4SoCXmPvef.rD0ZfaG.uMSpBKdaYM5tdWX13D_3Sj8Pkn1EGM08VMuX6&client_secret=FED2C722B45AF22B54387C55C4B3AE485BBFA1110FD6AA5B90ED9BD8A3EB6ACE&username=igor.emelyanov@phsa.hlthbcqax.ca&password=Technology1990!!!!!!!")
                .then().statusCode(200);

        given().post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9E8TNx7FN9y53NY7pkEQZLSSW4SoCXmPvef.rD0ZfaG.uMSpBKdaYM5tdWX13D_3Sj8Pkn1EGM08VMuX6&client_secret=FED2C722B45AF22B54387C55C4B3AE485BBFA1110FD6AA5B90ED9BD8A3EB6ACE&username=igor.emelyanov@phsa.hlthbcqax.ca&password=Technology1990!!!!!!!")
                .then().body("token_type", equalTo("Bearer") );

        given().post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9E8TNx7FN9y53NY7pkEQZLSSW4SoCXmPvef.rD0ZfaG.uMSpBKdaYM5tdWX13D_3Sj8Pkn1EGM08VMuX6&client_secret=FED2C722B45AF22B54387C55C4B3AE485BBFA1110FD6AA5B90ED9BD8A3EB6ACE&username=igor.emelyanov@phsa.hlthbcqax.ca&password=Technology1990!!!!!!!")
                .then().body("access_token", equalTo("00D590000008iln!AR0AQIw4om6yJ81tBDW.66vsxsU9nuUvK6XxRUzX80EaSO.CKcxCnP49syIW3qzTQeTjp3Y_K4_KJgkKyWpVIFvtegLk758n") );
    }

    @Test
    public void tet_03_Post(){
        given().
                post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9E8TNx7FN9y53NY7pkEQZLSSW4SoCXmPvef.rD0ZfaG.uMSpBKdaYM5tdWX13D_3Sj8Pkn1EGM08VMuX6&client_secret=FED2C722B45AF22B54387C55C4B3AE485BBFA1110FD6AA5B90ED9BD8A3EB6ACE&username=igor.emelyanov@phsa.hlthbcqax.ca&password=Technology1990!!!!!!!").
                then().statusCode(200).log().all();

    }

    @Test
    public void tet_04_Post(){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("\"name\"","Ragahav");
        map.put("job", "Tacer");
        log(map.toString());

        //given().
                //post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9E8TNx7FN9y53NY7pkEQZLSSW4SoCXmPvef.rD0ZfaG.uMSpBKdaYM5tdWX13D_3Sj8Pkn1EGM08VMuX6&client_secret=FED2C722B45AF22B54387C55C4B3AE485BBFA1110FD6AA5B90ED9BD8A3EB6ACE&username=igor.emelyanov@phsa.hlthbcqax.ca&password=Technology1990!!!!!!!").
                //then().statusCode(200).log().all();
        ;
    }
}
