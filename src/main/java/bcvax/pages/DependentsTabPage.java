    package bcvax.pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;

    import java.util.List;


    public class DependentsTabPage extends BasePage{

        @FindBy(xpath = "//button[contains(text(),'Add dependent')]")
        private WebElement btnAddDependent;

        @FindBy(xpath = "(//a[contains(text(),'Remove')])[1]")  //Remove first dependent
        private WebElement btnRemoveDependent;

        @FindBy(xpath = "//button[contains(text(),'Remove')]")  //Confirmation remove dependent
        private WebElement btnConfirmationRemoveDependent;

        @FindBy(xpath = "//button[contains(text(),'Cancel')]")
        private WebElement btnCancel;

        @FindBy(xpath = "//input[@placeholder = 'First and Middle Names']")
        private WebElement textFirstName;

        @FindBy(xpath = "//input[@placeholder = 'Last Name']")
        private WebElement textLastName;

        @FindBy(xpath = "//input[@placeholder = 'Date of Birth']")
        private WebElement textDateOfBirth;

        @FindBy(xpath = "//input[@placeholder = 'PHN']")
        private WebElement textPHN;

        @FindBy(xpath = "(//input[@type='checkbox']/..//span)[1]")
        private WebElement checkBoxConsent;

        @FindBy(xpath = "//button[contains(text(),'Register Dependent')]")
        private WebElement btnRegisterDependent;

        @FindBy(xpath = "(//h2/b)[1]")
        private WebElement textGetFirstAndLastNameOfFirstDependent;

        @FindBy(xpath = "(//b[contains(text(),'Profile')])[1]")
        private WebElement btnProfileOfFirstDependent;

        @FindBy(xpath = "(//h2/b)[1]")
        private WebElement textGetFirstAndLastNameOfFirstDependent2;


        public DependentsTabPage(WebDriver driver) {
            super(driver);
        }

        public int getNumberOfDependents(){
        List<WebElement> numberOfDependents = driver.findElements(By.xpath("//a[contains(text(),'Remove')]"));
        return numberOfDependents.size() ;
        }

        public void deleteDependents() throws InterruptedException {
          //  int numberOfDependents = getNumberOfDependents();
            for(int i = 0; i <getNumberOfDependents(); i++){
                click(btnRemoveDependent);
                click(btnConfirmationRemoveDependent);
                Thread.sleep(4000);
            }
        }

        public void addDependent(String firstName, String lastName, String dateOfBirth, String PHN) throws InterruptedException {
            click(btnAddDependent);
            typeIn(textFirstName, firstName);
            typeIn(textLastName, lastName);
            typeIn(textDateOfBirth, dateOfBirth);
            typeIn(textPHN, PHN);
            click(checkBoxConsent);
            click(btnRegisterDependent);
            Thread.sleep(4000);
        }

        public String getFirstAndLastNameOfFirstDependent() {
        //Needs to be updated
        return getText(textGetFirstAndLastNameOfFirstDependent);}

        public String getPhnOfDependent(String name) {
        //Needs to be updated
        return getText(textGetFirstAndLastNameOfFirstDependent);}

        public String getDateOfBirthDependent() { return getText(textGetFirstAndLastNameOfFirstDependent);}

        public void clickOnProfileOfDependentByName(String name) throws InterruptedException{
            By selectProfileByNameXpath = By.xpath("//*[contains(text(),'" + name + "')]/../../..//span[@title='Section Title']/b[text()='Profile']");
            click(selectProfileByNameXpath);
            Thread.sleep(500);
        }

        public void validateAccessExpired(String name) {
            By textYourAccessHasExpired = By.xpath("//*[contains(text(),'" + name + "')]/../../..//span[@title='Section Title']/b[text()='Profile']/../../../..//div[text()='Your access has expired']");
            By textYouNoLongerHaveAccess = By.xpath("//*[contains(text(),'" + name + "')]/../../..//span[@title='Section Title']/b[text()='Profile']/../../../..//div[text()='You no longer have access to this dependent as they have turned 12.']");
            List<WebElement> textAccessExpiredFound = driver.findElements(textYourAccessHasExpired);
            List<WebElement> textYouNoLongerHaveAccessFound = driver.findElements(textYouNoLongerHaveAccess);
            if(textAccessExpiredFound.size()!=1 && textYouNoLongerHaveAccessFound.size()!= 1) {
                throw new RuntimeException("NOT FOUND TEXT: Your access has expired 'OR' You no longer have access to this dependent as they have turned 12.");
            }
        }
    }
