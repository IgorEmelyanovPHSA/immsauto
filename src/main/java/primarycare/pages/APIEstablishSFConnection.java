package primarycare.pages;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class APIEstablishSFConnection {
    public String establishConnection(){
            return
                given().
                    urlEncodingEnabled(true)
                    .param("grant_type","password")
                    .param("client_id","3MVG9E8TNx7FN9y53NY7pkEQZLSSW4SoCXmPvef.rD0ZfaG.uMSpBKdaYM5tdWX13D_3Sj8Pkn1EGM08VMuX6")
                    .param("client_secret","FED2C722B45AF22B54387C55C4B3AE485BBFA1110FD6AA5B90ED9BD8A3EB6ACE")
                    .param("username","igor.emelyanov@phsa.hlthbcqax.ca")
                    .param("password","Technology1990!!!!!!!")
                    .header("Accept","application/json")
                    .header("Content-type","application/x-www-form-urlencoded").
                    when().
                    post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token")
                    .then()
                    .assertThat().statusCode(200).log().body().extract().path("access_token");
    }

}
