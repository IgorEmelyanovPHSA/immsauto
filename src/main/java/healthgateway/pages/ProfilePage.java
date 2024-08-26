    package healthgateway.pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;

    import java.util.List;


    public class ProfilePage extends BasePage{

        @FindBy(xpath = "//p[strong='Full Name']")
        private WebElement textFullName;

        @FindBy(xpath = "//p[strong='Personal Health Number']")
        private WebElement textPersonalHealthNumber;

        @FindBy(xpath = "//p[strong='Email address']")
        private WebElement textEmailAddress;

        @FindBy(xpath = "//*[contains(text(),'Email address')]/../..//button[@title='Clear']")
        private WebElement btnEditEmail;

        @FindBy(xpath = "//*[contains(text(),'Email address')]/../..//input[@type='text']")
        private WebElement textEditEmailFiled;

        By textEmailValidation = By.xpath("//span[text()='Valid email is required']");

        @FindBy(xpath = "//input[@placeholder='Your phone number']")
        private WebElement textCellNumber;

        @FindBy(xpath = "//*[contains(text(),'Mobile number')]/../../../..//button[@title='Clear']")
        private WebElement btnEditCellNumber;

        @FindBy(xpath = "//*[contains(text(),'Mobile number')]/../../../..//input[@type='text']")
        private WebElement textEditCellNumberFiled;

        By textCellNumberValidation = By.xpath("//span[text()='Invalid phone number']");

        @FindBy(xpath = "//p[strong='Mailing address']")
        private WebElement textMailingAddress;

        @FindBy(xpath = "//p[strong='Physical address']")
        private WebElement textPhysicalAddress;

        @FindBy(xpath = "//button[text()='Save']")
        private WebElement btnSave;

        @FindBy(xpath = "//button[text()='Cancel']")
        private WebElement btnCancel;

        public ProfilePage(WebDriver driver) {
            super(driver);
        }

        public String getFullName(){
            return getText(textFullName).split("\n")[1].trim();
        }

        public String getPersonalHealthNumber(){
            return getText(textPersonalHealthNumber).split("\n")[1].trim();
        }

        public String getEmailAddress(){
            return getText(textEmailAddress).split("\n")[1].trim();
        }

        public String getCellNumber(){
            log("cell: " +getText(textCellNumber));
            return getText(textCellNumber).split("\n")[1].trim();
        }

        public String getMailingAddress(){
            return getText(textMailingAddress).split("\n")[1].trim();
        }

        public String getPhysicalAddress(){
            return getText(textPhysicalAddress).split("\n")[1].trim();
        }

        public void emailCheck(String email) throws InterruptedException {
        click(btnEditEmail);
        Thread.sleep(500);
        typeIn(textEditEmailFiled, email);
        Thread.sleep(500);
        }

        public int checkForEmailValidationErrorMessages(){
            List<WebElement> errorMessages = driver.findElements(textEmailValidation);
            return errorMessages.size();
        }

        public void phoneNumberCheck(String phoneNumber) throws InterruptedException {
            click(btnEditCellNumber);
            Thread.sleep(500);
            typeIn(textEditCellNumberFiled, phoneNumber);
            Thread.sleep(2000);
        }

        public int checkForNumberValidationErrorMessages(){
            List<WebElement> errorMessages = driver.findElements(textCellNumberValidation);
            return errorMessages.size();
        }

    }