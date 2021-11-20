package parameterized;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class RegistrationFormParameterizedTest {
    static Stream<Arguments> fillFieldsMethodSourceTest() {
        return Stream.of(
                Arguments.of("withFirstValues", List.of("Andrey99999", "AndreyZhmaka99999@gmail.com", "Moscovskaya 999", "Lermontowa 999")),
                Arguments.of("secondFirstValues", List.of("Andrey66666", "AndreyAndrey66666@gmail.com", "Moscovskaya 666", "Lermontowa 666"))
        );
    }

    @CsvSource(value = {
            "withFirstValues,Andrey Zhmaka,AndreyZhmaka@gmail.com,Moscovskaya 22,Lermontowa 0"
            , "withSecondValues,Andrey Andrey,AndreyAndrey@gmail.com,Moscovskaya 99,Lermontowa 99"
    })
    @ParameterizedTest(name = "ParameterizedTest CsvSource {0}")
    void fillFieldsCsvSourceTest(String test, String name, String email, String address, String perAddress) {
        step("Open TextBox form", () -> {
            open("https://demoqa.com/");
            $(byText("Forms")).scrollTo().click();
            $(byText("Elements")).click();
            $(byText("Text Box")).click();
        });
        step("Fill fields and click Submit", () -> {
            $("#userName").setValue(name);
            $("#userEmail").setValue(email);
            $("#currentAddress").setValue(address);
            $("#permanentAddress").setValue(perAddress);
            $("#submit").scrollTo().click();
        });
        step("Check results", () -> {
            $(".border").$("#name").shouldHave(Condition.text(name));
            $(".border").$("#email").shouldHave(Condition.text(email));
            $(".border").$("#currentAddress").shouldHave(Condition.text(address));
            $(".border").$("#permanentAddress").shouldHave(Condition.text(perAddress));
        });
    }

    @MethodSource
    @ParameterizedTest(name = "ParameterizedTest MethodSource {0}")
    void fillFieldsMethodSourceTest(String test, List<String> expectedResult) {
        step("Open TextBox form", () -> {
            open("https://demoqa.com/");
            $(byText("Forms")).scrollTo().click();
            $(byText("Elements")).click();
            $(byText("Text Box")).click();
        });
        step("Fill fields and click Submit", () -> {
            $("#userName").setValue(expectedResult.get(0));
            $("#userEmail").setValue(expectedResult.get(1));
            $("#currentAddress").setValue(expectedResult.get(2));
            $("#permanentAddress").setValue(expectedResult.get(3));
            $("#submit").scrollTo().click();
        });
        step("Check results", () -> {
            $(".border").$("#name").shouldHave(Condition.text(expectedResult.get(0)));
            $(".border").$("#email").shouldHave(Condition.text(expectedResult.get(1)));
            $(".border").$("#currentAddress").shouldHave(Condition.text(expectedResult.get(2)));
            $(".border").$("#permanentAddress").shouldHave(Condition.text(expectedResult.get(3)));
        });
    }
}
