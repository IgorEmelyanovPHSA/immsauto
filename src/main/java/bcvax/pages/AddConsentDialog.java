package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AddConsentDialog extends BasePage {
    public AddConsentDialog(WebDriver driver) {
        super(driver);
    }
    public static boolean dialogExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By dialog_path = By.xpath("//div[@class='slds-modal__container']/header[@class='slds-modal__header']/h1[text()='Add Consent']");
        try {
            waitForElementToBeLocated(driver, dialog_path, 10);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static List<String> getInformedConsentTableHeaders(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By informed_consent_table_path = By.xpath("//div[@class='slds-modal__container']/header[@class='slds-modal__header']/h1[text()='Add Consent']/../..//flowruntime-datatable");
        waitForElementToBeEnabled(driver, informed_consent_table_path, 10);
        WebElement informed_consent_table_node = driver.findElement(informed_consent_table_path);
        GenericTable informed_consent_table = new GenericTable(informed_consent_table_node);
        List<String> headers = informed_consent_table.getHeadingsAsString();
        return headers;
    }

    public static void selectResponseGrantRadioButtonSelected(WebDriver driver) {
        WebElement grant_radio = driver.findElement(By.xpath("//input[@value='ConsentResponseValues.Grant']/..//span[@class='slds-radio_faux']"));
        grant_radio.click();
    }

    public static void selectResponseRefuseRadioButtonSelected(WebDriver driver) {
        WebElement response_radio = driver.findElement(By.xpath("//input[@value='ConsentResponseValues.Refuse']/..//span[@class='slds-radio_faux']"));
        response_radio.click();
    }

    public static void selectImmsBCProviderRadioButtonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='ProviderTypeValues.ImmsBC Provider (User)']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectNonImmsBCProviderRadioButtonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='ProviderTypeValues.Non-ImmsBC Provider (Contact)']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectObtainedFromClientSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectObtainedFromClientMinorSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client (Mature Minor)']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectObtainedFromClientSensitiveSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client (Mature Minor - Sensitive)']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectObtainedFromClientGuardianSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Substitute Decision Maker']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectFormOfConsentInPersonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.In Person']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectFormOfConsentWrittenSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.Written']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectFormOfConsentTelephoneSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.Telephone']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static boolean getResponseGrantRadioButtonSelected(WebDriver driver) {
        WebElement grant_radio = driver.findElement(By.xpath("//input[@value='ConsentResponseValues.Grant']"));
        return grant_radio.isSelected();
    }

    public static boolean getResponseRefuseRadioButtonSelected(WebDriver driver) {
        WebElement response_radio = driver.findElement(By.xpath("//input[@value='ConsentResponseValues.Refuse']"));
        return response_radio.isSelected();
    }

    public static boolean getImmsBCProviderRadioButtonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='ProviderTypeValues.ImmsBC Provider (User)']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getNonImmsBCProviderRadioButtonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='ProviderTypeValues.Non-ImmsBC Provider (Contact)']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getObtainedFromClientSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getObtainedFromClientMinorSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client (Mature Minor)']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getObtainedFromClientSensitiveSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client (Mature Minor - Sensitive)']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getObtainedFromClientGuardianSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Substitute Decision Maker']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getFormOfConsentInPersonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.In Person']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getFormOfConsentWrittenSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.Written']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getFormOfConsentTelephoneSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.Telephone']"));
        return provider_type_radio.isSelected();
    }

    public static void selectInformedConsentProvider(WebDriver driver, String provider) throws InterruptedException {

    }

    public static String getInformedConsentProvider(WebDriver driver) throws InterruptedException {
        return "";
    }

    public static void setConsentEffectiveDateFrom(WebDriver driver, String date) throws InterruptedException {

    }
    public static String getConsentEffectiveDateFrom(WebDriver driver) {
        return "";
    }

    public static void clickNextButton(WebDriver driver) throws InterruptedException {
        WebElement next_button = driver.findElement(By.xpath("//button[text()='Next']"));
        next_button.click();
    }
}
