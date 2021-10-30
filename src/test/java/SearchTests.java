import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {
    @Test
    public void selenideSearchTest() {
        open("https://www.google.com");
        $x(("//div[@class = 'jyfHyd'][text() = 'Принимаю']")).click();
        $("[name=q]").setValue("Selenide").pressEnter();
        $("[id = search]").shouldHave(text("selenide.org"));
    }
}
