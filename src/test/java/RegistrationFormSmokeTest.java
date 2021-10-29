import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

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

        $x("//div[@class = 'table-responsive']//td[text() = 'Student Name']/..//td[2]").shouldHave(text("Andrey Zhmaka"));
        $x("//div[@class = 'table-responsive']//td[text() = 'Student Email']/..//td[2]").shouldHave(text("AndreyZhmaka@mail.com"));
        $x("//div[@class = 'table-responsive']//td[text() = 'Gender']/..//td[2]").shouldHave(text("Male"));
        $x("//div[@class = 'table-responsive']//td[text() = 'Mobile']/..//td[2]").shouldHave(text("0123456789"));
        $x("//div[@class = 'table-responsive']//td[text() = 'Date of Birth']/..//td[2]").shouldHave(text("11 October,1999"));
        $x("//div[@class = 'table-responsive']//td[text() = 'Subjects']/..//td[2]").shouldHave(text("English"));
        $x("//div[@class = 'table-responsive']//td[text() = 'Hobbies']/..//td[2]").shouldHave(text("Sports, Reading, Music"));
        $x("//div[@class = 'table-responsive']//td[text() = 'Address']/..//td[2]").shouldHave(text("ul.Andrey 28/33"));
        $x("//div[@class = 'table-responsive']//td[text() = 'State and City']/..//td[2]").shouldHave(text("Rajasthan Jaiselmer"));


    }
}

