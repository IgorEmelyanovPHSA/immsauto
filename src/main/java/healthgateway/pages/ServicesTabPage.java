    package healthgateway.pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.testng.Assert;

    import java.util.List;


    public class ServicesTabPage extends BasePage{

        By textStatusRegisteredXpath = By.xpath("//span[text()='Registered']");

        @FindBy(xpath = "//a[text()='Download']")
        private WebElement btnDownload;

        @FindBy(xpath = "//button[contains(text(),'Cancel')]")
        private WebElement btnCancel;

        @FindBy(xpath = "//button[contains(text(),'Continue')]")
        private WebElement btnContinue;

        @FindBy(xpath = "//h1[(text()='Sensitive Document')]/../..//span[@part ='formatted-rich-text']")
        private WebElement textGetSensitiveWording;

        @FindBy(xpath = "//a[(text()='If needed, you can update your decision')]")
        private WebElement linkIfNeededUpdate;

        public ServicesTabPage(WebDriver driver) {
            super(driver);
        }

        public void validateStatusRegistered(){
            List<WebElement> statusRegistered = driver.findElements(textStatusRegisteredXpath);
            Assert.assertTrue(statusRegistered.size() == 1,  "Status: Registered NOT FOUND");
        }

        public void clickBtnDownload() throws InterruptedException {
           click(btnDownload);
        }

        public void clickBtnCancel() throws InterruptedException {
            click(btnCancel);
            Thread.sleep(1000);
        }

        public String checkSensitiveWording() throws InterruptedException {
            click(btnDownload);
            Thread.sleep(1000);
            return getText(textGetSensitiveWording);
        }

        public String getLinkValue(){
            return getLink(linkIfNeededUpdate);
        }

    }


