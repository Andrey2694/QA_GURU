import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormSmokeTest {
    @Test
    void formTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("Andrey");
        $("#lastName").setValue("Zhmaka");
        $("#userEmail").setValue("AndreyZhmaka@mail.com");
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue("0123456789");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("9");
        $(".react-datepicker__year-select").selectOptionByValue("1999");
        $$(".react-datepicker__day").find(text("11")).click();

        $("#hobbies-checkbox-1").scrollTo().parent().click();
        $("#hobbies-checkbox-2").parent().click();
        $("#hobbies-checkbox-3").parent().click();
        $("#uploadPicture").uploadFromClasspath("pic.png");
        $("#currentAddress").setValue("ul.Andrey 28/33");
        $(".css-tlfecz-indicatorContainer").scrollTo().click();
        $(".css-1g6gooi #react-select-3-input").setValue("Raja").pressEnter();
        $(".css-1g6gooi #react-select-4-input").setValue("Jaisel").pressEnter();
        $("#subjectsInput").scrollTo().setValue("English").pressEnter();
        $("#submit").scrollTo().click();

        $$x(("//td")).shouldHave(
                itemWithText("Andrey Zhmaka"),
                itemWithText("AndreyZhmaka@mail.com"),
                itemWithText("Male"),
                itemWithText("0123456789"),
                itemWithText("11 October,1999"),
                itemWithText("English"),
                itemWithText("Sports, Reading, Music"),
                itemWithText("ul.Andrey 28/33"),
                itemWithText("Rajasthan Jaiselmer"));
    }
}

