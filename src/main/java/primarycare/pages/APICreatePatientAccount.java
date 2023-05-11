package primarycare.pages;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class APICreatePatientAccount {

    public String insertAccount (String recordTypeId, String firstname,String lastname,String phn,String gender,
                                 String birthdate,String preferredCommunicationChannel,
                                 String mobile, String email, String street,String city,String postalcode,String isActive){
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        System.out.println("access_token is:" +acc_token);

        Map<String, Object> mapper =  new HashMap<String,Object>();
        mapper.put("RecordTypeId", recordTypeId);
        mapper.put("FirstName", firstname);
        mapper.put("LastName", lastname);
        mapper.put("PHN__c", phn);
        mapper.put("PersonGender", gender);
        mapper.put("PersonBirthdate", birthdate);
        mapper.put("Preferred_Communication_Channel__pc", preferredCommunicationChannel);
        mapper.put("PersonMobilePhone", mobile);
        mapper.put("PersonEmail", email);
        mapper.put("PersonMailingStreet", street);
        mapper.put("PersonMailingCity", city);
        mapper.put("PersonMailingPostalCode", postalcode);
        mapper.put("IsActive", isActive);
        mapper.put("EMPI_Verified__pc", "Verified");
        mapper.put("HealthCloudGA__SourceSystem__c", "Health1-00D590000008iln");
        mapper.put("HealthCloudGA__SourceSystem__pc", "Health1-00D590000008iln");


        JSONObject requester = new JSONObject(mapper);
        System.out.println("Account JSON is:" +requester.toString());

        return
            given().
            contentType(ContentType.JSON).
            accept(ContentType.JSON).
            header("Authorization", "Bearer "+acc_token).
            header("Content-Type", "application/json").
            body(requester.toString()).
            when().
            post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/data/v57.0/sobjects/Account").
                then().statusCode(201).log().body().extract().path("id");
                //then().log().body();
            //return null;
    }
}
