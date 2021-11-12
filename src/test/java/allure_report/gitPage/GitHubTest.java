package allure_report.gitPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class GitHubTest {
    public static final String CLASS_NAME_SEARCH_INPUT = ".header-search-input";

    @Step("Open GitHub")
    public void openSite() {
        open("https://github.com/");
    }

    @Step("Search repository {repository}")
    public void findRepository(String repository) {
        $(CLASS_NAME_SEARCH_INPUT).click();
        $(CLASS_NAME_SEARCH_INPUT).sendKeys(repository);
        $(CLASS_NAME_SEARCH_INPUT).submit();
    }

    @Step("Open repository {repository}")
    public void openRepository(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Assert Issue visible and has  {numberIssues}")
    public void shouldSeeIssueWithNumber(String numberIssues) {
        $("#issues-repo-tab-count").should(Condition.visible).shouldHave(Condition.exactTextCaseSensitive(numberIssues));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        final WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
