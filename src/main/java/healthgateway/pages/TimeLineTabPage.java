    package healthgateway.pages;

    import io.qameta.allure.Step;
    import org.openqa.selenium.By;
    import org.openqa.selenium.JavascriptExecutor;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;

    import java.util.List;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;


    public class TimeLineTabPage extends BasePage{

        @FindBy(xpath = "//div[contains(text(), 'Categories')]")
        private WebElement btnDropDownCategories;

        @FindBy(xpath = "//input[@type='checkbox'][@value='Lab Results']/../label[@class='slds-checkbox__label']")
        private WebElement dropDownSelectionLabResults;

        @FindBy(xpath = "//input[@type='checkbox'][@value='Imaging Reports']/../label[@class='slds-checkbox__label']")
        private WebElement dropDownSelectionImagingReports;

        @FindBy(xpath = "//input[@type='checkbox'][@value='Hospital Visits']/../label[@class='slds-checkbox__label']")
        private WebElement dropDownSelectionHospitalVisits;

        @FindBy(xpath = "//input[@type='checkbox'][@value='Immunizations']/../label[@class='slds-checkbox__label']")
        private WebElement dropDownSelectionImmunizations;

        @FindBy(xpath = "//input[@type='checkbox'][@value='Medications']/../label[@class='slds-checkbox__label']")
        private WebElement dropDownSelectionMedications;

        @FindBy(xpath = "//input[@type='checkbox'][@value='My Notes']/../label[@class='slds-checkbox__label']")
        private WebElement dropDownSelectionMyNotes;

        @FindBy(xpath = "//button[text() = 'Apply']")
        private WebElement btnApply;

        @FindBy(xpath = "(//*[contains(text(), 'Chemistry Lab Results')])[1]")
        private WebElement selectFirstLabResult;

        @FindBy(xpath = "(//*[contains(text(), 'Immunizations | COVID-19 mRNA')])[1]")
        private WebElement selectFirstImmunizationRecord;

        @FindBy(xpath = "(//b[text()='Status: '])[1]/parent::p")
        private WebElement textStatusImgReport;

        @FindBy(xpath = "(//b[text()='Order Status: '])[1]/parent::p")
        private WebElement textOrderStatus;

        @FindBy(xpath = "(//b[text()='Collection Date: '])[1]/parent::p")
        private WebElement textCollectionDate;

        @FindBy(xpath = "(//div[@class='card-title']//b)[1]")
        private WebElement textHeaderDate;

        @FindBy(xpath = "(//b[text()= 'Description: '])[1]/parent::p")
        private WebElement textDescription;

        @FindBy(xpath = "(//b[text()='Health Authority:'])[1]/parent::p")
        private WebElement textHealthAuthority;

        @FindBy(xpath = "(//b[text()='Ordering Provider: '])[1]/parent::p")
        private WebElement textOrderingProvider;

        @FindBy(xpath = "(//b[text()='Reporting Lab:'])[1]/parent::p")
        private WebElement textReportingLab;

        @FindBy(xpath = "(//b[text()='Location: '])[1]/parent::p")
        private WebElement textLocation;

        @FindBy(xpath = "(//b[text()='Product: '])[1]/parent::p")
        private WebElement textProduct;

        @FindBy(xpath = "(//b[text()='Immunizing Agent: '])[1]/parent::p")
        private WebElement textImmunizingAgent;

        @FindBy(xpath = "(//b[text()='Lot Number: '])[1]/parent::p")
        private WebElement textLotNumber;

        @FindBy(xpath = "//div[@class='dislpay-result-text slds-m-bottom_medium slds-m-top_medium']")
        private WebElement textDisplayingNumberOfRecords;

        @FindBy(xpath = "(//span[text()=' | X-Ray - Recently Updated'])[1]")
        private WebElement selectFirstImagingReport;

        @FindBy(xpath = "(//span[text()='| Sechelt Shishalh Hospital'])[1]")
        private WebElement selectFirstHospitalVisit;

        @FindBy(xpath = "(//span[text()=' | APO-FUROSEMIDE'])[1]")
        private WebElement selectFirstMedicationRecordUser14;

        @FindBy(xpath = "(//span[text()=' | Pharmacist Assessment'])")
        private WebElement selectFirstMedicationRecordUser11;

        /////Comments related xpath's
        @FindBy(xpath = "(//p[@class='slds-text-align_right hg-cmt-count'])[1]")
        private WebElement firstImagingReportCommentCount;

        @FindBy(xpath = "(//button[@title='Add a comment'])[1]")
        private WebElement btnAddACommentForFirstImagingReport;

        @FindBy(xpath = "//textarea[@placeholder='write a comment']")
        private WebElement textWriteAComment;

        @FindBy(xpath = "//button[text()='Save']")
        private WebElement btnSave;

        @FindBy(xpath = "(//*[@class='slds-card']//p[@class='slds-p-horizontal_small'])[2]")
        private WebElement textGetCreateComment;

        @FindBy(xpath = "//textarea[@placeholder='write a comment']/../../../..//button[text()='Cancel']")
        private WebElement btnCancelNewCommentCreation;

        @FindBy(xpath = "(//button[@title='Add a comment'])[1]/../../../..//a[text()='Edit']")
        private WebElement btnEditFirstCommentForFirstImgReport;

        @FindBy(xpath = "(//button[@title='Add a comment'])[1]/../../../..//a[@class='hg-cmt-delete']")
        private WebElement btnDeleteFirstCommentForFirstImgReport;

        @FindBy(xpath = "//button[text()='Ok']")
        private WebElement btnOkForDelete;

        @FindBy(xpath = "//button[@aria-label='Cancel and close']")
        private WebElement btnCancelForDeleteComment;

        /////Notes related xpath's
        @FindBy(xpath = "(//div[@class='card-header']/..//span[text()='Note'])[1]")
        private WebElement textBoxFirstNote;

        @FindBy(xpath = "(//div[@class='card-content']/slot/p)[1]")
        private WebElement textBodyFirstNote;

        @FindBy(xpath = "(//div[@class='card-header']/..//span[text()='Note'])[1]/../../../..//a[text()='Delete']")
        private WebElement btnDeleteFirstNote;

        @FindBy(xpath = "//button[text()='Add a note']")
        private WebElement btnAddANote;

        @FindBy(xpath = "//input[@placeholder='Title']")
        private WebElement textTitleOfNote;

        @FindBy(xpath = "//*[@placeholder='Enter your note here. Your notes are only available for your own viewing.']")
        private WebElement textNote;

        @FindBy(xpath = "//button[text()='Add a note']/../../../..//a[text()='Edit']")
        private WebElement btnEditFirstNote;

        /////Protective Word Xpath's
        @FindBy(xpath = "//input[@placeholder='Protective word']")
        private WebElement textInputProtectiveWord;

        @FindBy(xpath = "//button[text()='Continue']")
        private WebElement btnContinue;

        @FindBy(xpath = "//span[text()='Cancel and close']/..")
        private WebElement btnCloseWindowX;

        /////Search related Xpath's
        @FindBy(xpath = "//input[@placeholder='Search']")
        private WebElement textSearch;

        @FindBy(xpath = "//button[@title='Remove']")
        private WebElement btnXRemoveSearchResults;

        @FindBy(xpath = "//input[@placeholder='Start Date']")
        private WebElement textSearchStartDate;

        @FindBy(xpath = "//input[@placeholder='End Date']")
        private WebElement textSearchEndDate;


        public TimeLineTabPage(WebDriver driver) {
            super(driver);
        }

        @Step
        public void selectFilterLabResults() throws InterruptedException {
            click(btnDropDownCategories);
            click(dropDownSelectionLabResults);
            click(btnApply);
            Thread.sleep(2000);
        }

        @Step
        public void selectFilterImagingReports() throws InterruptedException {
            click(btnDropDownCategories);
            click(dropDownSelectionImagingReports);
            click(btnApply);
            Thread.sleep(2000);
        }

        @Step
        public void selectFilterHospitalVisits() throws InterruptedException {
            click(btnDropDownCategories);
            click(dropDownSelectionHospitalVisits);
            click(btnApply);
            Thread.sleep(2000);
        }

        @Step
        public void selectFilterImmunizations() throws InterruptedException {
            click(btnDropDownCategories);
            click(dropDownSelectionImmunizations);
            click(btnApply);
            Thread.sleep(3000);
        }

        @Step
        public void selectFilterMyNotes() throws InterruptedException {
            click(btnDropDownCategories);
            click(dropDownSelectionMyNotes);
            click(btnApply);
            Thread.sleep(2000);
        }

        public void selectFilerMedications() throws InterruptedException {
            click(btnDropDownCategories);
            click(dropDownSelectionMedications);
            click(btnApply);
            Thread.sleep(7000);
        }

        public void openFirstLabResultRecord() throws InterruptedException {
            click(selectFirstLabResult);
            Thread.sleep(8000);
        }

        public void openFirstImagingReport() throws InterruptedException {
            click(selectFirstImagingReport);
            Thread.sleep(8000);
        }

        public void openFirstMedicationRecordUser14() throws InterruptedException {
            click(selectFirstMedicationRecordUser14);
            Thread.sleep(2000);
        }
        public void openFirstMedicationRecordUser11() throws InterruptedException {
            click(selectFirstMedicationRecordUser11);
            Thread.sleep(2000);
        }

        public void openFirstHospitalVisit() throws InterruptedException {
            click(selectFirstHospitalVisit);
            Thread.sleep(2000);
        }

        public void openFirstImmunizationRecord() throws InterruptedException {
            click(selectFirstImmunizationRecord);
            Thread.sleep(2000);
        }

        public String getStatusImgReport(){
            return getText(textStatusImgReport).split(": ")[1];
        }

        public String getOrderStatus(){
            return getText(textOrderStatus).split(": ")[1];
        }

        public String getCollectionDate(){
            return getText(textCollectionDate).split(": ")[1];
        }

        public String getHeaderDate() { return getText(textHeaderDate);}

        public String getHealthAuthority() { return getText(textHealthAuthority).split(": ")[1];}

        public String getOrderingProvider(){
            return getText(textOrderingProvider).split(": ")[1];
        }

        public String getReportingLab(){
            return getText(textReportingLab).split(": ")[1];
        }

        public String getProduct(){
            return getText(textProduct).split(": ")[1];
        }

        public String getImmunizingAgent(){
            return getText(textImmunizingAgent).split(": ")[1];
        }

        public String getLotNumber(){
            return getText(textLotNumber).split(": ")[1];
        }

        public String getLocation(){
            return getText(textLocation).split(": ")[1];
        }

        public String getDisplayingNumberOfRecords(){
            return getText(textDisplayingNumberOfRecords);
        }

        public String getTextFromFirstComment(){
            return getText(textGetCreateComment);
        }

        public int getNumberOfCommentsForFirstImgReport(){
            return Integer.parseInt(getText(firstImagingReportCommentCount).split(" Comment")[0]);
        }

        public void createAComment(String textComment) throws InterruptedException {
            click(btnAddACommentForFirstImagingReport);
            Thread.sleep(2000);
            typeIn(textWriteAComment, textComment);
            click(btnSave);
            Thread.sleep(2000);
        }

        public void updateAComment(String textComment) throws InterruptedException {
            click(btnEditFirstCommentForFirstImgReport);
            typeIn(textWriteAComment, textComment);
            click(btnSave);
            Thread.sleep(2000);
        }

        public void deleteCommentsForRecord() throws InterruptedException {
            //Temp solution for status bar on the battom
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500)");
            int numberOfComments = getNumberOfCommentsForFirstImgReport();
            for(int i = 0; i < numberOfComments; i++){
                click(btnDeleteFirstCommentForFirstImgReport);
                click(btnOkForDelete);
                Thread.sleep(2000);
            }
        }

        public int getNumberOfRecords(){
            return Integer.parseInt(getText(textDisplayingNumberOfRecords).split("\\s+")[3]);
        }

        public int getNumberOfRecordsRegex() {
            // Define the regex pattern to match numbers
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(getText(textDisplayingNumberOfRecords));

            int lastNumber = 0;

            // Iterate through all matches
            while (matcher.find()) {
                // Update lastNumber with the current match
                lastNumber = Integer.parseInt(matcher.group());
            }

            // Return the last matched number
            return lastNumber;
        }

        public void deleteMyNotes() throws InterruptedException {
            int numberOfNotes = getNumberOfRecords();
            for(int i = 0; i <numberOfNotes; i++){
                click(textBoxFirstNote);
                click(btnDeleteFirstNote);
                click(btnOkForDelete);
                Thread.sleep(2000);
            }
        }

        public void createANote(String noteText) throws InterruptedException {
            click(btnAddANote);
            typeIn(textTitleOfNote, "Created by Automation");
            typeIn(textNote, noteText);
            click(btnSave);
            Thread.sleep(3000);
        }

        public void clickToExpendFirstNote() throws InterruptedException {
            click(textBoxFirstNote);
        }

        public String getTextBodyFromFirstMyNote(){
            return getText(textBodyFirstNote);
        }

        public void editAndUpdateANote(String noteText) throws InterruptedException {
            click(btnEditFirstNote);
           // typeIn(textTitleOfNote, "Created by Automation");
            typeIn(textNote, noteText);
            click(btnSave);
            Thread.sleep(3000);
        }

        public void enterProtectiveWordAndContinue(String text) throws InterruptedException {
            typeIn(textInputProtectiveWord, text);
            click(btnContinue);
            Thread.sleep(4000);
        }

        public void textValidationInvalidProtectiveWord(){
            List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'slds-text-color_error') and contains(text(), 'Invalid protective word.Try again.')]"));
            if (elements.isEmpty()) {
                throw new RuntimeException("Text not found: Invalid protective word.Try again.");
            }
        }

        public void filterBySearch(String text) throws InterruptedException {
            typeIn(textSearch, text);
            Thread.sleep(500);
            click(btnApply);
            Thread.sleep(1000);
        }

        public void removeSearchResults() throws InterruptedException {
            click(btnXRemoveSearchResults);
            Thread.sleep(2000);
        }

        public void filterByStartAndEndDate(String textStartDate, String textEndDate) throws InterruptedException {
            typeIn(textSearchStartDate, textStartDate);
            typeIn(textSearchEndDate, textEndDate);
            click(btnApply);
            Thread.sleep(1000);
        }

    }
