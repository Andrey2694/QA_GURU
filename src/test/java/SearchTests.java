import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchTests {



    @Test
    public void selenideSearchTest() {
        open("https://www.google.com");
        $(By.xpath("//div[@class = 'jyfHyd'][text() = 'Принимаю']")).click();
        $("[name=q]").setValue("Selenide").pressEnter();
        $("[id = search]").shouldHave(text("selenide.org"));

    }
}
