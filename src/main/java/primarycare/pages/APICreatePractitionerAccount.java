package primarycare.pages;

import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APICreatePractitionerAccount {

    public String insertPractitionerAccount (String recordTypeId, String firstname,String lastname,
                                             String gender, String birthdate, String phone,
                                             String email, String isActive, String MSP){
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        System.out.println("Connection for SF Establish with Status code 200");
        System.out.println("access_token is:" +acc_token);

        Map<String, Object> mapper =  new HashMap<String,Object>();
        mapper.put("RecordTypeId", recordTypeId);
        mapper.put("FirstName", firstname);
        mapper.put("LastName", lastname);
        mapper.put("PersonGender", gender);
        mapper.put("PersonBirthdate", birthdate);
        mapper.put("Phone", phone);
        mapper.put("PersonEmail", email);
        mapper.put("IsActive", isActive);
        mapper.put("EMPI_Verified__pc", "Verified");
        mapper.put("HealthCloudGA__SourceSystem__c", "Health1-00D8N0000008hbe");
        mapper.put("HealthCloudGA__SourceSystem__pc", "Health1-00D8N0000008hbe");
        mapper.put("MSP_Number__pc", MSP);


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


    public String insertDirectorAssociatedAccount (String practitionerFacilityName, String accountId,
                                                   String practitionerId,
                                                   String acceptingNewPatients, String maxNewRequests,
                                                   String role,
                                                   String isActive,String recordTypeId){
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        System.out.println("Connection for SF Establish with Status code 200");
        System.out.println("access_token is:" +acc_token);

        Map<String, Object> mapper =  new HashMap<String,Object>();
        mapper.put("Name", practitionerFacilityName);
        mapper.put("AccountId", accountId);
        mapper.put("PractitionerId", practitionerId);
        mapper.put("MOHBC_PRV_Accepting_new_patients__c", acceptingNewPatients);
        mapper.put("MOHBC_PRV_Max_New_Requests__c", maxNewRequests);
        mapper.put("MOHBC_PRV_Role__c", role);
        mapper.put("IsActive", isActive);
        mapper.put("RecordTypeId", recordTypeId);

        //mapper.put("EMPI_Verified__pc", "Verified");
        //mapper.put("HealthCloudGA__SourceSystem__c", "Health1-00D8N0000008hbe");
        //mapper.put("HealthCloudGA__SourceSystem__pc", "Health1-00D8N0000008hbe");


        JSONObject requester = new JSONObject(mapper);
        System.out.println("Account JSON is:" +requester.toString());

        //return
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        header("Authorization", "Bearer "+acc_token).
                        header("Content-Type", "application/json").
                        body(requester.toString()).
                        when().
                        post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/data/v57.0/sobjects/HealthcarePractitionerFacility").
                        //then().statusCode(201).log().body().extract().path("id");
        then().log().body();
        return null;
    }

}
