package selenoid;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WikiTest extends BaseTest {
    @Tag("web")
    @Test
    void searchWebTest() {
        open("https://ru.wikipedia.org/w/index.php?search=");
        step("Type search", () -> {
            $(byName("search")).setValue("docker")
                    .pressEnter();
        });
        step("Verify content found", () ->
                $$(".mw-search-result"));
    }
}