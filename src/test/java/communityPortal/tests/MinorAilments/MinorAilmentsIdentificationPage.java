package communityPortal.tests.MinorAilments;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class MinorAilmentsIdentificationPage extends BaseTest {

    private String incorrectLegalFirstName = "Ivy1";
    private String legalLastName = "BCVaxHanna";
    private String dateOfBirth = "Dec 17, 1992";
    private String personalHealthNumber = "9746173078";

    @Test
    public void MinorAilmentsIdentificationPageValidationsTest() throws Exception {
        TestcaseID = "259503"; //C259503
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("1. Open Minor Ailments portal");
        MinorAilmentsPage minorAilmentsPage = loginPage.openMinorAilmentsPortal();

        log("2. Verify that the phone number(1-833-882-0022) is displayed ");
        Assert.assertTrue(minorAilmentsPage.isBtnWithPhoneNumberDisplayed());

        log("3. Verify an error messages for each null mandatory fields is displayed ");
        Assert.assertTrue(minorAilmentsPage.isErrorMessagesForEachMandatoryFieldOnIdentificationPage());

        log("4. Fill all identification information WITH INCORRECT LAST NAME and click btn continue");
        minorAilmentsPage.fillMandatoryFieldsOnIdentificationSection(incorrectLegalFirstName, legalLastName, dateOfBirth, personalHealthNumber);

        log("5. Verify that a pop up window appears for the first 2 incorrect attempts and closing the message");
        Assert.assertTrue(minorAilmentsPage.isPopMessagesForInformationMissMatch());

        log("6. Click btn continue, second attempt to submit citizen info with error in FirstName");
        minorAilmentsPage.clickBtnContinue();

        log("7. Verify that a pop up window appears for the first 2 incorrect attempts and closing the message");
        Assert.assertTrue(minorAilmentsPage.isPopMessagesForInformationMissMatch());

        log("8. Click btn continue, third attempt to submit citizen info with error in FirstName");
        minorAilmentsPage.clickBtnContinue();

        log("9. Verify that a pop up window appears: You used the maximum number of retries.");
        Assert.assertTrue(minorAilmentsPage.isPopMessagesForMaximumNumberOfRetries());
    }

}
