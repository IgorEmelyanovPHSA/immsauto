package communityPortal.tests;

import bcvax.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class Preconditions extends BasePage {
    public Preconditions(WebDriver driver) {
        super(driver);
    }

    public static boolean registerCitizen(WebDriver driver) throws InterruptedException {
        return true;
    }

    public static boolean appointmentScheduleStage(WebDriver driver) throws InterruptedException {
        return true;
    }

    public static boolean vaccineAdministrationStage(WebDriver driver) throws InterruptedException {
        return true;
    }
}
