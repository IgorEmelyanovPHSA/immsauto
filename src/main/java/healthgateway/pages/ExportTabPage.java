    package healthgateway.pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;

    import java.util.List;


    public class ExportTabPage extends BasePage {

        @FindBy(xpath = "//div[contains(text(), 'Record Type')]")
        private WebElement dropDownRecordType;

        @FindBy(xpath = "//div[contains(text(), 'Immunizations')]")
        private WebElement dropDownMinimizeRecordType;

        @FindBy(xpath = "//span[(text()='Immunizations')]")
        private WebElement dropDownValueImmunizations;

        @FindBy(xpath = "//button[@title='Remove']")
        private WebElement btnXRemoveSearchResults;

        @FindBy(xpath = "//input[@placeholder='Start Date']")
        private WebElement textSearchStartDate;

        @FindBy(xpath = "//input[@placeholder='End Date']")
        private WebElement textSearchEndDate;

        @FindBy(xpath = "//button[contains(text(),'Cancel')]")
        private WebElement btnCancel;

        @FindBy(xpath = "//button[text() = 'Apply']")
        private WebElement btnApply;

        @FindBy(xpath = "//div[@class='table-info' and contains(text(), 'Displaying')]")
        private WebElement textDisplayingNumberOfRecords;

        @FindBy(xpath = "//a[text() = 'BC Vaccine Schedule']")
        private WebElement urlBCVaccineSchedule;

        @FindBy(xpath = "//a[text() = 'BC Government Immunization page']")
        private WebElement urlBCGovernmentImmunizationPage;

        public ExportTabPage(WebDriver driver) {
            super(driver);
        }

        public void filterByImmunization() throws InterruptedException {
            click(dropDownRecordType);
            click(dropDownValueImmunizations);
            Thread.sleep(500);
            click(dropDownMinimizeRecordType);
        }

        public void removeSearchResults() throws InterruptedException {
            click(btnXRemoveSearchResults);
            Thread.sleep(2000);
        }

        public void filterByStartAndEndDate(String textStartDate, String textEndDate) throws InterruptedException {
            typeIn(textSearchStartDate, textStartDate);
            typeIn(textSearchEndDate, textEndDate);
            click(btnApply);
            Thread.sleep(5000);
        }

        public Integer getNumberOfRecords(){
            // Split the text by whitespace
            String[] parts = getText(textDisplayingNumberOfRecords).split("\\s+");

            // Iterate over the parts to find and parse the number
            for (String part : parts) {
                try {
                    return Integer.parseInt(part);
                } catch (NumberFormatException e) {
                }
            }
            // Return null if no number is found
            return null;
        }

        public String getLinkValueBCVaccineSchedule(){
            return getLink(urlBCVaccineSchedule);
        }

        public String getLinkValueBCGovernmentImmunizationPage(){
            return getLink(urlBCGovernmentImmunizationPage);
        }

    }