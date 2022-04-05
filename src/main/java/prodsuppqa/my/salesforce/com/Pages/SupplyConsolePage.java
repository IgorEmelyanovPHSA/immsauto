package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SupplyConsolePage extends BasePage {
    @FindBy(id = "Automation Supply Location_1")
    private WebElement clinician_prodsuppqa;

    public SupplyConsolePage(WebDriver driver) {
        super(driver);
    }
}
