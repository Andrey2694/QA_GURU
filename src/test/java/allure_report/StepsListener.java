package allure_report;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StepsListener {
    @Test
    public void findSoftAssertionPageTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();

        $(".js-wiki-more-pages-link").click();
        $("[data-filterable-type = substring]").$$(".details-reset").findBy(text("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("JUnit5"));
    }

}
