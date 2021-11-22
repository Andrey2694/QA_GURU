import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubTest {
    @Test
    public void findSoftAssertionPageTest() {
        //открываем страницу селенид на гитхабе
        open("https://github.com/selenide/selenide");
        //ищем вики
        $("#wiki-tab").click();
        //переходим в вики
        $(".js-wiki-more-pages-link").click();
        //ищем текст SoftAssertions
        $("[data-filterable-type = substring]").$$(".details-reset").findBy(text("SoftAssertions")).click();
        //проверяем что в списке есть текст JUnit5
        $(".markdown-body").shouldHave(text("JUnit5"));
    }
}
