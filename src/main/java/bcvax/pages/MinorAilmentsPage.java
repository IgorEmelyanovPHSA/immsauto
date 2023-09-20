    package bcvax.pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;

    import java.util.Arrays;
    import java.util.List;

    import static org.testng.Assert.assertEquals;

    public class MinorAilmentsPage extends BasePage{

        @FindBy(xpath = "//input[@name='FirstName']")
        private WebElement textLegalFirstName;

        @FindBy(xpath = "//input[@name='LastName']")
        private WebElement textLegalLastName;

        @FindBy(xpath = "//input[@name='PersonBirthdate']")
        private WebElement textDateOfBirth;

        @FindBy(xpath = "//input[@name='HC_Personal_Health_Number']")
        private WebElement textPersonalHealthNumber;

        @FindBy(xpath = "(//button[contains(text(),'Continue')])[1]")
        private WebElement btnContinueRegistration;

        @FindBy(xpath = "//span[text() = 'Select One']")
        private WebElement btnSelectOne;

        @FindBy(xpath = "//*[@name='citizencomment']")
        private WebElement textNotes;

        @FindBy(xpath = "//button[contains(text(), 'Book another appointment')]")
        private WebElement btnBookAnotherAppointment;

        @FindBy(xpath = "//div[contains(text(), 'Book a Pharmacy Appointment')]")
        private WebElement textBookBookAPharmacyAppointmentMainPage;

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public MinorAilmentsPage(WebDriver driver) {super(driver);}

        public void fillMandatoryFieldsOnIdentificationSection(String firstName, String lastName, String dob, String phn) throws InterruptedException {
            typeIn(textLegalFirstName, firstName);
            typeIn(textLegalLastName, lastName);
            typeIn(textDateOfBirth, dob);
            typeIn(textPersonalHealthNumber, phn);
            click(btnContinueRegistration);
        }

        public void selectOneOption(String minorAilmentsToSelect) throws InterruptedException {
            waitForElementToBeClickable(btnSelectOne);
            click(btnSelectOne);
            By textReasonXpath = By.xpath("//span[text() = '" + minorAilmentsToSelect + "']");
            waitForElementToBeEnabled(driver, textReasonXpath, 5);
            WebElement textReason = driver.findElement(textReasonXpath);
            click(textReason);
        }

        public void verifyCountAndOrderOfTheList() throws InterruptedException {
            //23items due to index 0 = "Select One" ignored for both Lists
            List<String> givenListOfNamesInOrder = Arrays.asList("Select One","Contraception","Allergies (allergic rhinitis)",
            "Cold sores","Fungal infections","Heartburn (acid reflux)","Hemorrhoids","Headaches","Impetigo",
            "Indigestion (upset stomach)","Itching, including from bug bites","Menstrual pain","Mild acne",
            "Nicotine dependence","Oral fungal infection (thrush)","Oral ulcers (canker sores)","Pink eye (conjunctivitis)",
            "Shingles","Skin rash (dermatitis)","Sprains and strains","Threadworms or pinworms",
            "Uncomplicated urinary tract infection","Vaginal candidiasis (yeast infection)");

            waitForElementToBeClickable(btnSelectOne);
            click(btnSelectOne);
            Thread.sleep(750);
            List<WebElement> allInputElements = driver.findElements(By.xpath("//span[@class='slds-truncate']"));
            log("Size of allInputElements " +(allInputElements.size()-1));
            if (allInputElements.size() < 1){
                log("Cant read the list from dropDown");
            }

            //Comparing the lists
            for (int i = 0; i < givenListOfNamesInOrder.size(); i++) {
                String nameFromGivenList = givenListOfNamesInOrder.get(i);
                String nameFromActualList = allInputElements.get(i).getText();
                log("Compering name from the given list: " + nameFromGivenList + " VS actual name in dropdown list: " + nameFromActualList);
                assertEquals(nameFromGivenList, nameFromActualList);
            }
            //Close the dropDown
            click(btnSelectOne);
        }


        public void enterNotesForPharmacist(String notes) {
            typeIn(textNotes, notes);
        }

        public void clickBtnBookAnotherAppointment() throws InterruptedException {
            click(btnBookAnotherAppointment);
        }

        public boolean isBookAPharmacyAppointmentDisplayed() throws InterruptedException {
            boolean isBookingDisplayedFlag = false;
            for(int i = 1; i<=5; i++ ) {
                if(textBookBookAPharmacyAppointmentMainPage.isDisplayed()==false){
                    Thread.sleep(1000);
                    log("Re-try: Book a Pharmacy Appointment page is NOT displayed");
                }else {
                    isBookingDisplayedFlag = true;
                    log("Book a Pharmacy Appointment page Successfully displayed");
                    break;
                }
            }
            return isBookingDisplayedFlag;
        }

    }
