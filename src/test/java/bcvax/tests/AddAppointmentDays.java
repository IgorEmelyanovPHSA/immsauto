package bcvax.tests;

import bcvax.pages.MainPageOrg;
import bcvax.pages.Utils;
import org.testng.annotations.Test;

public class AddAppointmentDays extends BaseTest {
    String env;
    @Test()
    public void createAppointmentDays() throws Exception {
        env = Utils.getTargetEnvironment();
        MainPageOrg orgMainPage = loginPage.orgLoginAsPPHIS();
        orgMainPage.closeAllTabs();
        orgMainPage.switchApp("Appointment Day Management");
        System.out.println("Here");
    }
}
