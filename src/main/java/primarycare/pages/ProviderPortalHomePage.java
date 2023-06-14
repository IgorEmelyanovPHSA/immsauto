package primarycare.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProviderPortalHomePage extends BasePage{
    /*---------Properties-------*/
    @FindBy(xpath = ".//strong[text() = 'Register to get a doctor or nurse practitioner']")
    private static WebElement register_to_get_doctor_page_validation;

    @FindBy(xpath = "//a[text() = 'Home']")
    private WebElement home_link;
    private By home_link_1 = By.xpath("//a[text() = 'Home']");

    @FindBy(xpath = "(//span[text()='View'])[2]")
    private WebElement view_link;
    private By view_link_1 = By.xpath("(//span[text()='View'])[2]");

    @FindBy(xpath = "(//span[text()='My Panel'])[2]")
    private WebElement my_panel_link;
    private By my_panel_link_1 = By.xpath("(//span[text()='My Panel'])[2]");

    @FindBy(xpath = "//div[@title='Edit']")
    private WebElement edit_button;
    private By edit_button_1 = By.xpath("//div[@title='Edit']");

    @FindBy(xpath = "(//input[@data-interactive-lib-uid])[1]")
    private WebElement desired_panel_size_component;
    private By desired_panel_size_component_1 = By.xpath("(//input[@data-interactive-lib-uid])[1]");

    @FindBy(xpath = "//a[@class='select']")
    private WebElement accepting_new_patient_dropdown;
    private By accepting_new_patient_dropdown_1 = By.xpath("//a[@class='select']");

    @FindBy(xpath = "//a[@aria-disabled][@title='Yes']")
    private WebElement select_yes_from_accepting_new_patient_options;
    private By select_yes_from_accepting_new_patient_options_1 = By.xpath("//a[@aria-disabled][@title='Yes']");


    @FindBy(xpath = "(//input[@data-interactive-lib-uid])[1]")
    private WebElement max_new_requests_component;
    private By max_new_requests_component_1 = By.xpath("(//input[@data-interactive-lib-uid])[1]");

    @FindBy(xpath = "//div[@class='modal-footer slds-modal__footer']//span[text()='Save']")
    private WebElement save_button;
    private By save_button_1 = By.xpath("//div[@class='modal-footer slds-modal__footer']//span[text()='Save']");

    @FindBy(xpath = "(//span[@class='uiOutputNumber'])[1]")
    private WebElement desired_panel_size_actual_field_value;
    private By desired_panel_size_actual_field_value_1 = By.xpath("(//span[@class='uiOutputNumber'])[1]");

    @FindBy(xpath = "(//span[@class='uiOutputNumber'])[1]")
    private WebElement max_new_requests_actual_field_value;
    private By max_new_requests_actual_field_value_1 = By.xpath("(//span[@class='uiOutputNumber'])[1]");

    @FindBy(xpath = "(//div[@class='slds-form-element__static slds-truncate']//span[@data-aura-rendered-by])[2]")
    private WebElement isAccepting_actual_field_value;
    private By isAccepting_actual_field_value_1 = By.xpath("(//div[@class='slds-form-element__static slds-truncate']//span[@data-aura-rendered-by])[2]");



    /*---------Constructor-------*/
    public ProviderPortalHomePage(WebDriver driver) {
        super(driver);
    }


    /*-------------Methods--------------*/
    public static boolean validateRegisterToGetDoctorPageDisplayed() throws InterruptedException {
        try {
            waitForElementToBeVisible(driver, register_to_get_doctor_page_validation, 10);
            log("/*---Home Register to get a doctor/nurse page - shown up");
        } catch (Exception e) {
            log("/*---Home Register to get a doctor/nurse page has NOT show up");
            throw e;
        }
        return false;
    }

    public void clickHomeLink() throws InterruptedException {
        waitForElementToBeVisible(driver, home_link, 10);
        Thread.sleep(1000);
        WebElement element = driver.findElement(home_link_1);
        Thread.sleep(1000);
        home_link.click();
    }

    public void clickView() throws InterruptedException {
        waitForElementToBeVisible(driver, view_link, 10);
        Thread.sleep(1000);
        view_link.click();
    }

    public void clickMyPanel() throws InterruptedException {
        waitForElementToBeVisible(driver, my_panel_link, 10);
        Thread.sleep(1000);
        log("/*----jump to component --*/");
        WebElement element = driver.findElement(my_panel_link_1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        my_panel_link.click();
    }

    public void clickEdit() throws InterruptedException {
        waitForElementToBeVisible(driver, edit_button, 10);
        Thread.sleep(1000);
        edit_button.click();
    }

    public void enterDesiredPanelSize(int desiredPanelSize) throws InterruptedException {
        waitForElementToBeLocated(driver, desired_panel_size_component_1, 10);
        Thread.sleep(1000);
        desired_panel_size_component.click();
        Thread.sleep(1000);
        desired_panel_size_component.clear();
        Thread.sleep(1000);
        //String size ="" + String desiredPanelSize;
        String size = Integer.toString(desiredPanelSize);
        desired_panel_size_component.sendKeys(size);
        Thread.sleep(1000);
    }

    public void selectAcceptingNewPatientsOption(String isAcceptingNewPatients) throws InterruptedException {
        waitForElementToBeLocated(driver, accepting_new_patient_dropdown_1, 10);
        Thread.sleep(1000);
        accepting_new_patient_dropdown.click();
        Thread.sleep(1000);
        waitForElementToBeLocated(driver, select_yes_from_accepting_new_patient_options_1, 10);
        Thread.sleep(2000);
        select_yes_from_accepting_new_patient_options.click();
        Thread.sleep(2000);
    }

    public void enterMaxNewRequests(String maxNewRequests) throws InterruptedException {
        waitForElementToBeLocated(driver, max_new_requests_component_1, 10);
        Thread.sleep(1000);
        max_new_requests_component.click();
        Thread.sleep(1000);
        max_new_requests_component.clear();
        Thread.sleep(1000);
        max_new_requests_component.sendKeys(maxNewRequests);
        Thread.sleep(1000);
    }

    public void clickSave() throws InterruptedException {
        waitForElementToBeVisible(driver, save_button, 10);
        Thread.sleep(1000);
        save_button.click();
    }

    public String getActualDesiredPanelSizeForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, desired_panel_size_actual_field_value_1, 10);
        Thread.sleep(2000);
        desired_panel_size_actual_field_value.isDisplayed();
        return (desired_panel_size_actual_field_value.getText());
    }

    public String getActualMaxNewRequestsForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, max_new_requests_actual_field_value_1, 10);
        Thread.sleep(2000);
        max_new_requests_actual_field_value.isDisplayed();
        return (max_new_requests_actual_field_value.getText());
    }

    public String getActualIsAcceptingNewPatientsForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, isAccepting_actual_field_value_1, 10);
        Thread.sleep(2000);
        isAccepting_actual_field_value.isDisplayed();
        return (isAccepting_actual_field_value.getText());
    }




}
