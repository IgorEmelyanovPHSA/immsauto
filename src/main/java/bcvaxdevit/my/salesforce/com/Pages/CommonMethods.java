package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;

public class CommonMethods extends BasePage{

    public CommonMethods(WebDriver driver) {
        super(driver);
    }

    private By supplyConsoleApp = By.xpath(".//span[@title='Health Connect - Supply Console']");
    private By select_app_launcher1 = By.xpath("//div[@class='slds-icon-waffle']");
    private By healthconnectApp1 = By.xpath("//p[text()='Health Connect - Supply Console']");
    private By supplyLocation = By.xpath("//a[@title='Supply Locations']");

    @FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
    private WebElement dropdownMenu;

    //WHAT KIND OF XPATH IS THIS?
    @FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[7]/div/a/span[2]/span")
    private WebElement supplyLocationInDropdown;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void closeAutomationLocationTab() throws InterruptedException {
        do {
            try {
                WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
                click(closetab);
                Thread.sleep(2000);
            } catch (NoSuchElementException e) {
                log("/*---there are no Tab's to close anymore");
            }
        } while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
    }

    public void selectHealthConnectApp() throws InterruptedException {
        waitForElementToBeLocated(driver, select_app_launcher1, 10);
        WebElement element = driver.findElement(select_app_launcher1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(5000);
        waitForElementToBeLocated(driver, healthconnectApp1, 10);
        WebElement element1 = driver.findElement(healthconnectApp1);
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", element1);
        Thread.sleep(2000);
    }
    
    public void goToSupplyPageIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
        closeAutomationLocationTab();
        Thread.sleep(5000);
        if (isDisplayed(supplyConsoleApp)) {
            log("/*-- User already on Health Connect - Supply Console --*/");
        } else {
            log("/*-- Navigate to Health Connect - Supply Console --*/");
            selectHealthConnectApp();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        log("/*-- Close all open tabs --*/");
        closeAutomationLocationTab();
        if (isDisplayed(supplyLocation)) {
            log("/*-- User is already on Supply loc--*/");
        } else {
            log("/*-- Click Dropdown Menu --*/");
            click(dropdownMenu);
            Thread.sleep(5000);
            log("/*-- Navigate and Select Supply Locations --*/");
            click(supplyLocationInDropdown);
            Thread.sleep(2000);
        }
        closeAutomationLocationTab();
        log("/*-- Close all open tabs --*/");
        Thread.sleep(2000);
    }

    public int getMatchedRowToLotInRow1() throws Exception {
        //1. This Method is reading the Lot number in 1 row
        //2. Comparing by Lot number with other Lot's to find a match
        //3. As result return matched row number
        //Could be enhanced in the future to compare grid of doses and quantities
        HashMap<Integer, ArrayList<String>> lotAndRemainingDosesAndQuantityMap = new HashMap<>();
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(driver);
        int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
        if(countSupplyContainers < 2){
            throw new Exception("Min amount of containers required is 2");
        }
        log("Count of Supply Containers " +countSupplyContainers);

        int firstRowForFirstLotNumber = 1; //Default 1
        String firstRowLotNumberInGrid = "";
        int matchingRowForFirstLotNumber = 0;
        String matchingLotNumber;

        int d = 3;
        int q = 4;
        for (int i = 1; i <= countSupplyContainers; i++) {
            ArrayList<String> value = new ArrayList<>();
            WebElement lotNumberWebElement = driver.findElement(By.xpath("(//td[@data-label='Lot Number']//lightning-base-formatted-text)[" + i + "]"));
            WebElement remainingDosesWebElement = driver.findElement(By.xpath("(//lightning-formatted-number)[" + d + "]"));
            WebElement remainingQuantityWebElement = driver.findElement(By.xpath("(//lightning-formatted-number)[" + q + "]"));
            String lotNumber = getValue(lotNumberWebElement);
            String remainingDoses = getValue(remainingDosesWebElement);
            String remainingQuantity = getValue(remainingQuantityWebElement);

            if (i == 1) {
                firstRowLotNumberInGrid = lotNumber;
                value.add(String.valueOf(i)); //Row count in grid
                value.add(lotNumber);
                value.add(remainingDoses);
                value.add(remainingQuantity);
                lotAndRemainingDosesAndQuantityMap.put(i, value);
            } else {
                if (firstRowLotNumberInGrid.equalsIgnoreCase(lotNumber)) {
                    value.add(String.valueOf(i)); //Row count in grid
                    matchingLotNumber = lotNumber;
                    matchingRowForFirstLotNumber = i;
                    value.add(lotNumber);
                    value.add(remainingDoses);
                    value.add(remainingQuantity);
                    lotAndRemainingDosesAndQuantityMap.put(i, value);
                }
            }
            log("Row number " + i + " / Lot Number = " +lotNumber + " / Remaining Doses = " + remainingDoses + " / Remaining Quantity = " + remainingQuantity);
            d = d + 4;
            q = q + 4;
        }

//		for(int i=0; i<(countSupplyContainers+1); i++){  //Used only for debug
//			log("value " +i +" " +lotAndRemainingDosesAndQuantityMap.get(i));
//		}
        log("Found match row 1 " +lotAndRemainingDosesAndQuantityMap.get(firstRowForFirstLotNumber)
                + " with matching row " +matchingRowForFirstLotNumber + " " +lotAndRemainingDosesAndQuantityMap.get(matchingRowForFirstLotNumber));
        return matchingRowForFirstLotNumber;
    }

    public double[] getValueOfRemainingDoses(int rowNumber) throws InterruptedException {
        int d = 3; //For row 1 default values
        int q = 4; //For row 1 default values

        if(rowNumber != 1){
            for(int i=1; i<rowNumber; i++){
                d = d + 4;
                q = q + 4;
            }
        }
        WebElement remainingDosesWebElement = driver.findElement(By.xpath("(//lightning-formatted-number)[" + d + "]"));
        WebElement remainingQuantityWebElement = driver.findElement(By.xpath("(//lightning-formatted-number)[" + q + "]"));
        Double remainingDoses = Double.parseDouble(getValue(remainingDosesWebElement));
        Double remainingQuantity = Double.parseDouble(getValue(remainingQuantityWebElement));
        Double doseConversionFactor = remainingDoses / remainingQuantity;
        double[] dosesQuantityConversionFactor = {remainingDoses, remainingQuantity, doseConversionFactor};

        return dosesQuantityConversionFactor;
    }


}