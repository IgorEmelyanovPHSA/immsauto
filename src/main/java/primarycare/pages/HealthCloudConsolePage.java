package primarycare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import primarycare.pages.BasePage;

public class HealthCloudConsolePage extends BasePage {

    /*---------Properties-------*/
    //@FindBy(xpath = ".//button[text() = 'Register New Citizen']")
    //private WebElement register_button;
    //private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");


    /*---------Constructor-------*/
    public HealthCloudConsolePage(WebDriver driver) {
        super(driver);
    }



    /*-------------Methods--------------*/
    public void clickRegisterButton() throws InterruptedException {
        //waitForElementToBeVisible(driver, register_button, 10);
        //WebElement element = driver.findElement(register_button_1);
        //register_button.click();
    }

}
