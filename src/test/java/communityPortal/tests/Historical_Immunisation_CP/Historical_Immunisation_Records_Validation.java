package communityPortal.tests.Historical_Immunisation_CP;

import Utilities.TestListener;
import bcvax.pages.MainPageCP;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Listeners({TestListener.class})
public class Historical_Immunisation_Records_Validation extends BaseTest {

    private String legalFirstName = "Maegan";
    private String legalLastName = "bcvaxvillage";
    private String legalMiddleName = "Tanya";
    private String pirSubmissionField = "Submitted";
    private String patwayStatusFieldValidation = "Pathway Status";
    private String pirSubmissionStatusFieldValidation = "PIR Submission Status";

    @Test
    public void Validate_Historical_Immunization_PIR_Status_as_Clinician_CP() throws Exception {
        //TestcaseID = "243177"; //C243177 -- CIB - Historical Immunization Records - PIR Submission Status
        log("Target Environment: "+ Utils.getTargetEnvironment());

        MainPageCP cpMainPage = new MainPageCP(getDriver());

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin":
                log("Login as ImmsBCAdmin");
                TestcaseID = "243177"; //C243177
                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
                break;
            default:
                log("Login as Clinician user");
                TestcaseID = "243177"; //C243177
                loginPage.loginIntoCommunityPortalAsClinician();

        }
        Thread.sleep(10000);

        log("/* 2.----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
        //clinicInBox.SearchForCitizen(legalFirstName + " " + legalLastName);
        //CommunityPortalMainPage.SearchForCitizen(legalFirstName + " " + legalLastName);
        Thread.sleep(2000);


    }


}
