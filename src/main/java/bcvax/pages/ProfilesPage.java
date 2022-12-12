package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ProfilesPage extends BasePage{
    /*---------Properties-------*/
    @FindBy(xpath = "//span[text() = 'Related']")
    private WebElement click_related_tab;
    private By click_related_tab1 = By.xpath("//span[text() = 'Related']");

    @FindBy(xpath = "//button[contains(text(),'Create Immunization Record')]")
    private WebElement btn_create_Immunization_Record;
    private By btn_create_Immunization_Record1 = By.xpath("//button[contains(text(),'Create Immunization Record')]");



    /*---------Constructor-------*/
    public ProfilesPage(WebDriver driver) {super(driver);}


    /*-------------Methods--------------*/
    public void selectCitizenParticipantAcc(String name) throws InterruptedException {
        WebElement citizen_participant_acc = driver.findElement(By.xpath("//a[contains(text(),'Benoite Denna BCVaxD')]"));
        waitForElementToBeVisible(driver, citizen_participant_acc, 10);
        Thread.sleep(5000);
        citizen_participant_acc.click();
    }

    public void clickRelatedTab() throws InterruptedException {
        waitForElementToBeLocated(driver, click_related_tab1, 10);
        Thread.sleep(2000);
        WebElement element = driver.findElement(click_related_tab1);
        click_related_tab.click();
        //JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("arguments[0].click();", element);
    }

    public void clickCreateImmunizationRecord() throws InterruptedException {
        waitForElementToBeLocated(driver, btn_create_Immunization_Record1, 10);
        Thread.sleep(2000);
        btn_create_Immunization_Record.click();
    }

}
