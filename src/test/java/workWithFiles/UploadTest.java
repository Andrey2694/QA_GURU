package workWithFiles;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UploadTest {

    @Test
    public void uploadFile() {
        open("https://cgi-lib.berkeley.edu/ex/fup.html");
        $("[type = file]").uploadFromClasspath("doneTXT.txt");
        $("[type = submit]").click();
        $("pre").shouldHave(text("Hello World"));
    }
}
