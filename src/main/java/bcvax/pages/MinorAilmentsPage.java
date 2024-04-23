    package bcvax.pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

    import static org.testng.Assert.assertTrue;

    public class MinorAilmentsPage extends BasePage {
        @FindBy(xpath = "(//button[contains(text(),'Continue')])[1]")
        private WebElement btnContinueRegistration;

        @FindBy(xpath = "//button[contains(text(), 'Book another appointment')]")
        private WebElement btnBookAnotherAppointment;

        @FindBy(xpath = "//div[contains(text(), 'Book a Pharmacy Appointment')]")
        private WebElement textBookBookAPharmacyAppointmentMainPage;

        @FindBy(xpath = "//*[@class='slds-icon slds-icon_small' and @data-key='close']")
        private WebElement btnClosePopUpIncorrectInfo;

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public MinorAilmentsPage(WebDriver driver) {
            super(driver);
        }

        public void clickBtnContinue() throws InterruptedException {
            click(btnContinueRegistration);
        }

        public static void fillMandatoryFieldsOnIdentificationSection(WebDriver driver, String firstName, String lastName, String dob, String phn) throws InterruptedException {
            Thread.sleep(500);
            By legal_first_name_field_path = By.xpath("//input[@name='FirstName']");
            waitForElementToBeEnabled(driver, legal_first_name_field_path, 10);
            WebElement legal_first_name_field = driver.findElement(legal_first_name_field_path);

            By legal_last_name_field_path = By.xpath("//input[@name='LastName']");
            WebElement legal_last_name_field = driver.findElement(legal_last_name_field_path);

            By date_of_birth_field_path = By.xpath("//input[@name='PersonBirthdate']");
            WebElement date_of_birth_field = driver.findElement(date_of_birth_field_path);

            By phn_field_path = By.xpath("//input[@name='HC_Personal_Health_Number']");
            WebElement phn_field = driver.findElement(phn_field_path);
            legal_first_name_field.sendKeys(firstName);
            legal_last_name_field.sendKeys(lastName);
            date_of_birth_field.sendKeys(dob);
            phn_field.sendKeys(phn);
            By continue_registration_btn_path = By.xpath("//button[text()='Continue']");
            WebElement continue_registration_btn = driver.findElement(continue_registration_btn_path);
            continue_registration_btn.click();
        }

        public static void selectOneOption(WebDriver driver, String minorAilmentsToSelect) throws InterruptedException {
            Thread.sleep(500);
            By select_one_button_path = By.xpath("//span[text() = 'Select One']");
            waitForElementToBeEnabled(driver, select_one_button_path, 10);
            WebElement select_one_button = driver.findElement(select_one_button_path);
            select_one_button.click();
            By textReasonXpath = By.xpath("//span[text() = '" + minorAilmentsToSelect + "']");
            waitForElementToBeEnabled(driver, textReasonXpath, 5);
            WebElement textReason = driver.findElement(textReasonXpath);
            textReason.click();
        }

        public static void verifyCountAndOrderOfTheList(WebDriver driver) throws InterruptedException {
            //23items due to index 0 = "Select One" ignored for both Lists
            Thread.sleep(500);
            List<String> givenListOfNamesInOrder = Arrays.asList("Select One", "Contraception", "Acne (mild)", "Allergies and hay fever",
                    "Canker sores (Oral ulcers)", "Cold sores", "Fungal infections", "Headaches", "Heartburn (acid reflux/ GERD)", "Hemorrhoids", "Hives and itching, including from bug bites (urticaria)", "Impetigo",
                    "Menstrual pain", "Nicotine dependence", "Pink eye (conjunctivitis)", "Pinworms or threadworms", "Shingles", "Skin rash (dermatitis)",
                    "Sprains and strains (musculoskeletal pain)", "Thrush (oral fungal infection)", "Upset stomach (indigestion)",
                    "Urinary tract infection (uncomplicated)", "Yeast infection (vaginal candidiasis)");

            By select_one_button_path = By.xpath("//span[text() = 'Select One']");
            waitForElementToBeEnabled(driver, select_one_button_path, 10);
            WebElement select_one_button = driver.findElement(select_one_button_path);
            select_one_button.click();
            Thread.sleep(750);
            List<WebElement> allInputElements = driver.findElements(By.xpath("//span[@class='slds-truncate']"));
            log("Size of allInputElements " + (allInputElements.size() - 1));
            if (allInputElements.size() < 2) {
                log("Cant read the list from dropDown");
                Thread.sleep(1000);
                allInputElements = driver.findElements(By.xpath("//span[@class='slds-truncate']"));
            }

            List<String> namesFromActualList = new ArrayList<String>();
            for(WebElement item : allInputElements) {
                namesFromActualList.add(item.getText());
            }
            //Comparing the lists
            for (int i = 0; i < givenListOfNamesInOrder.size(); i++) {
                log("Make sure name from the given list: " + givenListOfNamesInOrder.get(i) + " exists in actual list");
                assertTrue(namesFromActualList.get(i).equals(givenListOfNamesInOrder.get(i)), "Item " + givenListOfNamesInOrder.get(i) + " doesn't exist in Actual Drop Down List");
            }
            //Close the dropDown
            select_one_button.click();
        }


        public static void enterNotesForPharmacist(WebDriver driver, String notes) throws InterruptedException {
            Thread.sleep(500);
            By notes_path = By.xpath("//textarea[@name='citizencomment']");
            waitForElementToBeEnabled(driver, notes_path, 10);
            WebElement notes_field = driver.findElement(notes_path);
            scrollCenter(driver, notes_field);
            notes_field.sendKeys(notes);
        }

        public void clickBtnBookAnotherAppointment() throws InterruptedException {
            click(btnBookAnotherAppointment);
        }

        public boolean isBookAPharmacyAppointmentDisplayed() throws InterruptedException {
            boolean isBookingDisplayedFlag = false;
            for (int i = 1; i <= 5; i++) {
                if (textBookBookAPharmacyAppointmentMainPage.isDisplayed() == false) {
                    Thread.sleep(1000);
                    log("Re-try: Book a Pharmacy Appointment page is NOT displayed");
                } else {
                    isBookingDisplayedFlag = true;
                    log("Book a Pharmacy Appointment page Successfully displayed");
                    break;
                }
            }
            return isBookingDisplayedFlag;
        }

        public boolean isBtnWithPhoneNumberDisplayed() throws InterruptedException {
            List<WebElement> allInputElements;
            for (int i = 1; i <= 3; i++) {
                allInputElements = driver.findElements(By.xpath("//button[@type='button' and contains(text(),'Call: 1-833-882-0022 (toll free)')]"));
                if (allInputElements.size() < 1) {
                    Thread.sleep(1000);
                    log("Re-try: Btn with phone number is NOT displayed");
                } else {
                    log("Btn with phone number Call: 1-833-882-0022 (toll free) Successfully displayed");
                    return true;
                }
            }
            return false;
        }


        public boolean isErrorMessagesForEachMandatoryFieldOnIdentificationPage() throws InterruptedException {
            click(btnContinueRegistration);
            for (int i = 1; i <= 3; i++) {
                List<WebElement> firstNameElement = driver.findElements(By.xpath("//div[@part='help-text' and contains(text(),'Please enter a valid First Name')]"));
                List<WebElement> lastNameElement = driver.findElements(By.xpath("//div[@part='help-text' and contains(text(),'Please enter a valid Last Name')]"));
                List<WebElement>  phnElement = driver.findElements(By.xpath("//div[@part='help-text' and contains(text(),'Please enter a valid Personal Health Number')]"));
                if (firstNameElement.size() < 1 || lastNameElement.size() < 1 || phnElement.size() < 1 ) {
                    Thread.sleep(1000);
                    log("Re-try: checking error messages for mandatory fields validation");
                } else {
                    log("Error messages are displayed for mandatory fields as expected");
                    return true;
                }
            }
            return false;
        }

        public boolean isPopMessagesForInformationMissMatch() throws InterruptedException {
            for (int i = 1; i <= 3; i++) {
                List<WebElement> firstNameElement = driver.findElements(By.xpath("//div[contains(text(),'We couldn’t find your information in our records.')]"));
                if (firstNameElement.size() < 1 ) {
                    Thread.sleep(1000);
                    log("Re-try: checking for Pop-Up for the first 2 incorrect attempts");
                } else {
                    log("Pop-up for incorrect attempts is displayed");
                    break;
                }
            }
            click(btnClosePopUpIncorrectInfo);
            return true;
        }

        public boolean isPopMessagesForMaximumNumberOfRetries() throws InterruptedException {
            for (int i = 1; i <= 3; i++) {
                List<WebElement> firstNameElement = driver.findElements(By.xpath("//span[contains(text(),'You used the maximum number of retries.')]"));
                if (firstNameElement.size() < 1 ) {
                    Thread.sleep(1000);
                    log("Re-try: checking for Pop-Up for the maximum number of retries");
                } else {
                    log("Pop-up maximum number of retries is displayed");
                    return true;
                }
            }
            return false;
        }



    }

