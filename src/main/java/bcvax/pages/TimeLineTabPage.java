    package bcvax.pages;

    import io.qameta.allure.Step;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;


    public class TimeLineTabPage extends BasePage{

        @FindBy(xpath = "//div[contains(text(), 'Categories')]")
        private WebElement btnDropDownCategories;

        @FindBy(xpath = "//input[@type='checkbox'][@value='Lab Results']/../label[@class='slds-checkbox__label']")
        private WebElement dropDownSelectionLabResults;

        @FindBy(xpath = "//input[@type='checkbox'][@value='Imaging Reports']/../label[@class='slds-checkbox__label']")
        private WebElement dropDownSelectionImagingReports;

        @FindBy(xpath = "//button[text() = 'Apply']")
        private WebElement btnApply;

        @FindBy(xpath = "(//*[contains(text(), 'Chemistry Lab Results')])[1]")
        private WebElement selectFirstLabResult;

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

        @FindBy(xpath = "//div[@class='dislpay-result-text slds-m-bottom_medium slds-m-top_medium']")
        private WebElement textDisplayingNumberOfRecords;

        @FindBy(xpath = "(//span[text()=' Imaging Reports | X-Ray - Recently Updated'])[1]")
        private WebElement selectFirstImagingReport;

        /////Comments related xpath's
        @FindBy(xpath = "(//p[@class='slds-text-align_right hg-cmt-count'])[1]")
        private WebElement firstImagingReportCommentCount;

        @FindBy(xpath = "(//button[@title='Add a comment'])[1]")
        private WebElement btnAddACommentForFirstImagingReport;

        @FindBy(xpath = "//textarea[@placeholder='write a comment']")
        private WebElement textWriteAComment;

        @FindBy(xpath = "//button[text()='Save']")
        private WebElement btnSaveForComment;

        @FindBy(xpath = "(//*[@class='slds-card']//p[@class='slds-p-horizontal_small'])[2]")
        private WebElement textGetCreateComment;

        @FindBy(xpath = "//textarea[@placeholder='write a comment']/../../../..//button[text()='Cancel']")
        private WebElement btnCancelNewCommentCreation;

        @FindBy(xpath = "(//button[@title='Add a comment'])[1]/../../../..//a[text()='Edit']")
        private WebElement btnEditFirstCommentForFirstImgReport;

        @FindBy(xpath = "(//button[@title='Add a comment'])[1]/../../../..//a[@class='hg-cmt-delete']")
        private WebElement btnDeleteFirstCommentForFirstImgReport;

        @FindBy(xpath = "//button[text()='Ok']")
        private WebElement btnOkForDeleteComment;

        @FindBy(xpath = "//button[@aria-label='Cancel and close']")
        private WebElement btnCancelForDeleteComment;

        public TimeLineTabPage(WebDriver driver) {
            super(driver);
        }

        @Step
        public void selectFilterLabResults() throws InterruptedException {
            //waitForElementToBeClickable(driver, btnDropDownCategories,15);
            Thread.sleep(12000);
            click(btnDropDownCategories);
            click(dropDownSelectionLabResults);
            click(btnApply);
            Thread.sleep(2000);
        }

        @Step
        public void selectFilterImagingReports() throws InterruptedException {
            //waitForElementToBeClickable(driver, btnDropDownCategories,15);
            Thread.sleep(12000);
            click(btnDropDownCategories);
            click(dropDownSelectionImagingReports);
            click(btnApply);
            Thread.sleep(2000);
        }

        public void openFirstLabResultRecord() throws InterruptedException {
            click(selectFirstLabResult);
            Thread.sleep(8000);
        }

        public void openFirstImagingReport() throws InterruptedException {
            click(selectFirstImagingReport);
            Thread.sleep(8000);
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
            click(btnSaveForComment);
            Thread.sleep(2000);
        }

        public void updateAComment(String textComment) throws InterruptedException {
            click(btnEditFirstCommentForFirstImgReport);
            typeIn(textWriteAComment, textComment);
            click(btnSaveForComment);
            Thread.sleep(2000);
        }

        public void deleteCommentsForRecord() throws InterruptedException {
            for(int i = 0; i < getNumberOfCommentsForFirstImgReport(); i++){
                click(btnDeleteFirstCommentForFirstImgReport);
                click(btnOkForDeleteComment);
                Thread.sleep(2000);
            }
        }

    }
