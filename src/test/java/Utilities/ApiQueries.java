package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.json.JSONException;
import org.testng.annotations.Test;

import static bcvaxdevit.my.salesforce.com.Tests.BaseTest.log;

public class ApiQueries {

    static final String USERNAME     = "auto_admin_portal@phsa.ca.bcvaxdevit";
    static final String PASSWORD     = "Technology1990!!!!!!";
    static final String LOGINURL     = "https://bcphsa--bcvaxdevit.my.salesforce.com";
    static final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
    static final String CLIENTID     = "3MVG9BM7anZT_gV7f1mkP5ctGzqO71H_vod4Ct5OFm19xb1h0.LcZTqN2X_JxKTZ1uEpGKI1GlcYAN4LsTnTz";
    static final String CLIENTSECRET = "67E62860A550802946981D6420A031B706BCAB170F3F7E4C863DA5FCF2A67E86";

    private static String REST_ENDPOINT = "/services/data" ;
    private static String API_VERSION = "/v50.0" ;
    private static String baseUri;
    private static Header oauthHeader;
    private static Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");

    public static String getOauthToken() {
        HttpClient httpclient = HttpClientBuilder.create().build();

        // Assemble the login request URL
        String loginURL = LOGINURL +
                GRANTSERVICE +
                "&client_id=" + CLIENTID +
                "&client_secret=" + CLIENTSECRET +
                "&username=" + USERNAME +
                "&password=" + PASSWORD;

        // Login requests must be POSTs
        HttpPost httpPost = new HttpPost(loginURL);
        HttpResponse response = null;

        try {
            // Execute the login POST request
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException cpException) {
            cpException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        // verify response is HTTP OK
        final int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            System.out.println("Error authenticating to Force.com: "+statusCode);
            // Error is in EntityUtils.toString(response.getEntity())
            System.exit(1);
        }

        String getResult = null;
        try {
            getResult = EntityUtils.toString(response.getEntity());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        JSONObject jsonObject = null;
        String loginAccessToken = null;
        String loginInstanceUrl = null;

        try {
            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
            loginAccessToken = jsonObject.getString("access_token");
            loginInstanceUrl = jsonObject.getString("instance_url");
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

        log("Access Token " +loginAccessToken );
        log("URL returned by SalesForce For Query " +loginInstanceUrl );

        // release connection
        httpPost.releaseConnection();
        return loginAccessToken;
    }

    // Query to get unique link using REST HttpGet
    public static String queryToGetUniqueLink(String uniqueNumber) {
        String BCH_Unique_Link__c_value = null;
        String oauthToken = getOauthToken();


        String query ="/query?q=SELECT+BCH_Unique_Link__c+FROM+Account+WHERE+BCH_Registration_Confirmation_Number__c='"+uniqueNumber+"'";
       // String query ="/query?q=SELECT+BCH_Unique_Link__c+FROM+Account+WHERE+BCH_Registration_Confirmation_Number__c=+'RFK8QAN5I'";
       // String query2 ="/query?q=SELECT+BCH_Unique_Link__c+FROM+Account+Limit+5";

        baseUri = LOGINURL + REST_ENDPOINT + API_VERSION ;
        oauthHeader = new BasicHeader("Authorization", "OAuth " + oauthToken) ;
        String uri = baseUri + query;
        log("oauthToken: " + oauthToken);
        log("baseUri: "+ baseUri);
        log("Query URI: " + uri);

        try {
            //Set up the HTTP objects needed to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader(oauthHeader);
            httpGet.addHeader(prettyPrintHeader);

            // Make the request.
            HttpResponse response = httpClient.execute(httpGet);

            // Process the result
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String response_string = EntityUtils.toString(response.getEntity());
                try {
                    JSONObject json = new JSONObject(response_string);
                    System.out.println("JSON result of Query:\n" + json.toString(1));
                    JSONArray j = json.getJSONArray("records");
                    for (int i = 0; i < j.length(); i++){
                        BCH_Unique_Link__c_value = json.getJSONArray("records").getJSONObject(i).getString("BCH_Unique_Link__c");
                        log("Unique link: " + BCH_Unique_Link__c_value);
                    }
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            } else {
                log("Query was unsuccessful. Status code returned is " + statusCode);
                log("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
                log(getBody(response.getEntity().getContent()));
                System.exit(-1);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        
        return BCH_Unique_Link__c_value;
    }

    private static String getBody(InputStream inputStream) {
        String result = "";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(inputStream)
            );
            String inputLine;
            while ( (inputLine = in.readLine() ) != null ) {
                result += inputLine;
                result += "\n";
            }
            in.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
