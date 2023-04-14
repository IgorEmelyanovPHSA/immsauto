package primarycare.pages;

import org.openqa.selenium.WebDriver;

public class PortalHealthConnectRegistryPage extends BasePage{

    /*---------Properties-------*/
    //@FindBy(xpath = ".//button[text() = 'Register New Citizen']")
    //private WebElement register_button;
    //private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");


    /*---------Constructor-------*/
    public PortalHealthConnectRegistryPage(WebDriver driver) {
        super(driver);
    }



    /*-------------Methods--------------*/
    public void clickRegisterButton() throws InterruptedException {
        //waitForElementToBeVisible(driver, register_button, 10);
        //WebElement element = driver.findElement(register_button_1);
        //register_button.click();
    }
}
