package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

import healthgateway.pages.Utils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
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


import static healthgateway.tests.BaseTest.log;

public class ApiQueries {

    private static String USERNAME;
    private static String PASSWORD;
    private static String LOGINURL;
    private static String CLIENTID;
    private static String CLIENTSECRET;

    static final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
    private static String REST_ENDPOINT = "/services/data" ;
    private static String API_VERSION = "/v56.0" ;
    private static String baseUri;
    private static Header oauthHeader;
    private static Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");

    static {
        try {
            USERNAME     = Utils.getEnvConfigProperty("api_user");
            PASSWORD     = Utils.getEnvConfigProperty("api_user_pw");
            LOGINURL     = Utils.getEnvConfigProperty("api_url");
            CLIENTID     = Utils.getEnvConfigProperty("api_clientId");
            CLIENTSECRET = Utils.getEnvConfigProperty("api_clientSecret");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getOauthToken() throws Exception {
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
            log("Error authenticating to Force.com: "+statusCode);
            // Error is in EntityUtils.toString(response.getEntity())
            throw new Exception("API request failed");
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
    public static String queryToGetUniqueLink(String uniqueNumber) throws Exception {
        String BCH_Unique_Link__c_value = null;
        String oauthToken = getOauthToken();

        String query ="/query?q=SELECT+BCH_Unique_Link__c+FROM+Account+WHERE+BCH_Registration_Confirmation_Number__c='"+uniqueNumber+"'";

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
                    log("JSON result of Query:\n" + json.toString(1));
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
                throw new Exception("API request failed");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) { //reference to custom throws new Exception
            e.printStackTrace();
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

    public static String queryToGetAccountId(String PersonEmail, String LastName, String FirstName) throws Exception {
        log("/*---Query to get AccountId record--*/ ");
        String AccountId = null;
        String query;
        String oauthToken = getOauthToken();

        if(PersonEmail.equalsIgnoreCase("PIR_ACCOUNT")){
           query ="/query?q=SELECT+ID+FROM+Account+WHERE+LastName='"+LastName+"'+AND+FirstName='"+FirstName+"'+Limit+3";
        }
        else{
            query ="/query?q=SELECT+ID+FROM+Account+WHERE+PersonEmail='"+PersonEmail+"'+AND+LastName='"+LastName+"'+AND+FirstName='"+FirstName+"'+Limit+3";
        }

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
                    log("JSON result of Query:\n" + json.toString(1));
                    JSONArray j = json.getJSONArray("records");
                    for (int i = 0; i < j.length(); i++){
                        AccountId = json.getJSONArray("records").getJSONObject(i).getString("Id");
                        log("AccountId: " + AccountId);
                    }
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            } else {
                log("Query was unsuccessful. Status code returned is " + statusCode);
                log("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
                log(getBody(response.getEntity().getContent()));
                throw new Exception("API request failed");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) {  //reference to custom throws new Exception
            e.printStackTrace();
        }
        return AccountId;
    }

    public static String queryToGetAccountId(String phn, String accountType) throws Exception {
        //Account type participant or PIR
        log("/*---Query to get AccountId record--*/ ");
        String AccountId = null;
        String query;
        String oauthToken = getOauthToken();

        if(accountType.equalsIgnoreCase("participantAccount")){
            query ="/query?q=SELECT+ID+FROM+Account+WHERE+BCH_Personal_Health_Number__c='"+phn+"'+AND+DDH__HC_Record_Type_Developer_Name__c='HC_Participant'";
        }
        else{
            query ="/query?q=SELECT+ID+FROM+Account+WHERE+BCH_Personal_Health_Number__c='"+phn+"'+AND+DDH__HC_Record_Type_Developer_Name__c='PIR_Account'";
        }

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
                    log("JSON result of Query:\n" + json.toString(1));
                    JSONArray j = json.getJSONArray("records");
                    for (int i = 0; i < j.length(); i++){
                        AccountId = json.getJSONArray("records").getJSONObject(i).getString("Id");
                        log("AccountId: " + AccountId);
                    }
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            } else {
                log("Query was unsuccessful. Status code returned is " + statusCode);
                log("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
                log(getBody(response.getEntity().getContent()));
                throw new Exception("API request failed");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) {  //reference to custom throws new Exception
            e.printStackTrace();
        }
        return AccountId;
    }

    public static String getAppointmentDays(String appointment_date, String appointment_type, String appointment_city) throws Exception {
        String oauthToken = getOauthToken();
        String query ="/query?q=SELECT+Id+FROM+DDH__HC_Appointment_Day_Management__c+WHERE+DDH__HC_Appointments_Date__c+=+" + appointment_date + "+AND+DDH__HC_Address__r.DDH__HC_City__c+=+%27" + appointment_city + "%27+AND+DDH__HC_Appointment_Type__r.Name+=+%27" + appointment_type.replace(" ", "+") + "%27";
        baseUri = LOGINURL + REST_ENDPOINT + API_VERSION ;
        oauthHeader = new BasicHeader("Authorization", "OAuth " + oauthToken) ;
        String uri = baseUri + query;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader(oauthHeader);
        httpGet.addHeader(prettyPrintHeader);

        // Make the request.
        HttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();

        int resp_size = 0;
        if (statusCode == 200) {
            String response_string = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(response_string);
            JSONArray my_array = json.getJSONArray("records");
            if(my_array.length() > 0) {
                String my_id = my_array.getJSONObject(0).getString("Id");
                return my_id;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public static ArrayList<HashMap> getAppointmentDayTimes(String appointment_day_id) throws Exception {
        String oauthToken = getOauthToken();
        String query ="/query?q=SELECT+DDH__HC_Start_Time__c,+DDH__HC_End_Time__c+FROM+DDH__HC_Appointment_Block_Time__c+WHERE+DDH__HC_Appointment_Day_Management__c+=+%27" + appointment_day_id + "%27";
        baseUri = LOGINURL + REST_ENDPOINT + API_VERSION ;
        oauthHeader = new BasicHeader("Authorization", "OAuth " + oauthToken) ;
        String uri = baseUri + query;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader(oauthHeader);
        httpGet.addHeader(prettyPrintHeader);

        // Make the request.
        HttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();

        int resp_size = 0;
        if (statusCode == 200) {
            String response_string = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(response_string);
            JSONArray records = json.getJSONArray("records");
            ArrayList existing_times = new ArrayList();
            for(int i = 0; i < records.length(); i++) {
                HashMap<String, String> ext_time = new HashMap<String, String>();
                ext_time.put("start_time", records.getJSONObject(i).get("DDH__HC_Start_Time__c").toString());
                ext_time.put("end_time", records.getJSONObject(i).get("DDH__HC_End_Time__c").toString());
                existing_times.add(ext_time);
            }
            return existing_times;
        } else {
            return new ArrayList<>();
        }
    }

    public static ArrayList<String> queryToGetListOfImmunizationRecords(String AccountId) throws Exception {
        log("/*---Query to get immunization record--*/ ");
        ArrayList<String> ImmunizationRecordList = new ArrayList<String>();
        String ImmunizationRecordId = null;
        String oauthToken = getOauthToken();

        String query ="/query?q=SELECT+ID+FROM+Case+WHERE+AccountId='"+AccountId+"'";

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
                    log("JSON result of Query:\n" + json.toString(1));
                    JSONArray j = json.getJSONArray("records");
                    for (int i = 0; i < j.length(); i++){
                        ImmunizationRecordId = json.getJSONArray("records").getJSONObject(i).getString("Id");
                        log("ImmunizationRecordId: " + ImmunizationRecordId);
                        ImmunizationRecordList.add(ImmunizationRecordId);
                    }
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            } else {
                log("Query was unsuccessful. Status code returned is " + statusCode);
                log("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
                log(getBody(response.getEntity().getContent()));
                throw new Exception("API request failed");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        log("Found amount of immunization records: " + ImmunizationRecordList.size());
        return ImmunizationRecordList;
    }

    public static void deleteImmunizationRecord(String immunizationRecordId) throws Exception {
        log("/*---Delete immunization record " +immunizationRecordId +"--*/ ");
        String oauthToken = getOauthToken();
        oauthHeader = new BasicHeader("Authorization", "OAuth " + oauthToken) ;

        baseUri = LOGINURL + REST_ENDPOINT + API_VERSION ;
        String uri = baseUri + "/sobjects/Case/" + immunizationRecordId;
        log("oauthToken: " + oauthToken);
        log("baseUri: "+ baseUri);
        log("Query URI: " + uri);

        try {
            //Set up the objects necessary to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpDelete httpDelete = new HttpDelete(uri);
            httpDelete.addHeader(oauthHeader);
            httpDelete.addHeader(prettyPrintHeader);

            //Make the request
            HttpResponse response = httpClient.execute(httpDelete);

            //Process the response
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 204) {
                log("Delete the immunization record successful.");
            } else {
                log("Immunization record delete NOT successful. Status code is " + statusCode);
            }
        } catch (JSONException e) {
            log("Issue creating JSON or processing results");
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public static void deleteAccount(String AccountId) throws Exception {
        log("/*---Delete account " +AccountId +"--*/ ");
        String oauthToken = getOauthToken();
        oauthHeader = new BasicHeader("Authorization", "OAuth " + oauthToken) ;

        baseUri = LOGINURL + REST_ENDPOINT + API_VERSION ;
        String uri = baseUri + "/sobjects/Account/" + AccountId;
        log("oauthToken: " + oauthToken);
        log("baseUri: "+ baseUri);
        log("Query URI: " + uri);

        try {
            //Set up the objects necessary to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpDelete httpDelete = new HttpDelete(uri);
            httpDelete.addHeader(oauthHeader);
            httpDelete.addHeader(prettyPrintHeader);

            //Make the request
            HttpResponse response = httpClient.execute(httpDelete);

            //Process the response
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 204) {
                log("Delete the account successful.");
            } else {
                log("Account delete NOT successful. Status code is " + statusCode);
            }
        } catch (JSONException e) {
            log("Issue creating JSON or processing results");
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public static void apiCallToRemoveDuplicateCitizenParticipantAccount(String PersonEmail, String LastName, String FirstName) throws Exception {
        String AccountId = queryToGetAccountId(PersonEmail,LastName,FirstName);
        if(AccountId==null){
            log("Duplicate account not found");
        }
        else {
            ArrayList<String> listOfImmunizationRecords = queryToGetListOfImmunizationRecords(AccountId);
             if (listOfImmunizationRecords.size() == 0) {
                log("Immunization records not found");
             } else {
                 for(int i=0; i < listOfImmunizationRecords.size(); i++){
                     String immunizationRecordId = listOfImmunizationRecords.get(i);
                     log("Immunization record to delete " +immunizationRecordId);
                     deleteImmunizationRecord(immunizationRecordId);
                 }
            }
            deleteAccount(AccountId);
        }
    }

    public static void apiCallToRemoveParticipantAccountByPHN(String phn) throws Exception {
        String AccountId = queryToGetAccountId(phn, "participantAccount");
        if(AccountId==null){
            log("Participant account not found");
        }
        else {
            ArrayList<String> listOfImmunizationRecords = queryToGetListOfImmunizationRecords(AccountId);
            if (listOfImmunizationRecords.size() == 0) {
                log("Immunization records not found");
            } else {
                for(int i=0; i < listOfImmunizationRecords.size(); i++){
                    String immunizationRecordId = listOfImmunizationRecords.get(i);
                    log("Immunization record to delete " +immunizationRecordId);
                    deleteImmunizationRecord(immunizationRecordId);
                }
            }
            deleteAccount(AccountId);
        }
    }

    public static void apiCallToRemovePIRAccountByPHN(String phn) throws Exception {
        String AccountId = queryToGetAccountId(phn, "PIR");
        if(AccountId==null){
            log("PIR account not found");
        }
        else {
            deleteAccount(AccountId);
        }
    }

    public static void apiCallToRemoveAppointmentsFromParticipantAccountByPHN(String phn) throws Exception {
        String accountId = queryToGetAccountId(phn, "participantAccount");
        if(accountId==null){
            log("Participant account not found");
        }
        else {
            ArrayList<String> listOfAppointmentsRecords = queryToGetListOfRecords(accountId, "appointment");
            if (listOfAppointmentsRecords.size() == 0) {
                log("Appointments records not found");
            } else {
                for(int i=0; i < listOfAppointmentsRecords.size(); i++){
                    String appointmentRecordId = listOfAppointmentsRecords.get(i);
                    log("Appointment record to delete " +appointmentRecordId);
                    deleteRecord(appointmentRecordId,"appointment");
                }
            }
        }
    }

    public static ArrayList<String> queryToGetListOfRecords(String accountId, String recordType) throws Exception {
        //Query to get records for "appointment" OR "immunization"
        String stringRecordType = null;
        if(recordType.equalsIgnoreCase("appointment"))
        {
            stringRecordType ="/query?q=SELECT+ID+FROM+DDH__HC_Session__c+WHERE+DDH__HC_Patient__c='"+accountId+"'";
        }

        if(recordType.equalsIgnoreCase("immunization"))
        {
            stringRecordType ="/query?q=SELECT+ID+FROM+Case+WHERE+AccountId='"+accountId+"'";
        }

        log("Query to get record");
        ArrayList<String> recordsList = new ArrayList<String>();
        String recordId = null;
        String oauthToken = getOauthToken();

        baseUri = LOGINURL + REST_ENDPOINT + API_VERSION ;
        oauthHeader = new BasicHeader("Authorization", "OAuth " + oauthToken) ;
        String uri = baseUri + stringRecordType;
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
                    log("JSON result of Query:\n" + json.toString(1));
                    JSONArray j = json.getJSONArray("records");
                    for (int i = 0; i < j.length(); i++){
                        recordId = json.getJSONArray("records").getJSONObject(i).getString("Id");
                        log("recordId: " + recordId);
                        recordsList.add(recordId);
                    }
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            } else {
                log("Query was unsuccessful. Status code returned is " + statusCode);
                log("An error has occurred. Http status: " + response.getStatusLine().getStatusCode());
                log(getBody(response.getEntity().getContent()));
                throw new Exception("API request failed");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        log("Found amount of " +recordType +" records: " + recordsList.size());
        return recordsList;
    }


    public static void deleteRecord(String recordId, String recordType) throws Exception {
        //Query to get records for "appointment" OR "immunization"
        String stringQuery = null;
        if(recordType.equalsIgnoreCase("appointment"))
        {
            stringQuery ="/sobjects/DDH__HC_Session__c/"+recordId;
        }

        if(recordType.equalsIgnoreCase("immunization"))
        {
            stringQuery ="/sobjects/Case/"+recordId;
        }
        log("Delete " +recordType +" record " +recordId +"--*/ ");

        String oauthToken = getOauthToken();
        oauthHeader = new BasicHeader("Authorization", "OAuth " + oauthToken) ;

        baseUri = LOGINURL + REST_ENDPOINT + API_VERSION ;
        String uri = baseUri + stringQuery;
      //  String uri = baseUri + "/sobjects/Case/" + recordId;
        log("oauthToken: " + oauthToken);
        log("baseUri: "+ baseUri);
        log("Query URI: " + uri);

        try {
            //Set up the objects necessary to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpDelete httpDelete = new HttpDelete(uri);
            httpDelete.addHeader(oauthHeader);
            httpDelete.addHeader(prettyPrintHeader);

            //Make the request
            HttpResponse response = httpClient.execute(httpDelete);

            //Process the response
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 204) {
                log("Delete the " +recordType +" record successful.");
            } else {
                log(recordType +" record delete NOT successful. Status code is " + statusCode);
            }
        } catch (JSONException e) {
            log("Issue creating JSON or processing results");
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}
