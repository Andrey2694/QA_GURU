import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTest {
    private final static String FIRST_NAME = "Andrey";
    private final static String LAST_NAME = "Zhmaka";
    private final static String USER_EMAIL = "AndreyZhmaka@mail.com";
    private final static String USER_NUMBER = "0123456789";
    private final static String GENDER = "Male";
    private final static String HOBBIES = "Sports";
    private final static String PICTURE = "pic.png";
    private final static String CURRENT_ADDRESS = "ul.Andrey 28/33";
    private final static String SUBJECT = "English";
    private final static String STATE = "Rajasthan";
    private final static String CITY = "Jaiselmer";

    @Test
    void formTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(USER_EMAIL);
        $("#genterWrapper").$(byText(GENDER)).click();
        $("#userNumber").setValue(USER_NUMBER);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("9");
        $(".react-datepicker__year-select").selectOptionByValue("1999");
        $x("//div[contains(@aria-label, \"October 11th, 1999\")]").click();

        $("#hobbiesWrapper").scrollTo().$(byText(HOBBIES)).click();
        $("#uploadPicture").uploadFromClasspath(PICTURE);
        $("#currentAddress").setValue(CURRENT_ADDRESS);
        $(".css-tlfecz-indicatorContainer").scrollTo().click();
        $("#react-select-3-input").setValue(STATE).pressEnter();
        $("#react-select-4-input").setValue(CITY).pressEnter();
        $("#subjectsInput").scrollTo().setValue(SUBJECT).pressEnter();
        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $$x(("//td")).shouldHave(
                itemWithText(FIRST_NAME + " " + LAST_NAME),
                itemWithText(USER_EMAIL),
                itemWithText(USER_NUMBER),
                itemWithText(SUBJECT),
                itemWithText(CURRENT_ADDRESS),
                itemWithText(STATE + " " + CITY),
                itemWithText(HOBBIES),
                itemWithText(GENDER),
                itemWithText(PICTURE),
                itemWithText("11 October,1999"));
    }
}

