import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubTest {
    @Test
    public void findSoftAssertionPageTest() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();

        WebElement moreElementsBtn = $(".js-wiki-more-pages-link");
        if (moreElementsBtn.isEnabled()) {
            moreElementsBtn.click();
        }

        $("[data-filterable-type = substring]").shouldBe(visible).$$(".details-reset").findBy(text("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("JUnit5"));
    }
}
