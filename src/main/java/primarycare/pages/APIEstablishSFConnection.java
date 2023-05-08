package primarycare.pages;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class APIEstablishSFConnection {
    public String establishConnection(){
            return
                given().
                    urlEncodingEnabled(true)
                    .param("grant_type","password")
                    .param("client_id","3MVG9AYugMwGAhY5vJHzZdB4DzlMvum_eG4knJKKwyv3RRllhMayItKYwHu13nRbQAQTvN9iAEqv6K1IyVQuk")
                    .param("client_secret","7D9CD46104899B7695541035E72CFA33D80DAB8A361E8CEBC454AA3D504EB6DC")
                    .param("username","igor.emelyanov@phsa.ca.hlthbcqax")
                    .param("password","Technology1990!!!!!!!")
                    .header("Accept","application/json")
                    .header("Content-type","application/x-www-form-urlencoded").
                    when().
                    post("https://healthbc--hlthbcqax.sandbox.my.salesforce.com/services/oauth2/token")
                    .then()
                    .assertThat().statusCode(200).log().body().extract().path("access_token");
    }

}
