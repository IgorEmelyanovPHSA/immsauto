package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;

import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;

public class DIWA  extends BaseTest {
    @Test
    public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "219745";
        System.out.println("/*----1. Login as an Clinician to ICE --*/");
        System.out.println("/*----2. Go to Related Tab fro Person Account--*/");
        System.out.println("/*----3. clinck on 'create Immunisation Record' quick action --*/");

        Assert.assertTrue(false);
    }
}
