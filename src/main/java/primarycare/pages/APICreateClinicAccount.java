package primarycare.pages;

import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APICreateClinicAccount {

    public String insertClinicAccount (String accountClinicName,String recordClinicTypeId,String sourceSystemClinic,
                                       String businessClinicEmail,String phoneClinic,String mailingStreetClinic,
                                       String mailingCityClinic,String mailingProvinceClinic,String mailingPostalCodeClinic,
                                       String mailingCountryClinic,
                                       String mailingLocationStreetClinic,String mailingLocationCityClinic,
                                       String mailingLocationProvinceClinic,String mailingLocationPostalCodeClinic,
                                       String mailingLocationContryClinic,String isActive_Clinic){
        APIEstablishSFConnection sfConnection = new APIEstablishSFConnection();
        String acc_token = sfConnection.establishConnection();
        System.out.println("Connection for SF Establish with Status code 200");
        System.out.println("access_token is:" +acc_token);

        Map<String, Object> mapper =  new HashMap<String,Object>();
        mapper.put("Name", accountClinicName);
        mapper.put("RecordTypeId", recordClinicTypeId);
        mapper.put("HealthCloudGA__SourceSystem__c", sourceSystemClinic);
        mapper.put("AccountEmail__c", businessClinicEmail);
        mapper.put("Phone", phoneClinic);
        mapper.put("BillingStreet", mailingStreetClinic);
        mapper.put("BillingCity", mailingCityClinic);
        mapper.put("BillingState", mailingProvinceClinic);
        mapper.put("BillingPostalCode", mailingPostalCodeClinic);
        mapper.put("BillingCountry", mailingCountryClinic);

        mapper.put("ShippingStreet", mailingLocationStreetClinic);
        mapper.put("ShippingCity", mailingLocationCityClinic);
        mapper.put("ShippingState", mailingLocationProvinceClinic);
        mapper.put("ShippingPostalCode", mailingLocationPostalCodeClinic);
        mapper.put("ShippingCountry", mailingLocationContryClinic);

        mapper.put("IsActive", isActive_Clinic);

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
