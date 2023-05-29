package primarycare.pages;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class APIEstablishSFConnection {
    public String establishConnection(){
            return
                given().
                    urlEncodingEnabled(true)
                    .param("grant_type","password")
                    .param("client_id","3MVG9SM6_sNwRXquRzeep8jS4SJAo7XzfJebCoF.wMWdwUzzR6yNDwh3mQ1H9pzgF8KVC9WIHrFqG0dQ75oF3")
                    .param("client_secret","E0FAB2FC847F4C5FD307005D973F77CA64ED0236C7B0ACCBF57E58784C9E9149")
                    .param("username","igor.emelyanov@phsa.ca.hlthbcqax")
                    .param("password","Technology1990!!!!!!")
                    .header("Accept","application/json")
                    .header("Content-type","application/x-www-form-urlencoded").
                    when().
                    post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token")
                    .then()
                    .assertThat().statusCode(200).log().body().extract().path("access_token");
    }

}
