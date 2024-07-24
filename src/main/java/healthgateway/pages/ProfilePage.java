    package bcvax.pages;

    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;


    public class ProfilePage extends BasePage{

        @FindBy(xpath = "//p[strong='Full Name']")
        private WebElement textFullName;

        @FindBy(xpath = "//p[strong='Personal Health Number']")
        private WebElement textPersonalHealthNumber;

        @FindBy(xpath = "//p[strong='Email address']")
        private WebElement textEmailAddress;

        @FindBy(xpath = "//p[strong='Cell number (SMS notifications)']")
        private WebElement textCellNumber;

        @FindBy(xpath = "//p[strong='Mailing address']")
        private WebElement textMailingAddress;

        @FindBy(xpath = "//p[strong='Physical address']")
        private WebElement textPhysicalAddress;

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
            return getText(textCellNumber).split("\n")[1].trim();
        }

        public String getMailingAddress(){
            return getText(textMailingAddress).split("\n")[1].trim();
        }

        public String getPhysicalAddress(){
            return getText(textPhysicalAddress).split("\n")[1].trim();
        }

    }