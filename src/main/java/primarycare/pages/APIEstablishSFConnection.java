package primarycare.pages;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class APIEstablishSFConnection {
    public String establishConnection(){
            return
                given().
                    urlEncodingEnabled(true)
                    .param("grant_type","password")
                    .param("client_id","3MVG9gtjsZa8aaSWGuVHX1CXMLqvIEHcMI.CyU6UcHbqX6qz3w0OFjmLx0_n4ibEo4zxeDxPi3365oDP_DuHC")
                    .param("client_secret","E69F000F4C4F99B080E236A4B3A3189A7443DAACF8273B2D33C0B805C89E7946")
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
