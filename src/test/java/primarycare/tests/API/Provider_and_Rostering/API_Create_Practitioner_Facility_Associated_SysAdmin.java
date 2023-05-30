package primarycare.tests.API.Provider_and_Rostering;

import org.testng.Assert;
import org.testng.annotations.Test;
import primarycare.pages.APICreateClinicAccount;
import primarycare.pages.APICreatePractitionerAccount;
import primarycare.pages.APIDelete;
import primarycare.pages.APISelect;
import primarycare.tests.API_BaseTest_PrimaryCare;

import static primarycare.tests.BaseTest_PrimaryCare.log;

public class API_Create_Practitioner_Facility_Associated_SysAdmin extends API_BaseTest_PrimaryCare{
    //1.for Practitioner account
    public String firstName = "API_Practitioner_222";//Tanya
    public String lastName = "Director";//Drysdale
    public String birthdate = "1983-07-11";
    public String gender = "Women";
    public String email = "accountToDelete@phsa.ca";
    public String phone = "2502946960";
    public String isActive = "true";
    public String recordTypeId = "0125f000000qtflAAA";

    public String accId;
    public String personContactId;


    //2. for Clinic Facility creation
    public String accountClinicName = "API_222 CRATED CLINIC ASSOCIATED";
    public String recordClinicTypeId = "0125f000000qtffAAA";
    public String sourceSystemClinic = "Health1-00D8N0000008hbe";
    public String businessClinicEmail = "accountToDelete@phsa.ca";
    public String phoneClinic = "2503652161";
    public String mailingStreetClinic = "1008 COLUMBIA AVE";
    public String mailingCityClinic = "Castlegar";
    public String mailingProvinceClinic = "BC";
    public String mailingPostalCodeClinic = "V1N 1H2";
    public String mailingCountryClinic = "Canada";
    public String isActive_Clinic = "true";


    ///3.for Practitioner Facility Associated creation
    public String practitionerFacilityName = "API_Practitioner_222 Director | API_222 CRATED CLINIC ASSOCIATED";
    public String clinicAccountId;// = "0018N00000F9StyQAF"; //CASTLEGAR MEDICAL ASSOCIATES
    public String practitionerId; //"0038N00000D9NPoQAN";//Lori-Ann May Bus
    public String acceptingNewPatients = "Yes";
    public String maxNewRequests = "77777";
    public String role = "Director";
    //public String role = "Provider";
    //public String role = "Medical Office Assistant";
    public String isActive_associated = "true";
    public String recordTypeId_associated; // = "0128G000001VwFaQAK";
    public String MSP = "09876";

    //5 for delete
    public String practitionerFacility_accId;


    @Test(priority = 1)
    public void API_Can_Create_Practitioner_Account_No_Associated_in_Salesforce_Status_Code_201_as_SysAdmin(){
        TestcaseID = "252884"; //C252884
        APICreatePractitionerAccount apiCreatePractitionerAccount = new APICreatePractitionerAccount();
        log("Create Practitioner account record.");
        String accountID = apiCreatePractitionerAccount.insertPractitionerAccount(recordTypeId, firstName,lastName,gender,
                birthdate,phone,email,isActive,MSP);
        log("Created Practitioner's id is: " +accountID);
        log("Status Code 201- created success");
        accId = accountID;
    }

    @Test(priority = 2)
    public void API_Can_Select_PersonContactID_From_Account_Salesforce_as_SysAdmin(){
        TestcaseID = "255139"; //C255139
        APISelect sqlQuery = new APISelect();
        log("Select PersonAccountID from Account.");
        String personContactID = sqlQuery.selectPersonAccountIDSQL("SELECT PersonContactId from Account WHERE id = '"+accId+"'", "PersonContactId");
        log("Selected PersonAccountID from Account is: " +personContactID);
        //Assert.assertEquals(accountNameReturned, name);
        personContactId=personContactID;
    }

    @Test(priority = 3)
    public void API_Can_Create_Clinic_Account_in_Salesforce_Status_Code_201_as_SysAdmin(){
        TestcaseID = "255149"; //C255149
        APICreateClinicAccount apiCreateClinic = new APICreateClinicAccount();
        log("Create a Clinic account account record.");
        String accountClinicID = apiCreateClinic.insertClinicAccount(accountClinicName,recordClinicTypeId,
                sourceSystemClinic, businessClinicEmail,phoneClinic,mailingStreetClinic,mailingCityClinic,
                mailingProvinceClinic,mailingPostalCodeClinic,mailingCountryClinic,isActive_Clinic);
        log("Created a Clinic Facility id is: " +accountClinicID);
        log("Status Code 201- created success");
        clinicAccountId = accountClinicID;
    }


    @Test(priority = 4)
    public void API_Can_Create_Practitioner_DIRECTORs_Facility_Associated_in_Salesforce_Status_Code_201_as_SysAdmin(){
        TestcaseID = "252895"; //252895
        APICreatePractitionerAccount apiCreateDirectorAccount = new APICreatePractitionerAccount();
        log("Create Practitioner DIRECTOR's Associated account record.");
        String accountID = apiCreateDirectorAccount.insertDirectorAssociatedAccount(practitionerFacilityName,
                clinicAccountId,personContactId, acceptingNewPatients, maxNewRequests, role,isActive_associated,recordTypeId_associated);
        log("Created Director Practitioner id is: " +accountID);
        log("Status Code 201- created success");
        practitionerFacility_accId = accountID;
    }


    @Test(priority = 5)
    public void API_Remove_Practitioner_DIRECTORs_Facility_Associated_in_Salesforce_as_SysAdmin(){
        //TestcaseID = "252885"; //C252885
        APIDelete apidelete_practitionerFacility = new APIDelete();
        log("Delete PractitionerFacility from HealthcarePractitionerFacility.");
        String apiResponse = apidelete_practitionerFacility.deletePractitionerFacility(practitionerFacility_accId);
        log("Deleted PractitionerFacility from HealthcarePractitionerFacility is: " +practitionerFacility_accId);
        log(apiResponse);
        //Assert.assertEquals(accountNameReturned, name);
    }

    //@Test  remove Practitioner

    //@Test  remove Clinic

}
