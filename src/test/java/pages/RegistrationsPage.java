package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Открываем сайт")
    public RegistrationsPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));

        return this;
    }

    @Step("Вводим имя")
    public void typeFirstName(String value) {
        firstNameInput.setValue(value);
    }

    @Step("Вводим фамилию")
    public void typeLastName(String value) {
        lastNameInput.setValue(value);
    }

    @Step("Вводим почту")
    public void typeEmail(String value) {
        $(emailInput).setValue(value);
    }

    @Step("Выбираем пол")
    public void typeGender(String value) {
        $(genderInput).$(byText(value)).click();
    }

    @Step("Вводим номер телефона")
    public void typeNumber(String value) {
        $(phoneNumberInput).setValue(value);
    }

    @Step("Выбираем тему")
    public void typeSubjects(String value) {
        $(subjectInput).setValue(value).pressEnter();
    }

    @Step("Выбираем хобби")
    public void selectHobbies(String value) {
        $(hobbiesInput).scrollTo().$(byText(value)).click();
    }

    @Step("Отправляем картинку")
    public void selectPicture(String value) {
        $(uploadInput).uploadFromClasspath(value);
    }

    @Step("Вводим адрес")
    public void typeAddress(String value) {
        $(addressInput).setValue(value);
    }

    @Step("Выбираем дату")
    public void selectState(String value) {
        $(stateId).click();
        $(stateAndCityWrapper).$(byText(value)).click();
    }

    @Step("Выбираем город")
    public void selectCity(String value) {
        $(cityID).click();
        $(stateAndCityWrapper).$(byText(value)).scrollTo().click();
    }

    @Step("Отправляем форму")
    public void sendForm() {
        $(submitButton).click();
    }

    @Step("Проверяем результат")
    public RegistrationsPage checkResultsValue(String key, String value) {
        $(tableTitleID).shouldHave(text(tableTitle));

        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));

        return this;
    }
}