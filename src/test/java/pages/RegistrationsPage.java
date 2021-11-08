package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationsPage {
    private static final String FORM_TITLE = "Student Registration Form";
    private final SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            phoneNumberInput = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateId = $("#state"),
            cityID = $("#city"),
            stateAndCityWrapper = $("#stateCity-wrapper"),
            submitButton = $("#submit"),
            resultsTable = $(".table-responsive"),
            tableTitleID = $("#example-modal-sizes-title-lg");
    private final String tableTitle = "Thanks for submitting the form";
    public CalendarComponent calendar = new CalendarComponent();

    public RegistrationsPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));

        return this;
    }

    public void typeFirstName(String value) {
        firstNameInput.setValue(value);
    }

    public void typeLastName(String value) {
        lastNameInput.setValue(value);
    }

    public void typeEmail(String value) {
        $(emailInput).setValue(value);
    }

    public void typeGender(String value) {
        $(genderInput).$(byText(value)).click();
    }

    public void typeNumber(String value) {
        $(phoneNumberInput).setValue(value);
    }

    public void typeSubjects(String value) {
        $(subjectInput).setValue(value).pressEnter();
    }

    public void selectHobbies(String value) {
        $(hobbiesInput).scrollTo().$(byText(value)).click();
    }

    public void selectPicture(String value) {
        $(uploadInput).uploadFromClasspath(value);
    }

    public void typeAddress(String value) {
        $(addressInput).setValue(value);
    }

    public void selectState(String value) {
        $(stateId).click();
        $(stateAndCityWrapper).$(byText(value)).click();
    }

    public void selectCity(String value) {
        $(cityID).click();
        $(stateAndCityWrapper).$(byText(value)).scrollTo().click();
    }

    public void sendForm() {
        $(submitButton).click();
    }

    public RegistrationsPage checkResultsValue(String key, String value) {
        $(tableTitleID).shouldHave(text(tableTitle));

        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));

        return this;
    }

}