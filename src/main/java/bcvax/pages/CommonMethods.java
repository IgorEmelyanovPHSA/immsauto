package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class CommonMethods extends BasePage{

    public CommonMethods(WebDriver driver) {
        super(driver);
    }

    private By supplyConsoleTitle = By.xpath(".//span[@title='Health Connect - Supply Console']");
    private By inClinicExperienceTitle = By.xpath("//span[@title='In-Clinic Experience']");
    private By callCenterPageTitle = By.xpath(".//span[@title='Call Center Console']");
    private By appsLauncher = By.xpath("//div[@class='slds-icon-waffle']");
    private By appsSupplyLocation = By.xpath("//p[text()='Health Connect - Supply Console']");
    private By appsInClinicExperience = By.xpath("//p[text()='In-Clinic Experience']");
    private By appsBCHVaccinationPortal = By.xpath("//p[text()='BCH Vaccination Portal']");
    private By supplyLocation = By.xpath("//a[@title='Supply Locations']");
    private By recentlyViewedId = By.xpath("//span[contains(text(),'Recently Viewed')]");

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

    @FindBy(xpath = "//p[text()='In-Clinic Experience']")
    private WebElement inClinicExperienceDropdown;

    @FindBy(xpath = "//button[@aria-label = 'Search']")
    private WebElement searchAssistant;

    @FindBy(xpath = "//input[@placeholder = 'Search...']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[contains(text(),'Go to User Defaults')]")
    private WebElement btnGoToUserDefaultsCP;


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
        waitForElementToBeLocated(driver, appsLauncher, 10);
        WebElement element = driver.findElement(appsLauncher);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(5000);
        waitForElementToBeLocated(driver, appsSupplyLocation, 10);
        WebElement element1 = driver.findElement(appsSupplyLocation);
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", element1);
        Thread.sleep(2000);
    }

    public void goToSupplyPageIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
        closeAutomationLocationTab();
        Thread.sleep(5000);
        if (isDisplayed(supplyConsoleTitle)) {
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

    public void goToSupplyPageIfNeededAndConfirmPageIsDisplayedNew() throws InterruptedException {
        String title = "Age 12 and Above - Abbotsford - Abby Pharmacy";
        closeAutomationLocationTab();
        Thread.sleep(5000);
        if (isDisplayed(supplyConsoleTitle)) {
            log("/*-- User already on Supply Location Page --*/");
        } else {
            log("/*-- Navigate to Supply Location Page --*/");
            click(appsLauncher);
            Thread.sleep(3000);
            click(appsSupplyLocation);
            Thread.sleep(5000);
        }
        if (isDisplayed(recentlyViewedId)) {
            WebElement location = driver.findElement(By.xpath("//a[@title='" + title + "']"));
            click(location);
            Thread.sleep(5000);
        }

    }

    public void goToVaccinationPortalIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
        log("/*-- Navigate to BCH Vaccination Portal --*/");
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();
        click(appsLauncher);
        Thread.sleep(3500);
        click(appsBCHVaccinationPortal);
        Thread.sleep(10000);

        //Wait for the new window or tab
        wait.until(numberOfWindowsToBe(2));
        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //Work around the issue with this message, will be deleted
//        click(btnGoToUserDefaultsCP);
//        Thread.sleep(3000);
    }


    public void goToUserDefaultsIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
      //  closeAutomationLocationTab();
      //  Thread.sleep(5000);
        if (isDisplayed(inClinicExperienceTitle)) {
            log("/*-- User already on In-Clinic Experience Page --*/");
        } else {
            log("/*-- Navigate to In-Clinic Experience Page --*/");
            click(appsLauncher);
            Thread.sleep(2000);
            click(appsInClinicExperience);
            Thread.sleep(5000);
            closeAutomationLocationTab();
            Thread.sleep(5000);
        }
    }

    public void goToCallCenterConsolePageIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
        closeAutomationLocationTab();
        Thread.sleep(5000);
        if (isDisplayed(callCenterPageTitle)) {
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
    public void expiredVaxHandler() throws InterruptedException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//h1[contains(text(),'Confirm')]"));
        if(listOfElements.size()>=1){
            WebElement btnOk = driver.findElement(By.xpath("//button[@data-ok-button]"));
            click(btnOk);
            Thread.sleep(2000);
        }
    }

}