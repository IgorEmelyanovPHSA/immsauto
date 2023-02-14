package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class CommonMethods extends BasePage{

    public CommonMethods(WebDriver driver) {
        super(driver);
    }

    private By supplyConsoleApp = By.xpath(".//span[@title='Health Connect - Supply Console']");
    private By select_app_launcher1 = By.xpath("//div[@class='slds-icon-waffle']");
    private By healthconnectApp1 = By.xpath("//p[text()='Health Connect - Supply Console']");
    private By supplyLocation = By.xpath("//a[@title='Supply Locations']");
    private By callCenterPageId = By.xpath(".//span[@title='Call Center Console']");

    @FindBy(xpath = "//div[@class='slds-icon-waffle']")
    private WebElement appLauncher;

    @FindBy(xpath = "//input[@class='slds-input' and @type='search']")
    private WebElement searchAppsAndItems;

    @FindBy(xpath = ".//span[@title='Call Center Console']")
    private WebElement callCenterConsolePageID;


    @FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
    private WebElement dropdownMenu;

    //WHAT KIND OF XPATH IS THIS?
    @FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[7]/div/a/span[2]/span")
    private WebElement supplyLocationInDropdown;

    @FindBy(xpath = "//button[@aria-label = 'Search']")
    private WebElement searchAssistant;

    @FindBy(xpath = "//input[@placeholder = 'Search...']")
    private WebElement searchInput;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean userFoundWithParameters(String legalFirstName, String legalMiddleName, String legalLastName) throws InterruptedException {
        By userFoundWithParameter = null;
        //To handle scenario where user doesn't have middle name
        if(legalMiddleName.length() == 0){
            userFoundWithParameter = By.xpath("//a[@title='" + legalFirstName + " " + legalLastName + "']");
        }
        else
            {
                userFoundWithParameter = By.xpath("//a[@title='" + legalFirstName + " " + legalMiddleName + " " + legalLastName + "']");
            }
        if (!isDisplayed(userFoundWithParameter)) {
            return false;
        }
        WebElement userFoundWithParameterId = driver.findElement(userFoundWithParameter);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", userFoundWithParameterId);
        Thread.sleep(5000);
        return true;
    }

    public void globalSearch(String textToSearch) throws InterruptedException {
        waitForElementToBeVisible(driver, searchAssistant, 10);
        click(searchAssistant);
        waitForElementToBeVisible(driver, searchInput, 10);
        typeIn(searchInput,textToSearch);
        searchInput.sendKeys(Keys.RETURN);
        Thread.sleep(5000);
    }

    public void globalSearchCP(String textToSearch) throws InterruptedException {
        waitForElementToBeVisible(driver, searchInput, 10);
        typeIn(searchInput,textToSearch);
        searchInput.sendKeys(Keys.RETURN);
        Thread.sleep(12000);
    }

    public boolean isUserFoundValidation(String legalFirstName, String legalMiddleName, String legalLastName) throws InterruptedException {
        boolean isUserFound = false;
        for(int i = 1; i<=7; i++ ) {
            if (!userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName)) {
                log(i +"-try to find user: " + legalFirstName + " " + legalLastName + " not found, re-try!");
                refreshBrowser();
                Thread.sleep(15000);
            } else {
                log("/*---User --> " + legalFirstName + " " + legalLastName + " present on the page--*/");
                isUserFound = true;
                break;
            }
        }
        return isUserFound;
    }

    public void refreshBrowser() throws InterruptedException {
        driver.navigate().refresh();
    }

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

    public void goToCallCenterConsolePageIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
        closeAutomationLocationTab();
        Thread.sleep(5000);
        if (isDisplayed(callCenterPageId)) {
            log("/*-- User already on Call Center Console Page --*/");
        } else {
            log("/*-- Navigate to Call Center Console Page --*/");
            click(appLauncher);
            Thread.sleep(5000);
            typeIn(searchAppsAndItems, "Call Center Console");
            //Needs more work
        }

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

    public double[] getRemainingDosesQtyAndConversionFactor(int rowNumber) throws InterruptedException {
        DecimalFormat df = new DecimalFormat("0.00");
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
        double remainingDoses = Double.parseDouble(getValue(remainingDosesWebElement));
        double remainingQuantity = Double.parseDouble(getValue(remainingQuantityWebElement));
        double doseConversionFactor = Double.valueOf(df.format(remainingDoses / remainingQuantity));
        double[] dosesQuantityConversionFactor = {remainingDoses, remainingQuantity, doseConversionFactor};

        return dosesQuantityConversionFactor;
    }


}